package com.reditus.knuhelper.controller

import com.reditus.knuhelper.service.NoticeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService,
) {
    @GetMapping("/test")
    fun test(): String = "Hello, World!"
}