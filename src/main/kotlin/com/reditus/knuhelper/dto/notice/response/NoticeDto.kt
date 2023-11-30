package com.reditus.knuhelper.dto.notice.response

import com.reditus.knuhelper.domain.notice.Notice
import java.time.LocalDate

data class NoticeDto(
    val id: Long,
    val title: String,
    val content: String,
    val site: String,
    val date: LocalDate,
    val url: String,
)

fun Notice.toDto() = NoticeDto(
    id = this.id!!,
    title = this.title,
    content = this.content,
    site = this.site.name,
    date = this.createdDate!!.toLocalDate(),
    url = this.url,
)