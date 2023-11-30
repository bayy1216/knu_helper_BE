package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.domain.user.UserSubscribedSite
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository : JpaRepository<Notice, Long> {
    fun findAllBySiteInOrderByCreatedDateDesc(sites: List<Site>, pageable: Pageable): Page<Notice>
}