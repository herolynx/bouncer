package com.herolynx.bouncer.db

import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import javax.persistence.EntityManagerFactory;

@Configuration
@EntityScan(basePackages = arrayOf("com.herolynx.bouncer"))
class JdbcConfig {

    @Autowired
    private var entityManagerFactory: EntityManagerFactory? = null

    fun <T> repository(): Repository<T> = SqlEntityRepository(entityManagerFactory!!)

}