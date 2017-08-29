package com.herolynx.bouncer.test.sql

import com.herolynx.bouncer.db.sql.JdbcConfig
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.orm.jpa.vendor.Database
import javax.sql.DataSource
import org.springframework.context.annotation.Scope

@TestConfiguration
@Configuration
class InMemorySqlDbConfig extends JdbcConfig {

    private static TEST_DB_NAME = "inmemory-db"

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean
    DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
        return builder
                .setName(TEST_DB_NAME)
                .setType(EmbeddedDatabaseType.H2)
                .build()
    }

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            JpaVendorAdapter jpaVendorAdapter,
            DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean()
        lef.dataSource = dataSource
        lef.jpaVendorAdapter = jpaVendorAdapter
        lef.setPackagesToScan(JdbcConfig.BASE_PACKAGE)
        return lef
    }

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setShowSql(false)
        hibernateJpaVendorAdapter.setGenerateDdl(true)
        hibernateJpaVendorAdapter.setDatabase(Database.H2)
        return hibernateJpaVendorAdapter
    }

}
