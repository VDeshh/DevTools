package persistence;

import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkUser(String name, String userName, String passWord, User usr) {
        assertEquals(0,name.compareTo(usr.getName()));
        assertEquals(0,userName.compareTo(usr.getUserName()));
        assertEquals(0,passWord.compareTo(usr.getPassWord()));
    }
}
