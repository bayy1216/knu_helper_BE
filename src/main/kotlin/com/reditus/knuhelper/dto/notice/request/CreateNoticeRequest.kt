package com.reditus.knuhelper.dto.notice.request

import com.reditus.knuhelper.domain.notice.Site
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class CreateNoticeRequest(
    val title: String,
    val content: String,
    val type: String,
    val url: String,
    val views: Int,
    val site: Site,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
)
