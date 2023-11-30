package com.reditus.knuhelper.core.postconstructor

import com.reditus.knuhelper.domain.user.User
import com.reditus.knuhelper.domain.user.UserRepository
import com.reditus.knuhelper.domain.user.UserRole
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager


class MakeUser (
    private val userRepository: UserRepository,
    private val em: EntityManager,
){
    @PostConstruct
    fun init(){
        val user = User(
            name = "test",
            userRole = UserRole.ROLE_ADMIN,
        )
        userRepository.save(user)
    }
}