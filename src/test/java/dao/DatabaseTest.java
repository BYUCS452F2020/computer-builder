package dao;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/*
    The Database has been populated so do not run any tests at this time.
 */

public class DatabaseTest {

    private Database databaseToTest = null;
    private Connection connection = null;

    @BeforeEach
    public void setUp() {
        databaseToTest = new Database();
    }

    @AfterEach
    public void tearDown() {
        databaseToTest = null;
        connection = null;
    }

    @Test
    @DisplayName("Should open connection to database when called")
    public void testOpenConnection() {
        try {
            connection = databaseToTest.openConnection();
            assertNotNull(connection);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    //For tests after this comment open the computerbuilder.sqlite file for reference if tests completely pass
    //Make sure to perform tests separately. Consecutive tests will result in an empty database.
    @Test
    @DisplayName("Should populate database with tables")
    public void testBuildTables() {
        try {
            databaseToTest.openConnection();
            databaseToTest.createTables();
            databaseToTest.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should clear all rows from tables")
    public void testClearTables() {
        try {
            databaseToTest.openConnection();
            databaseToTest.clearTables();
            databaseToTest.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should delete tables from database")
    public void testDeleteTables() {
        try {
            databaseToTest.openConnection();
            databaseToTest.deleteTables();
            databaseToTest.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }
}