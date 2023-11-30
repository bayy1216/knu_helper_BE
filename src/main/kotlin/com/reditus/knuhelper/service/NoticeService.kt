package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.dto.common.PagingResponse
import com.reditus.knuhelper.dto.notice.response.NoticeDto
import com.reditus.knuhelper.dto.notice.response.toDto
import com.reditus.knuhelper.utils.findByIdOrThrow
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.awt.print.Pageable

@Service
@Transactional(readOnly = true)
class NoticeService(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
) {
    fun getNotice(userId: Long, page:Int, pageSize: Int): PagingResponse<NoticeDto> {
        val user = userRepository.findByIdWithSubscribedSites(userId) ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")
        val sites = user.subscribedSite.map { it.site }
        val notices = noticeRepository.findAllBySitesInOrderByCreatedAtDesc(sites.toList(), PageRequest.of(page, pageSize))
        return PagingResponse(
            hasNext = notices.hasNext(),
            data = notices.content.map { it.toDto() }
        )
    }
}