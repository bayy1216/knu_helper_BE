package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.notice.NoticeRepository
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.utils.FcmUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class FcmService(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
    private val fcmUtils: FcmUtils,
) {

    fun sendNoticeMessage(startTime: LocalDateTime, endTime: LocalDateTime): Int{
        val users = userRepository.findAllWithSubscribedSites()
        val notices = noticeRepository.findAllByCreatedAtAfter(startTime)
        var count = 0
        users.forEach{ user ->
            val subscribedSites = user.subscribedSite.filter { site -> site.isAlarm }.map { site -> site.site }
            //구독중인 사이트의 공지사항만 필터링
            val filteredNotice = notices.filter { notice ->
                notice.site in subscribedSites
            }
            if(filteredNotice.isEmpty()) return@forEach

            val noticeTitles = filteredNotice.map { n -> n.title }
            val title = "${noticeTitles.size}개의 새로운 공지사항이 있습니다."
            val body = noticeTitles.reduce { acc, s -> "$acc\n$s" }
            fcmUtils.sendSingleMessage(title, body, user.fcmToken)
            count++
        }
        return count
    }
}