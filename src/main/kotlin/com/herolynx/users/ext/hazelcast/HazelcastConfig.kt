package com.herolynx.users.ext.hazelcast

import com.hazelcast.client.config.ClientConfig
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.herolynx.users.services.db.DataService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter
import org.springframework.data.keyvalue.core.KeyValueOperations
import org.springframework.data.keyvalue.core.KeyValueTemplate

@Configuration
class HazelcastConfig {

    private fun hazelcastConfig(): ClientConfig {
        val clientConfig = ClientConfig()
        clientConfig.networkConfig.addAddress("http://192.168.99.100:31265")
        return clientConfig
    }

    @Bean
    fun hazelcastInstance(): HazelcastInstance = Hazelcast.newHazelcastInstance()

    @Bean
    fun hazelcastKeyValueAdapter(instance: HazelcastInstance): HazelcastKeyValueAdapter = HazelcastKeyValueAdapter(instance)

    @Bean
    fun keyValueTemplate(adapter: HazelcastKeyValueAdapter): KeyValueOperations = KeyValueTemplate(adapter)

    @Bean
    fun <T> hazelcastDataService(ops: KeyValueOperations): DataService<T> = HazelcastDataService(ops)

}
