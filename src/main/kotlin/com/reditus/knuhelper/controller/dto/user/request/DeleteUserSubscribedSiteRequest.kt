package com.reditus.knuhelper.controller.dto.user.request

import com.reditus.knuhelper.domain.notice.Site

data class DeleteUserSubscribedSiteRequest(
    val site: String,
)
