package com.reditus.knuhelper.controller

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.core.annotation.TokenUserRole
import com.reditus.knuhelper.domain.user.UserRole
import com.reditus.knuhelper.dto.common.PagingResponse
import com.reditus.knuhelper.dto.notice.request.CreateNoticeRequest
import com.reditus.knuhelper.dto.notice.response.NoticeDto
import com.reditus.knuhelper.service.NoticeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService,
) {
    @GetMapping
    fun getNotice(@TokenUserId userId: Long, @RequestParam("page") page:Int, @RequestParam("size", required = false) size :Int = 20) : PagingResponse<NoticeDto> =
        noticeService.getNotice(userId, page, size)

    @PostMapping
    fun createNotice(@TokenUserRole role: UserRole, @RequestBody request: CreateNoticeRequest) : Long{
        if (role != UserRole.ROLE_ADMIN) {
            throw IllegalArgumentException("권한이 없습니다.")
        }
        return noticeService.createNotice(request)
    }

    @PostMapping("/insert")
    fun insertNotice(@TokenUserRole role: UserRole, @RequestBody request: CreateNoticeRequest) : ResponseEntity<Any>{
        if (role != UserRole.ROLE_ADMIN) {
            throw IllegalArgumentException("권한이 없습니다.")
        }
        return noticeService.insertNotice(request)
    }


    @DeleteMapping
    fun deleteNotice(@TokenUserRole role: UserRole, @RequestParam("noticeId") noticeId: Long) : ResponseEntity<Any>{
        if (role != UserRole.ROLE_ADMIN) {
            throw IllegalArgumentException("권한이 없습니다.")
        }
        return noticeService.deleteNotice(noticeId)
    }
}