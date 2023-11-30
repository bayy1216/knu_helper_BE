package com.reditus.knuhelper.controller

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.dto.common.PagingResponse
import com.reditus.knuhelper.dto.notice.response.NoticeDto
import com.reditus.knuhelper.service.NoticeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService,
) {
    @GetMapping
    fun getNotice(@TokenUserId userId: Long, @RequestParam("page") page:Int, @RequestParam("size", required = false) size :Int = 20) : PagingResponse<NoticeDto> =
        noticeService.getNotice(userId, page, size)

}