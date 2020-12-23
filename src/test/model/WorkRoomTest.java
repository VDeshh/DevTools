package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
This class represents the jUnit tests for the WorkRoom Model Class methods
 */

public class WorkRoomTest {

    WorkRoom wR;
    User usr1;
    User usr2;

    @BeforeEach
    void setup() {

        wR=new WorkRoom("Test WorkRoom");

        usr1 = new User("Sam", "sam2020", "A12P1",
                "Windows", true);

        usr2 = new User("Matt", "matt1010", "A00P2",
                "MACOS", false);

        wR.addUser(usr1);
        wR.addUser(usr2);
    }


    @Test
    void checkUserNameTestCorrect() {
        assertFalse(wR.checkUserName("sam2020"));
        assertFalse(wR.checkUserName("matt1010"));
    }
    @Test
    void checkUserNameTestWrong() {
        assertTrue(wR.checkUserName("Hello123"));
    }

    @Test
    void getUserWithUserNameTestCorrect() {
       assertTrue(wR.getUserWithUserName("sam2020").getName().equals("Sam"));
       assertTrue(wR.getUserWithUserName("matt1010").getName().equals("Matt"));
       assertNull(wR.getUserWithUserName("hello123"));
    }

    @Test
    void getUserWithUserNameTestWrong() {
        assertNull(wR.getUserWithUserName("sass"));
        assertFalse(wR.getUserWithUserName("sam2020").getName().equals("MAN"));
        assertNotNull(wR.getUserWithUserName("sam2020"));

    }
}