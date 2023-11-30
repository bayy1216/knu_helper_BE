package com.reditus.knuhelper.dto.user.request

import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserFavoriteSite

data class AddUserFavoriteSiteRequest(
    val site: Site,
    val color: String,
    val isAlarm: Boolean
)