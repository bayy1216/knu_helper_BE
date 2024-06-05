package com.reditus.knuhelper.infrastucture.notice

import com.querydsl.jpa.impl.JPAQueryFactory
import com.reditus.knuhelper.domain.notice.Notice
import com.reditus.knuhelper.domain.notice.QNotice.notice
import com.reditus.knuhelper.domain.notice.Site
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.time.LocalDateTime

interface NoticeRepository : JpaRepository<Notice, Long>, NoticeRepositoryCustom {
    fun findAllBySiteInAndTitleContainingOrderByDateDescViewsAsc(sites: List<Site>, title: String, pageable: Pageable): Page<Notice>
    fun findAllBySiteInOrderByDateDescViewsAsc(sites: List<Site>, pageable: Pageable): Page<Notice>


    fun findAllByCreatedAtAfter(createdAt: LocalDateTime): List<Notice>

    fun findAllByDate(date: LocalDate): List<Notice>

    fun findByUrl(url: String): Notice?
}

interface NoticeRepositoryCustom {
    fun findAllByPagination(sites:List<Site>, pageable: Pageable, title:String?): Page<Notice>
}

class NoticeRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : NoticeRepositoryCustom {

    override fun findAllByPagination(sites: List<Site>, pageable: Pageable, title: String?): Page<Notice> {
        val notices =  queryFactory
            .selectFrom(notice)
            .where(notice.site.`in`(sites), title?.let { notice.title.contains(title) })
            .orderBy(notice.date.desc(), notice.views.asc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
        val count = queryFactory
            .select(notice.count())
            .from(notice)
            .where(notice.site.`in`(sites), title?.let { notice.title.contains(title) })
            .fetchOne() ?: 0
        return PageImpl(notices, pageable, count)
    }
}