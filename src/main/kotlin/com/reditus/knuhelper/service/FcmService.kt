package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.domain.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.utils.FirebaseMessageSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class FcmService(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
    private val firebaseMessageSender: FirebaseMessageSender,
) {

    fun sendNoticeMessage(startTime: LocalDateTime, endTime: LocalDateTime): Int{
        val notices = noticeRepository.findAllByCreatedAtAfter(startTime)
        val sites = notices.map { notice -> notice.site }.distinct()
        val users = userRepository.findAllWithIsAlarmSubscribedSites(sites)
        var count = 0

        users.forEach{ user ->
            val subscribedSites: List<Site> = user.getAlarmSites()
            //구독중인 사이트의 공지사항만 필터링
            val filteredNotice = notices.filter { notice ->
                notice.site in subscribedSites
            }
            if(filteredNotice.isEmpty()) return@forEach

            val title = "${filteredNotice.size}개의 새로운 공지사항이 등록되었습니다."
            val body = makeMessageBody(filteredNotice)

            firebaseMessageSender.sendSingleMessage(
                title = title,
                body = body,
                token = user.fcmToken,
            )
            count++
        }
        return count
    }

    /**
     * 사이트별 공지사항을 그룹화하여 메시지 본문을 만든다.
     * ex)
     * 경북대학교
     *   경북대학교 공지사항1 제목
     *   경북대학교 공지사항2 제목
     * 컴퓨터학부
     *   컴퓨터학부 공지사항1 제목
     *   컴퓨터학부 공지사항2 제목
     */
    private fun makeMessageBody(filteredNotice :List<Notice>): String {

        val groupBySiteNotice = filteredNotice.groupBy { it.site }
        var body = ""
        groupBySiteNotice.keys.forEach { site ->
            val siteNoticeList = groupBySiteNotice[site]!!
            body += "${site.koreaName}\n"
            siteNoticeList.forEach { notice ->
                body += "  ${notice.title}\n"
            }
        }
        body = body.substring(0, body.length - 1)
        return body
    }
}