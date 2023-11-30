package com.reditus.knuhelper.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserSubsribedSiteRepository : JpaRepository<UserSubscribedSite, Long> {
    fun findByUserId(userId: Long): List<UserSubscribedSite>
}