package com.reditus.knuhelper.infrastucture.notice

import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.domain.notice.Site
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface NoticeRepository : JpaRepository<Notice, Long> {
    fun findAllBySiteInAndTitleContainingOrderByDateDescViewsAsc(sites: List<Site>, title: String, pageable: Pageable): Page<Notice>
    fun findAllBySiteInOrderByDateDescViewsAsc(sites: List<Site>, pageable: Pageable): Page<Notice>


    fun findAllByCreatedAtAfter(createdAt: LocalDateTime): List<Notice>

    fun findByUrl(url: String): Notice?
}