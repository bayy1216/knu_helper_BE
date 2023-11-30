package com.reditus.knuhelper.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserFavoriteSiteRepository : JpaRepository<UserFavoriteSite, Long> {
}