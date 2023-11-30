package com.reditus.knuhelper.domain.user

import com.reditus.knuhelper.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    var name: String,

    @Enumerated(EnumType.STRING)
    val userRole: UserRole,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val subscribedSite: MutableList<UserSubscribedSite> = mutableListOf(),


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {
}