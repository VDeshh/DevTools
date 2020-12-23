package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents a User Model Class which has name, Username, Password, Operating System and the access permission
public class User implements Writable {

    private String name;
    private String userName;
    private String passWord;
    private String operatingSystem;
    private boolean accessPermission;

    // EFFECTS: Parameterized Constructor to initialize the data members to the corresponding arguments.
    public User(String name,
                String userName,
                String passWord,
                String operatingSystem,
                boolean accessPermission) {

        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.operatingSystem = operatingSystem;
        this.accessPermission = accessPermission;

    }

    // EFFECTS: Returns the name of the User
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: Sets the name of the User
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: Returns the username of the User
    public String getUserName() {
        return userName;
    }

    // MODIFIES: this
    // EFFECTS: Sets the username of the User
    public void setUserName(String userName) {
        this.userName = userName;
    }

    //EFFECTS: Returns the Password of the User
    public String getPassWord() {
        return passWord;
    }

    // MODIFIES: this
    // EFFECTS: Sets the Password of the User
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    // EFFECTS: Returns the Operating System used by the User
    public String getOperatingSystem() {
        return operatingSystem;
    }

    // MODIFIES: this
    //EFFECTS: Sets the Operating System of the User
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    // EFFECTS: Returns the access permission (true / false)
    public boolean isAccessPermission() {
        return accessPermission;
    }

    // MODIFIES: this
    // EFFECTS: Sets the access permission (true / false)
    public void setAccessPermission(boolean accessPermission) {
        this.accessPermission = accessPermission;
    }

    // EFFECTS: Pushes all the User data into a JSON structure and constructs a JSON object and returns
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("User Name", userName);
        json.put("Password", passWord);
        json.put("Operating System", operatingSystem);
        json.put("Access Permission", accessPermission);
        return json;
    }
}

