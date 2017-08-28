package com.herolynx.bouncer.db.sql

import com.herolynx.bouncer.db.ReadRepository
import com.herolynx.bouncer.db.RepositoryFactory
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManager

@Configuration
@EntityScan(basePackages = arrayOf("com.herolynx.bouncer"))
class JdbcConfig {

    @Bean
    fun repository(em: EntityManager): ReadRepository = BasicReadRepository(em)

    @Bean
    fun repositoryFactory(tm: PlatformTransactionManager, em: EntityManager): RepositoryFactory = SqlRepositoryFactory(tm, em)

    companion object {
        val BASE_PACKAGE = "com.herolynx.bouncer"
    }

}