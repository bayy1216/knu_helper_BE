package com.reditus.knuhelper.infrastucture.user

import com.querydsl.jpa.impl.JPAQueryFactory
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.QUser.user
import com.reditus.knuhelper.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.subscribedSite WHERE u.id = :userId")
    fun findByIdWithSubscribedSites(@Param("userId") userId: Long): User?

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.subscribedSite")
    fun findAllWithSubscribedSites(): List<User>

    fun findByNickname(nickname: String): User?
}

interface UserRepositoryCustom {
    fun findAllWithIsAlarmSubscribedSites(sites: Set<Site>): List<User>
}

class UserRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : UserRepositoryCustom {
    override fun findAllWithIsAlarmSubscribedSites(sites: Set<Site>): List<User> {
        return queryFactory
            .selectFrom(user)
            .leftJoin(user.subscribedSite).fetchJoin()
            .where(user.subscribedSite.any().site.`in`(sites), user.subscribedSite.any().isAlarm.eq(true))
            .fetch()
    }

}