package com.herolynx.bouncer.test.sql

import java.sql.Connection
import java.util.ArrayList
import javax.sql.DataSource

interface InMemoryDbTest {

    var dataSource: DataSource?

    fun cleanDb() {
        cleanDb(dataSource!!)
    }

    private fun cleanDb(dataSource: DataSource) {
        try {
            dataSource.getConnection().use({ connection ->
                connection.createStatement().use({ databaseTruncationStatement ->
                    databaseTruncationStatement.execute("SET REFERENTIAL_INTEGRITY FALSE")
                    for (tableName in getTableNames(connection)) {
                        databaseTruncationStatement.executeUpdate(
                                "TRUNCATE TABLE " + tableName
                        )
                    }
                    databaseTruncationStatement.execute("SET REFERENTIAL_INTEGRITY TRUE")
                })
            })
        } catch (ex: Exception) {
            throw RuntimeException("Couldn't clean database", ex)
        }

    }

    private fun getTableNames(connection: Connection): List<String> {
        try {
            connection.createStatement().use({ databaseTruncationStatement ->
                val tables = ArrayList<String>()
                val rs = databaseTruncationStatement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'")
                while (rs.next()) {
                    tables.add(rs.getString(1))
                }
                return tables
            })
        } catch (ex: Exception) {
            throw RuntimeException("Couldn't clean database", ex)
        }

    }

}