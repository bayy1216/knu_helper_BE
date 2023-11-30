package com.reditus.knuhelper.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.subscribedSite WHERE u.id = :userId")
    fun findByIdWithSubscribedSites(@Param("userId") userId: Long): User?

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.subscribedSite")
    fun findAllWithSubscribedSites(): List<User>
}