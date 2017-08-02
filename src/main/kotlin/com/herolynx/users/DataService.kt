package com.herolynx.users

import org.funktionale.tries.Try

interface DataService<T> {

    fun save(t: T): Try<T>

    fun delete(t: T): Try<T>

    fun findAll(type: Class<T>): Try<List<T>>

}