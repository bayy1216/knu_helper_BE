package com.reditus.knuhelper.controller.notice

import com.reditus.knuhelper.domain.fcm.FcmSender
import com.reditus.knuhelper.domain.fcm.FcmService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class FcmScheduler(
    private val fcmService: FcmService,
    private val fcmSender: FcmSender,
){

    @Scheduled(cron = "0 0/30 * * * *")// 매 30분마다 실행
    fun sendNotice() {
        val endTime = LocalDateTime.now()
        val startTime = endTime.minusMinutes(30)
        val messages = fcmService.getFcmMessages(startTime, endTime)
        messages.forEach { fcmSender.sendSingleMessage(message = it) }
    }
}