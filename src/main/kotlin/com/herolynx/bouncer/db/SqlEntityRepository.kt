package com.herolynx.bouncer.db

import com.herolynx.bouncer.monitoring.error
import com.querydsl.core.types.Expression
import com.querydsl.jpa.impl.JPAQuery
import org.funktionale.option.Option
import org.funktionale.option.toOption
import javax.persistence.EntityManagerFactory
import org.funktionale.tries.Try
import javax.persistence.EntityManager

internal class SqlEntityRepository<T> : Repository<T> {

    private val entityManagerFactory: EntityManagerFactory

    constructor(entityManagerFactory: EntityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory
    }

    private fun <R> execute(operation: (EntityManager) -> Try<R>): Try<R> {
        val entityManager = entityManagerFactory.createEntityManager()
        try {
            return operation(entityManager)
        } finally {
            entityManager.close()
        }
    }

    private fun <R> transactional(operation: (EntityManager) -> R): Try<R> {
        val entityManager = entityManagerFactory.createEntityManager()
        try {
            entityManager.transaction.begin()
            val r = operation(entityManager)
            entityManager.transaction.commit()
            return Try.Success(r)
        } catch (e: Exception) {
            entityManager.transaction.rollback()
            return Try.Failure(e)
        } finally {
            entityManager.close()
        }
    }

    override fun save(entity: T): Try<T> = transactional { em -> em.merge<T>(entity) }
            .onFailure { ex -> error("Couldn't save entity: $entity", ex) }

    override fun <Id> delete(clazz: Class<T>, id: Id): Try<Option<T>> = transactional { em ->
        val entity = em.find(clazz, id).toOption()
        entity.map { e ->
            em.remove(e)
            em.flush()
        }
        entity
    }
            .onFailure { ex -> error("Couldn't delete entity - class: $clazz, id: $id", ex) }

    override fun <Id> find(clazz: Class<T>, id: Id): Try<Option<T>> = execute { em ->
        Try { em.find(clazz, id).toOption() }
                .onFailure({ ex -> error("Couldn't load entity - class: $clazz, id: $id", ex) })
    }

    override fun <R> query(query: (JPAQuery<T>) -> R): Try<R> = execute { em ->
        Try {
            query(JPAQuery<T>(em))
        }
    }

}