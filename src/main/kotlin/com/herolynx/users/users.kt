package com.herolynx.users

import java.io.Serializable

typealias EMail = String
typealias Password = String

data class User(val firstName: String, val lastName: String, val eMail: EMail) : Serializable