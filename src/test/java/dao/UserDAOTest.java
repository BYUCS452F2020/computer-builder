package dao;

import models.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
    The Database has been populated so do not run any tests at this time.
 */

public class UserDAOTest {

    private User testUser;
    private User fakeUser;
    private UserDAO userDAOToTest;
    private Database database;

    @BeforeEach
    public void setUp() {
        testUser = new User("newport", "Bobby", "Newport",
                "bobby@gmail.com");
        testUser.setPassword("newport123");
        fakeUser = new User("fakeuser", "Fake", "User", "fake@gmail.com");
        fakeUser.setPassword("fake123");

        database = new Database();
        try {

            userDAOToTest = new UserDAO(database.openConnection());
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
        testUser = null;
        fakeUser = null;
        userDAOToTest = null;
    }

    @Test
    @DisplayName("Should insert given user into the database")
    public void testInsert() {
        try {
            userDAOToTest.insert(testUser);
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should delete a given user")
    public void testDeleteOne() {
        try {
            int count;
            userDAOToTest.insert(testUser);
            count = userDAOToTest.deleteOne(testUser.getUsername());
            database.closeConnection(true);
            assertEquals(1, count);
        } catch (DataAccessException e) {
            fail("DataAccessException thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should delete all users in the database")
    public void testDeleteAll() {
        try {
            int count;
            userDAOToTest.insert(testUser);
            userDAOToTest.insert(fakeUser);
            count = userDAOToTest.deleteAll();
            database.closeConnection(true);
            assertEquals(2, count);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should query the given user")
    public void testQuery() {
        try {
            User copiedUser;
            userDAOToTest.insert(testUser);
            copiedUser = userDAOToTest.find(testUser.getUsername(), "newport123");
            assertNotNull(copiedUser);
            assertEquals(testUser, copiedUser);
            userDAOToTest.deleteAll();
            database.closeConnection(true);
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown: " + e.getMessage());
        }
    }
}
