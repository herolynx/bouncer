package com.herolynx.users

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "db")
class DbConfig {

    var username: String? = null
    var password: String? = null
}
