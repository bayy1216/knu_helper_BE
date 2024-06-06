package com.reditus.knuhelper.controller.notice.request

import com.reditus.knuhelper.domain.notice.NoticeCommand
import com.reditus.knuhelper.domain.notice.Site
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class CreateNoticeRequest(
    val title: String,
    val type: String,
    val url: String,
    val views: Int,
    val site: Site,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate,
){
    fun toCommand() = NoticeCommand.Create(
        title = title,
        type = type,
        url = url,
        views = views,
        site = site,
        date = date
    )

    fun toUpdateCommand() = NoticeCommand.Update(
        title = title,
        views = views
    )
}
