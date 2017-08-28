package com.herolynx.bouncer.users

import com.herolynx.bouncer.db.RepositoryFactory
import com.herolynx.bouncer.test.sql.DataAccessTest
import com.herolynx.bouncer.test.sql.InMemorySqlDbConfig
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
class UserInfoDataAccessTest : DataAccessTest<UserInfo> {

    @Autowired
    override var dataSource: DataSource? = null
    @Autowired
    override var repoFactory: RepositoryFactory? = null

    override fun entityClass(): Class<UserInfo> = UserInfo::class.java

    override fun idOf(t: UserInfo): Any = t.eMail

    override fun v1(): UserInfo = UserInfo(firstName = "Johny", lastName = "Bravo", eMail = "johny@bravo.com")

    override fun v2(v1: UserInfo): UserInfo = v1.copy(firstName = "Johnny")
}