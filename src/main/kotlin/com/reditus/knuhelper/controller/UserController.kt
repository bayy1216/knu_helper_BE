package com.reditus.knuhelper.controller

import com.reditus.knuhelper.core.annotation.TokenUserId
import com.reditus.knuhelper.controller.dto.user.request.DeleteUserSubscribedSiteRequest
import com.reditus.knuhelper.controller.dto.user.request.UserSubscribeSiteRequest
import com.reditus.knuhelper.controller.dto.user.response.UserSubscribedSiteResponse
import com.reditus.knuhelper.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/favorite-site")
    fun getUserFavoriteSite(@TokenUserId userId: Long): UserSubscribedSiteResponse =
        userService.getUserFavoriteSite(userId)

    @PostMapping("/favorite-site")
    fun addUserFavoriteSite(@TokenUserId userId: Long,@RequestBody request: UserSubscribeSiteRequest): ResponseEntity<Any> =
        userService.addUserFavoriteSite(userId, request)

    @PutMapping("/favorite-site")
    fun updateUserFavoriteSite(@TokenUserId userId: Long,@RequestBody request: UserSubscribeSiteRequest): ResponseEntity<Any> =
        userService.updateUserFavoriteSite(userId, request)

    @DeleteMapping("/favorite-site")
    fun deleteUserFavoriteSite(@TokenUserId userId: Long,@RequestBody request: DeleteUserSubscribedSiteRequest): ResponseEntity<Any> =
        userService.deleteUserFavoriteSite(userId, request)
}