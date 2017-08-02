package com.herolynx.users.services

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@org.springframework.context.annotation.Configuration
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "db")
class DbConfig {

    var username: String? = null
    var password: String? = null
}
