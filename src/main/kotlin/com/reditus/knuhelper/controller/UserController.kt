package com.reditus.knuhelper.controller

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.dto.user.request.UserSubscribeSiteRequest
import com.reditus.knuhelper.dto.user.response.UserSubscribedSiteResponse
import com.reditus.knuhelper.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/favorite-site")
    fun getUserFavoriteSite(@TokenUserId userId: Long): UserSubscribedSiteResponse =
        userService.getUserFavoriteSite(userId)

    @PostMapping("/favorite-site")
    fun addUserFavoriteSite(@TokenUserId userId: Long, request: UserSubscribeSiteRequest): ResponseEntity<Any> =
        userService.addUserFavoriteSite(userId, request)

    @PutMapping("/favorite-site")
    fun updateUserFavoriteSite(@TokenUserId userId: Long, request: UserSubscribeSiteRequest): ResponseEntity<Any> =
        userService.updateUserFavoriteSite(userId, request)
}