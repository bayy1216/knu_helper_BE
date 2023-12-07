package com.reditus.knuhelper.domain.notice

import com.reditus.knuhelper.domain.user.UserSubscribedSite
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface NoticeRepository : JpaRepository<Notice, Long> {
    fun findAllBySiteInOrderByCreatedAtDesc(sites: List<Site>, pageable: Pageable): Page<Notice>

    fun findAllByModifiedAtAfter(modifiedAt: LocalDateTime): List<Notice>

    fun findByUrl(url: String): Notice?
}