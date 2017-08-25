package com.herolynx.bouncer.users

import com.herolynx.bouncer.db.Repository
import org.funktionale.option.getOrElse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = "/users")
class UsersWebService {

    private val usersRepo: Repository<User>

    @Autowired
    constructor(usersRepo: Repository<User>) {
        this.usersRepo = usersRepo
    }

    @PostMapping
    fun createUser(): User? {
        val u = User(firstName = "mike", lastName = "wrona", eMail = UUID.randomUUID().toString())
        return usersRepo.save(u).get()
    }

    @GetMapping
    fun getUsers(): List<User> {
        return usersRepo.find(User::class.java, "wrona")
                .map { u -> u.map { w -> listOf(w) }.getOrElse { listOf() } }
                .getOrElse { listOf() }
    }


}
