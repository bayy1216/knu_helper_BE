package com.reditus.knuhelper.controller.user

import com.reditus.knuhelper.global.api.interceptor.TokenUserId
import com.reditus.knuhelper.controller.user.request.DeleteUserSubscribedSiteRequest
import com.reditus.knuhelper.controller.user.request.UserSubscribeSiteRequest
import com.reditus.knuhelper.controller.user.response.UserSubscribedSiteResponse
import com.reditus.knuhelper.domain.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/favorite-site")
    @ResponseStatus(HttpStatus.OK)
    fun getUserFavoriteSite(@TokenUserId userId: Long): UserSubscribedSiteResponse =
        userService.getUserFavoriteSite(userId)

    @PostMapping("/favorite-site")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUserFavoriteSite(@TokenUserId userId: Long, @RequestBody request: UserSubscribeSiteRequest) =
        userService.addUserFavoriteSite(userId, request)

    @PutMapping("/favorite-site")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUserFavoriteSite(@TokenUserId userId: Long, @RequestBody request: UserSubscribeSiteRequest) =
        userService.updateUserFavoriteSite(userId, request)

    @DeleteMapping("/favorite-site")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserFavoriteSite(@TokenUserId userId: Long, @RequestBody request: DeleteUserSubscribedSiteRequest) =
        userService.deleteUserFavoriteSite(userId, request)
}