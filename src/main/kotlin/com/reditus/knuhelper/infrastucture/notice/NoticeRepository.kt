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

    fun findByUrl(url: String): Notice?
}

interface NoticeRepositoryCustom {
    fun findAllByPagination(sites:List<Site>, pageable: Pageable, title:String?): Page<Notice>

    /**
     * startTime과 endTime 사이의 공지사항을 조회한다.
     * Notice의 date에 인덱스가 걸려있으므로, startTime의 date를 사용해 성능 향상을 기대한다.
     */
    fun findAllByDateAfterBefore(startTime: LocalDateTime, endTime: LocalDateTime): List<Notice>
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

    override fun findAllByDateAfterBefore(startTime: LocalDateTime, endTime: LocalDateTime): List<Notice> {
        return queryFactory
            .selectFrom(notice)
            .where(notice.date.eq(startTime.toLocalDate())
                ,notice.createdAt!!.between(startTime, endTime)
            )
            .fetch()
    }
}