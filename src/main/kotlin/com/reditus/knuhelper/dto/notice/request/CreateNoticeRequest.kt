package com.reditus.knuhelper.dto.notice.request

import com.reditus.knuhelper.domain.notice.Site

data class CreateNoticeRequest(
    val title: String,
    val content: String,
    val type: String,
    val url: String,
    val views: Int,
    val site: Site,
)
