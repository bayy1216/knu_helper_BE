package com.reditus.knuhelper.controller.notice.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.reditus.knuhelper.domain.notice.Notice
import java.time.LocalDate

data class NoticeDto(
    val id: Long,
    val title: String,
    val site: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val date: LocalDate,
    val url: String,
    val views: Int,
    val type: String
)

fun Notice.toDto() = NoticeDto(
    id = this.id!!,
    title = this.title,
    site = this.site.koreaName,
    date = this.date,
    url = this.url,
    views = this.views,
    type = this.type
)

fun List<Notice>.toDto() = this.map(Notice::toDto)