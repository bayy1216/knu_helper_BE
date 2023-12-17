package com.reditus.knuhelper.dto.user.request

import com.reditus.knuhelper.domain.notice.Site

data class UserSubscribeSiteRequest(
    val site: String,
    val color: String,
    val alarm: Boolean
)