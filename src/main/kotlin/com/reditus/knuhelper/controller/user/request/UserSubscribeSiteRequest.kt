package com.reditus.knuhelper.controller.user.request

import com.reditus.knuhelper.domain.notice.Site

data class UserSubscribeSiteRequest(
    val site: String,
    val color: String,
    val alarm: Boolean
)