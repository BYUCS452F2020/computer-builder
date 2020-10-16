package dao;

import models.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentDAOTest {

    private Component testComponent;
    private Component fakeComponent;
    private ComponentDAO componentDAOToTest;
    private Database database;

    @BeforeEach
    public void setUp() {
        testComponent = new Component("1234", "AMD Ryzen 7", "CPU",
                "AMD", 6, 99, "AMD", 1200);
        fakeComponent = new Component("3453", "FakeComponent", "NA", "NA",
                0, 0, "NA", 0);
        database = new Database();

        try {
            componentDAOToTest = new ComponentDAO(database.openConnection());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("Error accessing database: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if(database.isOpenConnection()) {
            try {
                database.closeConnection(false);
            } catch (DataAccessException e) {
                e.printStackTrace();
                System.out.println("Failed to close connection in tear down: " + e.getMessage());
            }
        }
        database = null;
        testComponent = null;
        fakeComponent = null;
        componentDAOToTest = null;
    }

    @Test
    @DisplayName("Should insert given component into the database")
    public void testInsert() {
        try {
            componentDAOToTest.insert(testComponent);
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should delete a given component")
    public void testDeleteOne() {
        try {
           int count;
           componentDAOToTest.insert(testComponent);
           count = componentDAOToTest.deleteOne(testComponent.getComponentID());
           database.closeConnection(true);
           assertEquals(1, count);
        } catch (DataAccessException e) {
            fail("DataAccessException thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should delete all components in the database")
    public void testDeleteAll() {
        try {
            int count;
            componentDAOToTest.insert(testComponent);
            componentDAOToTest.insert(fakeComponent);
            count = componentDAOToTest.deleteAll();
            database.closeConnection(true);
            assertEquals(2, count);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should query the given component with no conditions")
    public void testQueryNoPreconditions() {
        try {
            Component copiedComponent;
            componentDAOToTest.insert(testComponent);
            copiedComponent = componentDAOToTest.queryNoPreconditions(testComponent.getComponentID());
            assertNotNull(copiedComponent);
            assertEquals(testComponent, copiedComponent);
            componentDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

}
