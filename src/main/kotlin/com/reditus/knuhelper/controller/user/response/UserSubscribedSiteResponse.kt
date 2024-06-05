package com.reditus.knuhelper.controller.user.response

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserSubscribedSite

data class UserSubscribedSiteResponse(
    val data: List<UserSubscribedSiteDto>
)

data class UserSubscribedSiteDto(
    val site: String,
    val color: String,
    val isAlarm: Boolean
)

fun UserSubscribedSite.toDto() = UserSubscribedSiteDto(
    site = this.site.koreaName,
    color = this.color,
    isAlarm = this.isAlarm
)

fun List<UserSubscribedSite>.toDto() = this.map(UserSubscribedSite::toDto)