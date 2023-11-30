package com.reditus.knuhelper.scheduler

import com.reditus.knuhelper.service.FcmService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class FcmScheduler(
    private val fcmService: FcmService
){

    @Scheduled(cron = "0 0/30 * * * *")// 매 30분마다 실행
    fun sendNotice() {
        val endTime = LocalDateTime.now()
        val startTime = endTime.minusMinutes(30)
        fcmService.sendNoticeMessage(startTime, endTime)
    }
}