package com.herolynx.bouncer.users

import com.herolynx.bouncer.db.Repository
import com.herolynx.bouncer.monitoring.error
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
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
    fun getUsers(
            @RequestParam(required = false, defaultValue = "0") offset: Long = 0,
            @RequestParam(required = false, defaultValue = "50") limit: Long = 50

    ): List<UserInfo> {
        return usersRepo.query { q ->
            q.select(QUserInfo.userInfo)
                    .from(QUserInfo.userInfo)
                    .offset(offset)
                    .limit(limit)
                    .fetch()
        }
                .onFailure { ex -> error("Couldn't get users", ex) }
                .getOrElse { listOf() }
    }


}
