package com.reditus.knuhelper.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.reditus.knuhelper.domain.notice.NoticeRepository
import com.reditus.knuhelper.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FcmService(
    private val firebaseMessaging: FirebaseMessaging,
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
) {

    fun sendNoticeMessage(startTime: LocalDateTime, endTime: LocalDateTime){
        val users = userRepository.findAllWithSubscribedSites()
        val notices = noticeRepository.findAllByModifiedAtAfter(startTime)

        users.forEach{ user ->
            val subscribedSites = user.subscribedSite

            //구독중인 사이트의 공지사항만 필터링
            val filteredNotice = notices.filter { notice ->
                subscribedSites.any {
                    subscribedSite -> subscribedSite.site == notice.site
                }
            }

            val noticeTitles = filteredNotice.map { n -> n.title }
            val title = "${noticeTitles.size}개의 새로운 공지사항이 있습니다."
            val body = noticeTitles.reduce { acc, s -> "$acc\n$s" }
            sendMessage(title, body, user.fcmToken)
        }
    }



    private fun sendMessage(title: String, body: String, token: String){
        val message = Message.builder()
            .setNotification(Notification.builder().setTitle(title).setBody(body).build())
            .setToken(token)
            .build()
        try{
            val response = firebaseMessaging.send(message)
        }catch (e: Exception){
            println(e.message)
        }
    }
}