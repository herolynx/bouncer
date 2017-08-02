package com.herolynx.users.ext.hazelcast

import com.herolynx.users.services.db.DataService
import org.funktionale.tries.Try
import org.springframework.data.keyvalue.core.KeyValueOperations
import java.io.Serializable

internal class HazelcastDataService<T> : DataService<T> {

    private val ops: KeyValueOperations

    constructor(template: KeyValueOperations) {
        this.ops = template
    }

    override fun <K : Serializable> create(key: K, t: T): Try<K> = Try {
        ops.insert(key, t)
        key
    }

    override fun <K : Serializable> update(key: K, t: T): Try<K> = Try {
        ops.update(key, t)
        key
    }

    override fun <K : Serializable> delete(key: K, type: Class<T>): Try<K> = Try {
        ops.delete(key, type)
        key
    }

    override fun findAll(type: Class<T>): Try<List<T>> = Try { ops.findAll(type).toList() }
}