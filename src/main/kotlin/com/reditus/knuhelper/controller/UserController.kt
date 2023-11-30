package com.reditus.knuhelper.controller

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.dto.user.response.UserFavoriteSiteResponse
import com.reditus.knuhelper.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/favorite-site")
    fun getUserFavoriteSite(@TokenUserId userId: Long): UserFavoriteSiteResponse =
        userService.getUserFavoriteSite(userId)


}