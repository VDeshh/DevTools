package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of User
public class WorkRoom implements Writable {
    private String name;
    private List<User> users;

    // EFFECTS: constructs workroom with a name and empty list of Users
    public WorkRoom(String name) {
        this.name = name;
        users = new ArrayList<>();
    }

    // EFFECTS: returns the name of this workroom
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds Users to this workroom
    public void addUser(User u) {
        users.add(u);
    }

    // EFFECTS: returns an unmodifiable list of Users in this workroom
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    // EFFECTS: returns number of Users in this workroom
    public int numUsers() {
        return users.size();
    }

    // EFFECTS: Pushes all the Users of the workroom into a JSON structure and constructs a JSON object and returns
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("users", usersToJson());
        return json;
    }

    // EFFECTS: returns Users in this workroom as a JSON array
    private JSONArray usersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (User u : users) {
            jsonArray.put(u.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: Checks if the username is taken in the current workroom. Returns true if username is available
    public boolean checkUserName(String userNameToCheck) {

        List<User> users = getUsers();

        for (User u : users) {
            if (u.getUserName().compareTo(userNameToCheck) == 0) {
                return false;
            }
        }
        return true;

    }

    // EFFECTS: returns the user with the given username
    public User getUserWithUserName(String userNameToCheck) {

        for (User u : users) {
            if (u.getUserName().equals(userNameToCheck)) {
                return u;
            }
        }

        return null;
    }

}

