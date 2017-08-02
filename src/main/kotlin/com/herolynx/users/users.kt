package com.herolynx.users

import org.springframework.data.keyvalue.annotation.KeySpace
import java.io.Serializable

typealias EMail = String
typealias Password = String

@KeySpace("users")
data class User(val firstName: String, val lastName: String, val eMail: EMail) : Serializable