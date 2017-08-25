package com.herolynx.bouncer.db

import com.querydsl.core.types.Expression
import com.querydsl.jpa.impl.JPAQuery
import org.funktionale.option.Option
import org.funktionale.tries.Try

interface Repository<T> {

    fun save(entity: T): Try<T>

    fun <Id> delete(clazz: Class<T>, id: Id): Try<Option<T>>

    fun <Id> find(clazz: Class<T>, id: Id): Try<Option<T>>

    fun <R> query(query: (JPAQuery<T>) -> R): Try<R>

}