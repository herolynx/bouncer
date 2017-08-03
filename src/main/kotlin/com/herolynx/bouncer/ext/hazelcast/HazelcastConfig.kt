package com.herolynx.bouncer.ext.hazelcast

import com.hazelcast.client.HazelcastClient
import com.herolynx.bouncer.db.DataService
import com.herolynx.bouncer.db.BasicDataService
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter

@Configuration
class HazelcastConfig {

    private val settings: HazelcastSettings

    @org.springframework.beans.factory.annotation.Autowired
    constructor(settings: HazelcastSettings) {
        this.settings = settings
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    fun hazelcastInstance(): com.hazelcast.core.HazelcastInstance = HazelcastClient.newHazelcastClient(settings.hazelcastConfig())

    @Bean
    fun hazelcastKeyValueAdapter(instance: com.hazelcast.core.HazelcastInstance): HazelcastKeyValueAdapter = HazelcastKeyValueAdapter(instance)

    @Bean
    fun keyValueTemplate(adapter: HazelcastKeyValueAdapter): org.springframework.data.keyvalue.core.KeyValueOperations = org.springframework.data.keyvalue.core.KeyValueTemplate(adapter)

    @Bean
    fun <T> hazelcastDataService(ops: org.springframework.data.keyvalue.core.KeyValueOperations): DataService<T> = BasicDataService(ops)

}
