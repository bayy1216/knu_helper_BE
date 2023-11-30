package com.reditus.knuhelper.controller

import com.reditus.knuhelper.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService,
){
}