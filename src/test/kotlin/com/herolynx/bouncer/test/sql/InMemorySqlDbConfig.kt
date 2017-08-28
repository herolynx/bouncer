package com.herolynx.bouncer.test.sql

import com.herolynx.bouncer.db.sql.JdbcConfig
import com.herolynx.bouncer.monitoring.debug
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.orm.jpa.vendor.Database
import javax.sql.DataSource

@Configuration
class InMemorySqlDbConfig : JdbcConfig() {

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean
    fun dataSource(): DataSource {
        debug("Preparing in-memory H2 test data base - db name: ${TEST_DB_NAME}")
        val builder = EmbeddedDatabaseBuilder()
        return builder
                .setName(TEST_DB_NAME)
                .setType(EmbeddedDatabaseType.H2)
                .build()
    }

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        debug("Preparing test entity manager factory - packages to scan: ${JdbcConfig.BASE_PACKAGE}")
        val lef = LocalContainerEntityManagerFactoryBean()
        lef.dataSource = dataSource()
        lef.jpaVendorAdapter = jpaVendorAdapter()
        lef.setPackagesToScan(JdbcConfig.BASE_PACKAGE)
        return lef
    }

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setShowSql(false)
        hibernateJpaVendorAdapter.setGenerateDdl(true)
        hibernateJpaVendorAdapter.setDatabase(Database.H2)
        return hibernateJpaVendorAdapter
    }

    companion object {
        private val TEST_DB_NAME = "inmemory-test"
    }

}