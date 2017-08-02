package com.herolynx.users.ext.hazelcast

import com.herolynx.users.DataService
import org.funktionale.tries.Try
import org.springframework.data.keyvalue.core.KeyValueOperations

class HazelcastDataService<T> : DataService<T>  {

    private val template: KeyValueOperations

    constructor(template: KeyValueOperations) {
        this.template = template
    }

    override fun save(t: T): Try<T> = Try { template.insert(t) }

    override fun delete(t: T): Try<T> {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(type: Class<T>): Try<List<T>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}