package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.domain.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.domain.user.UserRole
import com.reditus.knuhelper.controller.dto.common.PagingResponse
import com.reditus.knuhelper.controller.dto.notice.request.CreateNoticeRequest
import com.reditus.knuhelper.controller.dto.notice.response.NoticeDto
import com.reditus.knuhelper.controller.dto.notice.response.toDto
import com.reditus.knuhelper.utils.findByIdOrThrow
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.AccessDeniedException

@Service
@Transactional(readOnly = true)
class NoticeService(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
) {
    fun getNotice(userId: Long, page:Int, pageSize: Int, title: String?, site: String?): PagingResponse<NoticeDto> {
        val user = userRepository.findByIdWithSubscribedSites(userId) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        val sites = site?.let { listOf(Site.getSiteByKoreaName(site)) } ?: user.subscribedSite.map { it.site }
        val notices = if(title == null) {
            noticeRepository.findAllBySiteInOrderByDateDescViewsAsc(sites, PageRequest.of(page, pageSize))
        } else {
            noticeRepository.findAllBySiteInAndTitleContainingOrderByDateDescViewsAsc(sites, title, PageRequest.of(page, pageSize))
        }
        return PagingResponse(
            hasNext = notices.hasNext(),
            data = notices.content.toDto()
        )
    }

    @Transactional
    fun createNotice(role: UserRole, request: CreateNoticeRequest) : Long {
        if(role != UserRole.ADMIN) throw AccessDeniedException("권한이 없습니다.")
        val notice = Notice(
            title = request.title,
            type = request.type,
            url = request.url,
            views = request.views,
            site = request.site,
            date = request.date
        )
        noticeRepository.save(notice)
        return notice.id!!
    }

    @Transactional
    //url로 공지 조회후 있으면 수정, 없으면 생성
    fun insertNotice(role: UserRole, request: CreateNoticeRequest): ResponseEntity<Any> {
        if(role != UserRole.ADMIN) throw AccessDeniedException("권한이 없습니다.")
        val notice = noticeRepository.findByUrl(request.url)
        notice?.let {
            it.title = request.title
            it.views = request.views
            return ResponseEntity.status(HttpStatus.OK).build()
        } ?: createNotice(role, request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Transactional
    fun deleteNotice(role: UserRole, noticeId: Long): ResponseEntity<Any> {
        if(role != UserRole.ADMIN) throw AccessDeniedException("권한이 없습니다.")
        val notice = noticeRepository.findByIdOrThrow(noticeId)
        noticeRepository.delete(notice)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}