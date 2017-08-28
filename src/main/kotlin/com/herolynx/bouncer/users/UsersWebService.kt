package com.herolynx.bouncer.users

import com.herolynx.bouncer.db.RepositoryFactory
import com.herolynx.bouncer.monitoring.error
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = "/users")
class UsersWebService {

    private val repoFactory: RepositoryFactory

    @Autowired
    constructor(repoFactory: RepositoryFactory) {
        this.repoFactory = repoFactory
    }

    @PostMapping
    fun createUser(): UserInfo? {
        val u = UserInfo(firstName = "mike", lastName = "wrona", eMail = UUID.randomUUID().toString())
        return repoFactory.transactional { r ->
            r.save(u).get()
        }
                .onFailure { ex -> error("Couldn't save user", ex) }
                .get()
    }

    @GetMapping
    fun getUsers(
            @RequestParam(required = false, defaultValue = "0") offset: Long = 0,
            @RequestParam(required = false, defaultValue = "50") limit: Long = 50

    ): List<UserInfo> {
        return repoFactory.use { r ->
            r.query { q ->
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

}
