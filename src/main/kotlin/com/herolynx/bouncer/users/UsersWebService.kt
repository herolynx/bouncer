package com.herolynx.bouncer.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = "/users")
class UsersWebService {

    @PostMapping
    fun createUser(): User? {
        val u = User(firstName = "mike", lastName = "wrona", eMail = UUID.randomUUID().toString())
        return u
    }

    @GetMapping
    fun getUsers(): List<User> {
        return listOf()
    }


}
