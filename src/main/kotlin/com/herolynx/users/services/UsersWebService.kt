package com.herolynx.users.services

import com.herolynx.users.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/users")
class UsersWebService {

    private val usersRepo: DataService<User>

    @Autowired
    constructor(usersRepo: DataService<User>) {
        this.usersRepo = usersRepo
    }

    @PostMapping
    fun createUser(): User? {
        val u = User(firstName = "mike", lastName = "wrona", eMail = "m-wrona@gmail.com")
        return usersRepo.create(u.eMail, u)
                .map { c -> u }
                .get()
    }

    @GetMapping
    fun getUsers(): List<User> {
        return usersRepo
                .findAll(User::class.java)
                .getOrElse { listOf() }
    }


}
