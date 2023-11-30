package com.reditus.knuhelper.dto.user.response

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserFavoriteSite

data class UserFavoriteSiteResponse(
    val data: List<UserFavoriteSiteDto>
)

data class UserFavoriteSiteDto(
    val site: Site,
    val color: String,
    val isAlarm: Boolean
)

fun UserFavoriteSite.toDto() = UserFavoriteSiteDto(
    site = this.site,
    color = this.color,
    isAlarm = this.isAlarm
)
