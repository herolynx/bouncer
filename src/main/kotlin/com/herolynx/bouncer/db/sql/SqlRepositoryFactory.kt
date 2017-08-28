package com.herolynx.bouncer.db.sql

import com.herolynx.bouncer.db.ReadRepository
import com.herolynx.bouncer.db.RepositoryFactory
import com.herolynx.bouncer.db.WriteRepository
import org.funktionale.tries.Try
import javax.persistence.EntityManager

class SqlRepositoryFactory : RepositoryFactory {

    private val em: EntityManager
    private val repo: BasicWriteRepository

    private fun <R> transaction(operation: (EntityManager) -> R): Try<R> {
        try {
            em.transaction.begin()
            val r = operation(em)
            em.transaction.commit()
            return Try.Success(r)
        } catch (e: Exception) {
            em.transaction.rollback()
            return Try.Failure(e)
        }
    }

    override fun <T> transactional(operation: (WriteRepository) -> T): Try<T> = transaction { em -> operation(repo) }

    override fun repository(): ReadRepository = repo

    constructor(em: EntityManager) {
        this.em = em
        repo = BasicWriteRepository(em)
    }
}
