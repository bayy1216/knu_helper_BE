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

    var fcmToken : String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {

    companion object{
        fun fixture(
            name: String = "사용자이름",
            userRole: UserRole = UserRole.ROLE_ADMIN,
            fcmToken: String = "사용자 fcm token",
            id: Long? = null,
        ) : User{
            return User(
                name = name,
                userRole = userRole,
                subscribedSite =  mutableListOf(),
                fcmToken = fcmToken,
                id = id
            )
        }
    }
}