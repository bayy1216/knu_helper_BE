package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.infrastucture.notice.NoticeRepository
import com.reditus.knuhelper.infrastucture.user.UserRepository
import com.reditus.knuhelper.controller.PagingResponse
import com.reditus.knuhelper.controller.notice.request.CreateNoticeRequest
import com.reditus.knuhelper.controller.notice.response.NoticeDto
import com.reditus.knuhelper.controller.notice.response.toDto
import com.reditus.knuhelper.global.infrastucture.findByIdOrThrow
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NoticeService(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
) {
    fun getNotice(userId: Long, pageRequest: PageRequest, title: String?, site: String?): PagingResponse<NoticeDto> {
        val user = userRepository.findByIdWithSubscribedSites(userId) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        val sites = site?.let { listOf(Site.getSiteByKoreaName(site)) }
            ?: user.subscribedSite.map { it.site }

        val noticePage = noticeRepository.findAllByPagination(sites, pageRequest, title)
        return PagingResponse.from(noticePage, Notice::toDto)
    }

    @Transactional
    fun createNotice(command: NoticeCommand.Create) : Long {
        val notice = Notice.create(command)
        noticeRepository.save(notice)
        return notice.id!!
    }

    @Transactional
    //url로 공지 조회후 있으면 수정, 없으면 생성
    fun insertNotice(request: CreateNoticeRequest){
        val notice = noticeRepository.findByUrl(request.url)
        notice?.update(
            command = request.toUpdateCommand()
        ) ?: createNotice(request.toCommand())
    }

    @Transactional
    fun deleteNotice(noticeId: Long) {
        val notice = noticeRepository.findByIdOrThrow(noticeId)
        noticeRepository.delete(notice)
    }
}