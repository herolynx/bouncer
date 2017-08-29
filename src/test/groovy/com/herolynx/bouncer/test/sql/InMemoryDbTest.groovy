package com.herolynx.bouncer.test.sql

import org.springframework.beans.factory.annotation.Autowired

import java.sql.Connection
import javax.sql.DataSource
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

trait InMemoryDbTest {

    @Autowired
    private DataSource dataSource

    void cleanDb() {
        cleanDb(dataSource)
    }

    void cleanDb(DataSource dataSource) {
        Connection connection
        Statement statement
        try {
            connection = dataSource.getConnection()
            statement = connection.createStatement()
            statement.execute("SET REFERENTIAL_INTEGRITY FALSE")
            getTableNames(statement).each { tableName ->
                statement.executeUpdate("TRUNCATE TABLE " + tableName)
            }
            statement.execute("SET REFERENTIAL_INTEGRITY TRUE")
        } catch (Exception ex) {
            throw RuntimeException("Couldn't clean database", ex)
        }
        finally {
            statement?.close()
            connection?.close()
        }

    }

    private List<String> getTableNames(Statement statement) throws SQLException {
        List<String> tables = []
        ResultSet rs = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'")
        while (rs.next()) {
            tables.add(rs.getString(1))
        }
        return tables
    }

}