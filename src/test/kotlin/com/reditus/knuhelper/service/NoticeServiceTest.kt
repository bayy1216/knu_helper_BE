package com.reditus.knuhelper.service

import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.infrastucture.notice.NoticeRepository
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.infrastucture.user.UserRepository
import com.reditus.knuhelper.domain.user.UserSubscribedSite
import com.reditus.knuhelper.infrastucture.user.UserSubscribedSiteRepository
import com.reditus.knuhelper.domain.notice.NoticeService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class NoticeServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val noticeRepository: NoticeRepository,
    private val userSubscribedSiteRepository: UserSubscribedSiteRepository,
    private val noticeService: NoticeService,
){
    @AfterEach
    fun clean(){
        userRepository.deleteAll()
        noticeRepository.deleteAll()
        userSubscribedSiteRepository.deleteAll()
    }
    @Test
    fun 공지조회가_적상적으로_작동한다(){
        //given
        val user1 = User.fixture(name = "사용자1")
        val user2 = User.fixture(name = "사용자2")
        userRepository.saveAll(listOf(user1, user2))

        for(i in 1..30){
            val notice = Notice.fixture("[신규공지]공지사항$i", site = Site.KNU, url = "dsa$i")
            noticeRepository.save(notice)
        }
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


        //when
        val noticesPage0 = noticeService.getNotice(user1.id!!, PageRequest.of(0,20), "", null)
        val noticesPage1 = noticeService.getNotice(user1.id!!, PageRequest.of(1,20), "", null)
        val noticesKnuPage0 = noticeService.getNotice(user1.id!!, PageRequest.of(0,20), "", Site.KNU.koreaName)
        val noticesAppchemPage0 = noticeService.getNotice(user1.id!!, PageRequest.of(0,20), "", Site.APPCHEM.koreaName)

        //then
        println(noticesPage0)
        println()
        println(noticesPage1)
        println()
        println(noticesKnuPage0)
        println()
        println(noticesAppchemPage0)
        Assertions.assertThat(noticesPage0.data.size).isEqualTo(20)
        Assertions.assertThat(noticesPage0.hasNext).isTrue
        Assertions.assertThat(noticesPage1.data.size).isEqualTo(13)
        Assertions.assertThat(noticesPage1.hasNext).isFalse

        Assertions.assertThat(noticesKnuPage0.data.size).isEqualTo(20)
        Assertions.assertThat(noticesKnuPage0.hasNext).isTrue
        Assertions.assertThat(noticesAppchemPage0.data.size).isEqualTo(2)
        Assertions.assertThat(noticesAppchemPage0.hasNext).isFalse
    }
}