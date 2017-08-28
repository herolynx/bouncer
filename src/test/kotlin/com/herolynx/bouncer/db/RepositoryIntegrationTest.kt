package com.herolynx.bouncer.db

import com.herolynx.bouncer.test.sql.InMemoryDbTest
import com.herolynx.bouncer.test.sql.InMemorySqlDbConfig
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import javax.sql.DataSource

@RunWith(SpringJUnit4ClassRunner::class)
@EnableAutoConfiguration
@ContextConfiguration(classes = arrayOf(
        InMemorySqlDbConfig::class
))
class RepositoryIntegrationTest : InMemoryDbTest {

    @Autowired
    override var dataSource: DataSource?? = null

    @Before
    fun setUp() {
        cleanDb()
    }

    @Test
    fun shouldTest1() {

    }

    @Test
    fun shouldTest2() {

    }


}