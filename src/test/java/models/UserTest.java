package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testHashPassword() {
        User testUser = new User("newport", "Bobby", "Newport", "newport@gmail.com");
        testUser.setPassword("newport123");
        assertEquals(25194, testUser.getPassword());
        testUser.setPassword("rockinroll345!");
        assertEquals(25090, testUser.getPassword());
        testUser.setPassword("fakeUser123");
        assertEquals(23763, testUser.getPassword());
    }

}
