package com.reditus.knuhelper.core.postconstructor

import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.domain.user.UserRole
import jakarta.annotation.PostConstruct
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