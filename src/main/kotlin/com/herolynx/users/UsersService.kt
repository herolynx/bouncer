package com.herolynx.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

import java.util.UUID

@RestController
class UsersService {

    private val id = UUID.randomUUID()
    @Autowired
    private var config: ServiceConfig? = null
    @Autowired
    private var dbConfig: DbConfig? = null
    private var healthy = true
    private var ready = true

    @GetMapping("/hello")
    fun ping(): String {
        ready()
        health()
        return String.format("[%s] %s", id, config?.message)
    }

    @GetMapping("/secrets")
    fun secrets(): String {
        ready()
        health()
        return String.format("[%s] Can I tell you a secret? Username: %s, password: %s", id, dbConfig?.username, dbConfig?.password)
    }

    @GetMapping("/probe/health")
    fun health(): Boolean {
        return checkStatus(healthy, String.format("[%s] I'm sick!", id))
    }

    @PostMapping("/probe/health")
    fun changeHealth(): String {
        healthy = !healthy
        return String.format("%s is now: %s", id, healthy)
    }

    @GetMapping("/probe/ready")
    fun ready(): Boolean {
        return checkStatus(ready, String.format("[%s] Dude, I'm busy - leave me alone!", id))
    }

    @PostMapping("/probe/ready")
    fun changeReady(): String {
        ready = !ready
        return String.format("%s is now: %s", id, ready)
    }

    private fun checkStatus(s: Boolean, errMsg: String): Boolean {
        if (!s) {
            throw RuntimeException(errMsg)
        }
        return s
    }

}
