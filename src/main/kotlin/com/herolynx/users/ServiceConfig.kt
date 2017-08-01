package com.herolynx.users

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "ping")
class ServiceConfig {

    var message: String? = null

}
