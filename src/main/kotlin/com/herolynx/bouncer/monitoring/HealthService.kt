package com.herolynx.bouncer.monitoring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

import java.util.UUID

@RestController
class HealthService {

    private val id = UUID.randomUUID()
    private var healthy = true
    private var ready = true


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
