package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.fcm.FcmSender
import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.infrastucture.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.infrastucture.user.UserRepository
import com.reditus.knuhelper.domain.user.UserSubscribedSite
import com.reditus.knuhelper.infrastucture.user.UserSubscribedSiteRepository
import com.reditus.knuhelper.domain.fcm.FcmService
import com.reditus.knuhelper.infrastucture.fcm.FcmSenderImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class FcmServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
    private val userSubscribedSiteRepository: UserSubscribedSiteRepository,
){
    val fcmService = FcmService(
        userRepository,
        noticeRepository,
    )
    @AfterEach
    fun clean(){
        userRepository.deleteAll()
        noticeRepository.deleteAll()
        userSubscribedSiteRepository.deleteAll()
    }

    @Test
    fun 알림발송이_적상적으로_작동한다(){
        val user1 = User.fixture(name = "사용자1")
        val user2 = User.fixture(name = "사용자2")
        userRepository.saveAll(listOf(user1, user2))

        val notice1 = Notice.fixture("[신규공지]공지사항1", site = Site.KNU, url = "dsa")
        val notice2 = Notice.fixture("[전체공지]공지사항", site = Site.APPCHEM, url = "dsaa")
        val notice3 = Notice.fixture("[학사공지]공지사항", site = Site.APPCHEM, url = "dsaaa1")
        noticeRepository.saveAll(listOf(notice1, notice2, notice3))

        val user1SubscribedSite = UserSubscribedSite.fixture(
            user = user1,
            site = Site.APPCHEM,
            isAlarm = true,
        )
        val user1SubscribedSite111 = UserSubscribedSite.fixture(
            user = user1,
            site = Site.KNU,
            isAlarm = true,
        )
        val user2SubscribedSite = UserSubscribedSite.fixture(
            user = user2,
            site = Site.AIC,
            isAlarm = true,
        )
        userSubscribedSiteRepository.saveAll(listOf(user1SubscribedSite, user2SubscribedSite,user1SubscribedSite111))

        val now = LocalDateTime.now()
        val count = fcmService.getFcmMessages(now.minusHours(1), now)
        assertThat(count.size).isEqualTo(1)
    }
}