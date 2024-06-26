package com.reditus.knuhelper.infrastucture.user

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserSubscribedSite
import org.springframework.data.jpa.repository.JpaRepository

interface UserSubscribedSiteRepository : JpaRepository<UserSubscribedSite, Long> {
    fun findByUserId(userId: Long): List<UserSubscribedSite>

    fun findByUserIdAndSite(userId: Long, site: Site): UserSubscribedSite?

    fun existsByUserIdAndSite(userId: Long, site: Site): Boolean
}