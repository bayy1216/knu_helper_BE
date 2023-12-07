package com.reditus.knuhelper.dto.user.request

import com.reditus.knuhelper.domain.notice.Site

data class DeleteUserSubscribedSiteRequest(
    val site: Site,
)
