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

        val notices = noticeRepository.findAllByCreatedAtAfter(startTime)
        val sites = notices.map { notice -> notice.site }.distinct()
        val users = userRepository.findAllWithIsAlarmSubscribedSites(sites)
        var count = 0
        users.forEach{ user ->
            val subscribedSites = user.subscribedSite.filter { site -> site.isAlarm }.map { site -> site.site }
            //구독중인 사이트의 공지사항만 필터링
            val filteredNotice = notices.filter { notice ->
                notice.site in subscribedSites
            }
            if(filteredNotice.isEmpty()) return@forEach

            val title = "${filteredNotice.size}개의 새로운 공지사항이 등록되었습니다."
            val groupBySiteNotice = filteredNotice.groupBy { it.site }
            var body = ""
            groupBySiteNotice.keys.forEach { site ->
                val siteNoticeList = groupBySiteNotice[site]!!
                body += "${site.koreaName}\n"
                siteNoticeList.forEach { notice ->
                    body += "  ${notice.title}\n"
                }
            }

            fcmUtils.sendSingleMessage(title, body, user.fcmToken)
            count++
        }
        return count
    }
}