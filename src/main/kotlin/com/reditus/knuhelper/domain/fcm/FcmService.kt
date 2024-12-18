package com.reditus.knuhelper.domain.fcm

import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.infrastucture.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.infrastucture.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class FcmService(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
) {

    /**
     * startTime과 endTime 사이의 공지사항을 조회하여
     * 구독중인 유저들에게 보낼 FcmMessage를 만든다.
     *
     * 1. startTime과 endTime 사이의 공지사항을 조회한다.
     * 2. 해당 공지사항들의 site를 구독중인 유저들을 조회한다.
     * 3. 각 유저별로 FcmMessage를 만든다.
     */
    @Transactional(readOnly = true)
    fun getFcmMessages(startTime: LocalDateTime, endTime: LocalDateTime): List<FcmMessage> {
        val notices = noticeRepository.findAllByDateAfterBefore(startTime, endTime) //1
        if (notices.isEmpty()) return emptyList()

        val siteToNotices: Map<Site, List<Notice>> = notices.groupBy { it.site } // 사이트별 공지사항 그룹핑

        val users = userRepository.findAllWithIsAlarmSubscribedSites(siteToNotices.keys) //2

        return users.mapNotNull { user -> //3
            // 새 공지사항 중, 유저가 구독한 사이트의 공지사항만 필터링
            val filteredNotices = user.getAlarmSites()
                .flatMap { site -> siteToNotices[site] ?: emptyList() }

            if (filteredNotices.isEmpty()) return@mapNotNull null

            val noticeMessage = NoticeMessage.from(filteredNotices)
            noticeMessage.toFcmMessage(user.fcmToken)
        }
    }
}

data class NoticeMessage(
    val title: String,
    val body: String,
) {

    fun toFcmMessage(token: String) = FcmMessage(
        title = title,
        body = body,
        token = token
    )

    companion object {
        /**
         * 사이트별 공지사항을 그룹화하여 메시지 본문을 만든다.
         * ex)
         * 4개의 새로운 공지사항이 등록되었습니다.
         * 경북대학교
         *   경북대학교 공지사항1 제목
         *   경북대학교 공지사항2 제목
         * 컴퓨터학부
         *   컴퓨터학부 공지사항1 제목
         *   컴퓨터학부 공지사항2 제목
         */
        fun from(notices: List<Notice>): NoticeMessage {
            val title = "${notices.size}개의 새로운 공지사항이 등록되었습니다."
            val body = notices.toBodyMessage()

            return NoticeMessage(
                title = title,
                body = body
            )
        }

        private fun List<Notice>.toBodyMessage(): String {
            val groupBySiteNotice = this.groupBy { it.site }

            val bodyBuilder = StringBuilder()

            groupBySiteNotice.forEach { (site, siteNoticeList) ->
                bodyBuilder.append("${site.koreaName}\n")
                siteNoticeList.forEach { notice ->
                    bodyBuilder.append("  ${notice.title}\n")
                }
            }
            // 마지막 개행 제거
            return bodyBuilder.trimEnd().toString()
        }
    }
}