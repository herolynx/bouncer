package com.herolynx.bouncer.users

import com.herolynx.bouncer.db.Repository
import com.herolynx.bouncer.monitoring.error
import org.funktionale.option.getOrElse
import org.funktionale.tries.Try
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = "/users")
class UsersWebService {

    private val usersRepo: Repository<UserInfo>

    @Autowired
    constructor(usersRepo: Repository<UserInfo>) {
        this.usersRepo = usersRepo
    }

    @PostMapping
    fun createUser(): UserInfo? {
        val u = UserInfo(firstName = "mike", lastName = "wrona", eMail = UUID.randomUUID().toString())
        return usersRepo.save(u).get()
    }

    @GetMapping
    fun getUsers(): List<UserInfo> {
        return usersRepo.query { q ->
            q.select(QUserInfo.userInfo)
                    .from(QUserInfo.userInfo)
                    .fetch()
        }
                .onFailure { ex -> error("Couldn't get users", ex) }
                .getOrElse { listOf() }
    }


}
