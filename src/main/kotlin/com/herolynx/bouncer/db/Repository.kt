package com.herolynx.bouncer.db

import org.funktionale.option.Option
import org.funktionale.tries.Try

interface Repository<T> {

    /**
     * Save entity
     *
     * @param entity entity to be saved
     * @return saved entity
     */
    fun save(entity: T): Try<T>

    /**
     * Delete entity
     *
     * @param clazz entity class
     * @param id    entity's ID
     * @param <Id>  type of ID
     * @return deleted entity if found
    </Id> */
    fun <Id> delete(clazz: Class<T>, id: Id): Try<Option<T>>

    /**
     * Find entity
     *
     * @param clazz entity class
     * @param id    entity's ID
     * @param <Id>  type of ID
     * @return found entity
    </Id> */
    fun <Id> find(clazz: Class<T>, id: Id): Try<Option<T>>

}