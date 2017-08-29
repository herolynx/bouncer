package com.herolynx.bouncer.users

import com.herolynx.bouncer.db.DaoTest
import com.herolynx.bouncer.test.sql.InMemorySqlDbConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import([
        InMemorySqlDbConfig.class
])
@EnableAutoConfiguration
class UserInfoDaoIntTest extends DaoTest<UserInfo> {

    @Override
    Class<UserInfo> entityClass() {
        return UserInfo.class
    }

    @Override
    Object idOf(UserInfo o) {
        return o.EMail
    }

    @Override
    UserInfo v1() {
        return new UserInfo("Johny", "Bravo", "johny@bravo.com")
    }

    @Override
    UserInfo v2(UserInfo v1) {
        return v1.copy("Johnny", v1.lastName, v1.EMail)
    }

    def "should find many users"() {
        given:
        UserInfo v1 = createV1()

        when:
        List<UserInfo> found = repositoryFactory.repository().query { q ->
            q.select(QUserInfo.userInfo)
                    .from(QUserInfo.userInfo)
                    .where(QUserInfo.userInfo.lastName.eq(v1.lastName))
                    .fetch()
        }
        .get()

        then:
        found.size() == 1
        found.get(0).equals(v1)
    }

    def "should find one user"() {
        given:
        UserInfo v1 = createV1()

        when:
        UserInfo found = repositoryFactory.repository().query { q ->
            q.select(QUserInfo.userInfo)
                    .from(QUserInfo.userInfo)
                    .where(QUserInfo.userInfo.lastName.eq(v1.lastName))
                    .fetchOne()
        }
        .get()

        then:
        found.equals(v1)
    }

}

