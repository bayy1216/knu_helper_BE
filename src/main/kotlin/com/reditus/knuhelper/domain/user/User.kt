package com.reditus.knuhelper.domain.user

import com.reditus.knuhelper.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class User(
    var name: String,

    @Enumerated(EnumType.STRING)
    val userRole: UserRole,

    @OneToMany(mappedBy = "user")
    val subscribedSite: MutableList<UserSubscribedSite> = mutableListOf(),


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {
}