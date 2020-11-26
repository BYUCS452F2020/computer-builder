package dao;

import models.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
/*
    The Database has been populated so do not run any tests at this time.
 */
public class ComponentDAOTest {

    private Component testComponent;
    private Component testComponentTwo;
    private Component fakeComponent;
    private Component randomComponent;
    private ComponentDAO componentDAOToTest;
    private Database database;

    @BeforeEach
    public void setUp() {
        testComponent = new Component("1234", "AMD Ryzen 7", "CPU",
                "AMD", 6, 99.99, "AMD", 1200);
        fakeComponent = new Component("3453", "FakeComponent", "NA", "NA",
                0, 0.00, "NA", 0);
        testComponentTwo = new Component("4568", "Intel I5", "CPU",
                "Intel", 6, 120.89, "Intel", 1200);
        randomComponent = new Component(UUID.randomUUID().toString(), "X470 Taichi Ultimate", "Motherboard", "AMD",
                7, 294.00, "AMD", 63);
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
            componentDAOToTest.insert(randomComponent);
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

    //TODO test queries with conditions
    @Test
    @DisplayName("Should query component with certain type")
    public void testQueryTypeCondition() {
        try {
            Component copiedComponent;
            componentDAOToTest.insert(testComponent);
            componentDAOToTest.insert(fakeComponent);
            copiedComponent = componentDAOToTest.queryComponentsWithConditions(testComponent.getComponentType(), 0, 0, null, 0).get(0);
            assertNotNull(copiedComponent);
            assertEquals(testComponent, copiedComponent);
            componentDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should query component with certain performance rating")
    public void testQueryPerformanceCondition() {
        try {
            Component copiedComponent;
            componentDAOToTest.insert(testComponent);
            componentDAOToTest.insert(fakeComponent);
            componentDAOToTest.insert(testComponentTwo);
            List<Component> queriedComponents = componentDAOToTest.queryComponentsWithConditions("CPU", 0, 6, null, 0);
            assertNotNull(queriedComponents);
            assertEquals(2, queriedComponents.size());
            copiedComponent = queriedComponents.get(0);
            assertEquals(testComponent, copiedComponent);
            copiedComponent = queriedComponents.get(1);
            assertEquals(testComponentTwo, copiedComponent);
            componentDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should query component with certain price range")
    public void testQueryPriceCondition() {
        try {
            Component copiedComponent;
            componentDAOToTest.insert(testComponent);
            componentDAOToTest.insert(fakeComponent);
            componentDAOToTest.insert(testComponentTwo);
            List<Component> queriedComponents = componentDAOToTest.queryComponentsWithConditions(testComponent.getComponentType(), 100, 0, null, 0);
            assertEquals(1, queriedComponents.size());
            copiedComponent = queriedComponents.get(0);
            assertEquals(testComponent, copiedComponent);
            componentDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should query component with certain cpu family")
    public void testQueryCPUFamilyCondition() {
        try {
            Component copiedComponent;
            componentDAOToTest.insert(testComponent);
            componentDAOToTest.insert(fakeComponent);
            componentDAOToTest.insert(testComponentTwo);
            List<Component> queriedComponents = componentDAOToTest.queryComponentsWithConditions(testComponent.getComponentType(), 0, 0, "Intel", 0);
            assertEquals(1, queriedComponents.size());
            copiedComponent = queriedComponents.get(0);
            assertEquals(testComponentTwo, copiedComponent);
            componentDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should query component based on all conditions")
    public void testQueryAllConditions() {
        try {
            Component copiedComponent;
            componentDAOToTest.insert(testComponent);
            componentDAOToTest.insert(fakeComponent);
            componentDAOToTest.insert(testComponentTwo);
            List<Component> queriedComponents = componentDAOToTest.queryComponentsWithConditions(testComponent.getComponentType(), 100, 6, "AMD", 0);
            assertEquals(1, queriedComponents.size());
            copiedComponent = queriedComponents.get(0);
            assertEquals(testComponent, copiedComponent);
            componentDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }



}
