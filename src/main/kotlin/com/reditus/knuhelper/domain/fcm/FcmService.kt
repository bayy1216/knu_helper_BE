package com.reditus.knuhelper.domain.fcm

import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.infrastucture.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.infrastucture.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
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
     * 4. 이후 전송은 service를 호출한 곳에서 진행한다.
     */
    fun getFcmMessages(startTime: LocalDateTime, endTime: LocalDateTime): List<FcmMessage>{
        val notices = noticeRepository.findAllByDateAfterBefore(startTime, endTime) //1
        val sites = notices.map { notice -> notice.site }.distinct()

        val users = userRepository.findAllWithIsAlarmSubscribedSites(sites) //2

        val fcmMessages = users.mapNotNull { user -> //3
            val subscribedSites: List<Site> = user.getAlarmSites()
            //구독중인 사이트의 공지사항만 필터링
            val filteredNotice = notices.filter { notice ->
                notice.site in subscribedSites
            }
            if(filteredNotice.isEmpty()) return@mapNotNull null

            val (title,body) = makeMessageBody(filteredNotice)

            FcmMessage(
                title = title,
                body = body,
                token = user.fcmToken,
            )
        }
        return fcmMessages
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
    private fun makeMessageBody(filteredNotice :List<Notice>): Pair<String, String> {

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
        val title = "${filteredNotice.size}개의 새로운 공지사항이 등록되었습니다."
        return Pair(title, body)
    }
}