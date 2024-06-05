package com.reditus.knuhelper.controller.notice

import com.reditus.knuhelper.controller.PagingResponse
import com.reditus.knuhelper.global.api.interceptor.TokenUserId
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.controller.notice.request.CreateNoticeRequest
import com.reditus.knuhelper.controller.notice.response.NoticeDto
import com.reditus.knuhelper.controller.notice.response.NoticeInfoResponse
import com.reditus.knuhelper.controller.notice.response.toDto
import com.reditus.knuhelper.global.api.interceptor.Admin
import com.reditus.knuhelper.domain.notice.NoticeService
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService,
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getNotice(
        @TokenUserId userId: Long,
        @RequestParam("page") page: Int,
        @RequestParam("size", required = false) size: Int = 20,
        @RequestParam("title", required = false) title: String? = null,
        @RequestParam("site", required = false) site: String? = null,
    ): PagingResponse<NoticeDto> = noticeService.getNotice(userId, PageRequest.of(page,size), title, site)


    @PostMapping
    @Admin
    @ResponseStatus(HttpStatus.CREATED)
    fun createNotice(@RequestBody request: CreateNoticeRequest): Long = noticeService.createNotice(request)


    @PostMapping("/insert")
    @Admin
    fun insertNotice(@RequestBody request: CreateNoticeRequest) = noticeService.insertNotice(request)


    @DeleteMapping("/{id}")
    @Admin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNotice(@PathVariable("id") id: Long) =
        noticeService.deleteNotice(id)

    @GetMapping("/site-info")
    @ResponseStatus(HttpStatus.OK)
    fun getSiteInfo(): NoticeInfoResponse {
        val sites = Site.entries.map { it.toDto() }
        return NoticeInfoResponse(
            siteInfoList = sites
        )
    }

}