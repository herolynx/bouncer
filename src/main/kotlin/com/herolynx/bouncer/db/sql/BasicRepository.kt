package com.herolynx.bouncer.db.sql

import com.herolynx.bouncer.db.ReadRepository
import com.herolynx.bouncer.db.WriteRepository
import com.querydsl.jpa.impl.JPAQuery
import org.funktionale.option.Option
import org.funktionale.option.toOption
import org.funktionale.tries.Try
import javax.persistence.EntityManager

internal class BasicWriteRepository(private val em:EntityManager) : WriteRepository, BasicReadRepository(em) {

    override fun <T> save(entity: T): Try<T> = Try { em.merge<T>(entity) }

    override fun <T, Id> delete(clazz: Class<T>, id: Id): Try<Option<T>> = Try {
        val entity = em.find(clazz, id).toOption()
        entity.map { e ->
            em.remove(e)
            em.flush()
        }
        entity
    }

}

internal open class BasicReadRepository : ReadRepository {

    private val em: EntityManager

    constructor(em: EntityManager) {
        this.em = em
    }

    override fun <T, Id> find(clazz: Class<T>, id: Id): Try<Option<T>> = Try { em.find(clazz, id).toOption() }

    override fun <T> query(query: (JPAQuery<Any>) -> T): Try<T> = Try {
        query(JPAQuery<Any>(em))
    }

    override fun close() {
        em.close()
    }
}