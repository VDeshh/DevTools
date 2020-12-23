package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
This class represents the jUnit tests for the User Model Class methods
 */
public class UserTest {

    User usrG;
    User usrS;

    @BeforeEach
    void setup() {
        usrG = new User("Sam", "sam2020", "A12P1",
                "Windows", true);
        usrS = new User("Sam", "sam2020", "A12P1",
                "Windows", true);
    }


    @Test
    void getNameTestCorrect() {
        assertTrue(usrS.getName().equals("Sam"));
    }

    @Test
    void getNameTestWrong() {
        assertFalse(usrG.getName().equals("Same"));
    }

    @Test
    void setNameTestCorrect() {
        usrS.setName("Matt");
        assertTrue(usrS.getName().equals("Matt"));

    }

    @Test
    void setNameTestWrong() {
        usrS.setName("Matt");
        assertFalse(usrS.getName().equals("James"));
    }

    @Test
    void getUserNameTestCorrect() {
        assertTrue(usrG.getUserName().equals("sam2020"));
    }

    @Test
    void getUserNameTestWrong() {
        assertFalse(usrG.getUserName().equals("Matt101"));
    }


    @Test
    void setUserNameTestCorrect() {
        usrS.setUserName("Matt101");
        assertTrue(usrS.getUserName().equals("Matt101"));
    }

    @Test
    void setUserNameTestWrong() {
        usrS.setUserName("Matt101");
        assertFalse(usrS.getUserName().equals("Sam2000"));
    }

    @Test
    void getPassWordTestCorrect() {
        assertTrue(usrG.getPassWord().equals("A12P1"));
    }

    @Test
    void getPassWordTestWrong() {
        assertFalse(usrG.getPassWord().equals("A00012P1"));

    }

    @Test
    void setPassWordTestCorrect() {
        usrS.setPassWord("Hello12");
        assertTrue(usrS.getPassWord().equals("Hello12"));
    }

    @Test
    void setPassWordTestWrong() {
        usrS.setPassWord("Hello12");
        assertFalse(usrS.getPassWord().equals("ABCD"));
    }

    @Test
    void getOperatingSystemCoreect() {
        assertTrue(usrG.getOperatingSystem().equals("Windows"));
    }


    @Test
    void getOperatingSystemWrong() {
        assertFalse(usrG.getOperatingSystem().equals("MACOS"));
    }


    @Test
    void setOperatingSystemCorrect() {
        usrS.setOperatingSystem("MACOS");
        assertTrue(usrS.getOperatingSystem().equals("MACOS"));
    }

    @Test
    void setOperatingSystemWrong() {
        usrS.setOperatingSystem("MACOS");
        assertFalse(usrS.getOperatingSystem().equals("WINDOWS"));
    }


    @Test
    void isAccessPermissionTest() {
        assertTrue(usrG.isAccessPermission());
    }

    @Test
    void setAccessPermissionTest() {
        usrS.setAccessPermission(false);
        assertFalse(usrS.isAccessPermission());
    }

}
