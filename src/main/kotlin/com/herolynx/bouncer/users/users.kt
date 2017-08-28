package com.herolynx.bouncer.users

import javax.persistence.*

typealias EMail = String
typealias Password = String

@Entity
data class UserInfo(
        val firstName: String,
        val lastName: String,
        @Id
        val eMail: EMail
)

@Entity
data class UserCredentials(
        val password: Password,
        @Id
        val eMail: EMail
)

@Entity
data class UserSession(
        @Id
        val id: String
)