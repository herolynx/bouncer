package com.herolynx.users.ext.hazelcast

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import com.hazelcast.config.NetworkConfig
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.herolynx.users.services.db.DataService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter
import org.springframework.data.keyvalue.core.KeyValueOperations
import org.springframework.data.keyvalue.core.KeyValueTemplate

@Configuration
class HazelcastSettings {

    fun hazelcastConfig(): ClientConfig {
        val clientConfig = ClientConfig()
        clientConfig.networkConfig
                .addAddress("192.168.99.100:31611")
                .setConnectionAttemptLimit(5)
                .setConnectionAttemptPeriod(500)
                .setConnectionTimeout(500)
        clientConfig.groupConfig.name = "herolynx"
        clientConfig.groupConfig.password = "pass"
        return clientConfig
    }

}
