package com.reditus.knumate.controller

import com.reditus.knumate.service.NoticeService
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