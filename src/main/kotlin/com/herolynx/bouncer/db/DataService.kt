package com.herolynx.bouncer.db

import org.funktionale.tries.Try
import java.io.Serializable

interface DataService<T> {

    fun <K : Serializable> create(key: K, t: T): Try<K>

    fun <K : Serializable> update(key: K, t: T): Try<K>

    fun <K : Serializable> delete(key: K, type: Class<T>): Try<K>

    fun findAll(type: Class<T>): Try<List<T>>

}