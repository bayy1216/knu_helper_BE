package com.reditus.knuhelper.global.postconstructor

import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.infrastucture.user.UserRepository
import com.reditus.knuhelper.domain.user.UserRole
import org.springframework.stereotype.Component

@Component
class MakeUser (
    private val userRepository: UserRepository,
){
    //@PostConstruct
    fun init(){
        val user = User.fixture(
            name = "test",
            userRole = UserRole.ADMIN,
            fcmToken = "test"
        )
        userRepository.save(user)
    }
}