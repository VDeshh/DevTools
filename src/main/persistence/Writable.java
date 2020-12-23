package persistence;

import org.json.JSONObject;

//Interface that is used to represent the toJson method
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
