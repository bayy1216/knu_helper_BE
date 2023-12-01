package com.reditus.knuhelper.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserSubscribedSiteRepository : JpaRepository<UserSubscribedSite, Long> {
    fun findByUserId(userId: Long): List<UserSubscribedSite>
}