package com.herolynx.bouncer.db.sql

import com.herolynx.bouncer.db.ReadRepository
import com.herolynx.bouncer.db.RepositoryFactory
import com.herolynx.bouncer.db.WriteRepository
import org.funktionale.tries.Try
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

class SqlRepositoryFactory : RepositoryFactory {

    private val entityManagerFactory: EntityManagerFactory

    private fun <R> transaction(operation: (EntityManager) -> R): Try<R> {
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

    override fun <T> transactional(operation: (WriteRepository) -> T): Try<T> = transaction { em ->
        operation(BasicWriteRepository(em))
    }

    override fun repository(): ReadRepository = BasicReadRepository(entityManagerFactory.createEntityManager())

    constructor(entityManagerFactory: EntityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory
    }
}
