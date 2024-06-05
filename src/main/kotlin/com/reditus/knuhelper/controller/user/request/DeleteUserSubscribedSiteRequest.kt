package com.reditus.knuhelper.controller.user.request

import com.reditus.knuhelper.domain.notice.Site

data class DeleteUserSubscribedSiteRequest(
    val site: String,
)
