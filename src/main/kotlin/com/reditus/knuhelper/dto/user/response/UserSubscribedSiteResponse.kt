package com.reditus.knuhelper.dto.user.response

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserSubscribedSite

data class UserSubscribedSiteResponse(
    val data: List<UserSubscribedSiteDto>
)

data class UserSubscribedSiteDto(
    val site: Site,
    val color: String,
    val isAlarm: Boolean
)

fun UserSubscribedSite.toDto() = UserSubscribedSiteDto(
    site = this.site,
    color = this.color,
    isAlarm = this.isAlarm
)
