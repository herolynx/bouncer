package com.herolynx.users.ext.hazelcast

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import com.hazelcast.config.NetworkConfig
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.herolynx.users.services.db.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter
import org.springframework.data.keyvalue.core.KeyValueOperations
import org.springframework.data.keyvalue.core.KeyValueTemplate

@Configuration
class HazelcastConfig {

    private val settings: HazelcastSettings

    @Autowired
    constructor(settings: HazelcastSettings) {
        this.settings = settings
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    fun hazelcastInstance(): HazelcastInstance = HazelcastClient.newHazelcastClient(settings.hazelcastConfig())

    @Bean
    fun hazelcastKeyValueAdapter(instance: HazelcastInstance): HazelcastKeyValueAdapter = HazelcastKeyValueAdapter(instance)

    @Bean
    fun keyValueTemplate(adapter: HazelcastKeyValueAdapter): KeyValueOperations = KeyValueTemplate(adapter)

    @Bean
    fun <T> hazelcastDataService(ops: KeyValueOperations): DataService<T> = HazelcastDataService(ops)

}
