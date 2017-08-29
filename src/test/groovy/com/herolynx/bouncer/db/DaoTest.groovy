package com.herolynx.bouncer.db

import com.herolynx.bouncer.test.sql.InMemoryDbTest
import com.herolynx.bouncer.test.sql.InMemorySqlDbConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import([
        InMemorySqlDbConfig.class
])
@EnableAutoConfiguration
abstract class DaoTest<T> extends Specification implements InMemoryDbTest {

    @Autowired
    protected RepositoryFactory repositoryFactory

    abstract Class<T> entityClass()

    abstract Object idOf(T t)

    abstract T v1()

    abstract T v2(T v1)

    def setup() {
        cleanDb()
    }

    protected T createV1() {
        T v1 = v1()
        return repositoryFactory.transactional { r -> r.save(v1).get() }.get()
    }

    def "should create entity"() {
        given:
        T v1 = v1()

        when:
        T dbV1 = repositoryFactory.transactional { r -> r.save(v1).get() }.get()
        T q1 = repositoryFactory.repository().find(entityClass(), idOf(v1)).get()

        then:
        dbV1.equals(v1)
        q1.isDefined()
        q1.get().equals(v1)
    }

    def "should update entity"() {
        given:
        T v1 = createV1()
        T v2 = v2(v1)

        when:
        T dbV2 = repositoryFactory.transactional { r -> r.save(v2).get() }.get()
        T q2 = repositoryFactory.repository().find(entityClass(), idOf(v1)).get()

        then:
        dbV2.equals(v2)
        q2.isDefined()
        q2.get().equals(v2)
    }

    def "should delete entity"() {
        given:
        T v1 = createV1()

        when:
        T deletedV1 = repositoryFactory.transactional { r -> r.delete(entityClass(), idOf(v1)).get() }.get()
        T q1 = repositoryFactory.repository().find(entityClass(), idOf(v1)).get()

        then:
        deletedV1.isDefined()
        deletedV1.get().equals(v1)
        !q1.isDefined()
    }

}