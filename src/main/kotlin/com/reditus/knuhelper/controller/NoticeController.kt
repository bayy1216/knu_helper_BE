package com.reditus.knuhelper.controller

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.core.annotation.TokenUserRole
import com.reditus.knuhelper.domain.notice.Site
import com.reditus.knuhelper.domain.user.UserRole
import com.reditus.knuhelper.dto.common.PagingResponse
import com.reditus.knuhelper.dto.notice.request.CreateNoticeRequest
import com.reditus.knuhelper.dto.notice.response.NoticeDto
import com.reditus.knuhelper.dto.notice.response.NoticeInfoResponse
import com.reditus.knuhelper.dto.notice.response.toDto
import com.reditus.knuhelper.service.NoticeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping
    fun createNotice(@TokenUserRole role: UserRole, @RequestBody request: CreateNoticeRequest) : Long =
        noticeService.createNotice(role, request)


    @PostMapping("/insert")
    fun insertNotice(@TokenUserRole role: UserRole, @RequestBody request: CreateNoticeRequest) : ResponseEntity<Any> =
       noticeService.insertNotice(role, request)



    @DeleteMapping("/{id}")
    fun deleteNotice(@TokenUserRole role: UserRole, @PathVariable("id") id: Long) : ResponseEntity<Any>
        = noticeService.deleteNotice(role, id)

    @GetMapping("/site-info")
    fun getSiteInfo() : NoticeInfoResponse{
        val sites = Site.entries.map { it.toDto() }
        return NoticeInfoResponse(
            siteInfoList = sites
        )
    }

}