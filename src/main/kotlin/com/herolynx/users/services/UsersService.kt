package com.herolynx.users.services

import com.herolynx.users.User
import com.herolynx.users.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/users")
class UsersService {

//    private val usersRepo: UserRepository? = null
//
////    @Autowired
////    constructor(usersRepo: UserRepository) {
////        this.usersRepo = usersRepo
////    }
//
//    @PostMapping
//    fun createUser(): User? {
//        val u = User(firstName = "mike", lastName = "wrona", eMail = "m-wrona@gmail.com")
//        return usersRepo?.save(u)
//    }
//
//    @GetMapping
//    fun getUsers(): List<User>? {
//        return usersRepo
//                ?.findAll()
//                ?.toList()
//    }


}
