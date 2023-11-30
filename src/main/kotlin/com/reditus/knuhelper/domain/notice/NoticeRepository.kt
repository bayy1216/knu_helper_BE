package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.domain.user.UserSubscribedSite
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import java.awt.print.Pageable

interface NoticeRepository : JpaRepository<Notice, Long> {
    fun findAllBySitesInOrderByCreatedAtDesc(sites: List<Site>, pageRequest: PageRequest): Page<Notice>
}