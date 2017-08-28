package com.herolynx.bouncer.test.sql

import com.herolynx.bouncer.db.RepositoryFactory
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.sql.DataSource

interface DataAccessTest<T> : InMemoryDbTest {

    override var dataSource: DataSource?
    var repoFactory: RepositoryFactory?

    @Before
    fun setUp() {
        cleanDb()
    }

    fun entityClass(): Class<T>

    fun idOf(t: T): Any

    fun v1(): T

    fun v2(v1: T): T

    fun givenV1IsCreated(): T {
        //GIVEN sample entity
        val v1 = v1()
        //AND entity is created
        return repoFactory!!.transactional { r -> r.save(v1).get() }.get()
    }

    @Test
    fun shouldCreateEntity() {
        //GIVEN sample entity
        val v1 = v1()

        //WHEN creating
        val dbV1 = repoFactory!!.transactional { r -> r.save(v1).get() }.get()
        val q1 = repoFactory!!.repository().find(entityClass(), idOf(v1)).get()

        //THEN entity is created
        Assert.assertThat("DB entity is different than input data", dbV1, Matchers.`is`(v1))
        Assert.assertTrue("Entity has not been found", q1.isDefined())
        Assert.assertThat("Found entity is different than input data", q1.get(), Matchers.`is`(v1))
    }

    @Test
    fun shouldUpdateEntity() {
        val v1 = givenV1IsCreated()
        //AND entity has been changed
        val v2 = v2(v1)

        //WHEN updating
        val dbV2 = repoFactory!!.transactional { r -> r.save(v2).get() }.get()
        val q2 = repoFactory!!.repository().find(entityClass(), idOf(v1)).get()

        //THEN entity is updated
        Assert.assertThat("DB entity is different than input data", dbV2, Matchers.`is`(v2))
        Assert.assertTrue("Entity has not been found", q2.isDefined())
        Assert.assertThat("Found entity is different than input data", q2.get(), Matchers.`is`(v2))
    }

    @Test
    fun shouldDeleteEntity() {
        val v1 = givenV1IsCreated()

        //WHEN deleting
        repoFactory!!.transactional { r -> r.delete(entityClass(), idOf(v1)).get() }.get()

        //THEN entity is deleted
        val dbV2 = repoFactory!!.repository().find(entityClass(), idOf(v1)).get()
        Assert.assertFalse("Entity has not been deleted", dbV2.isDefined())
    }

}