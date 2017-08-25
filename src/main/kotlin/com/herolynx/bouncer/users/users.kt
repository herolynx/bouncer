package com.herolynx.bouncer.users

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

typealias EMail = String
typealias Password = String

@Entity(name = "BouncerUser")
@Table(name = "BouncerUser")
data class User(
        val firstName: String,
        val lastName: String,
        @Id
        val eMail: EMail
) : Serializable