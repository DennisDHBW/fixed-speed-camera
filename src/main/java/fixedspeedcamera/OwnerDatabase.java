package fixedspeedcamera;

import java.util.HashMap;

public class OwnerDatabase {
    static HashMap<String, String> data = new HashMap<>();

    public void registration(String licensePlate, String name) {
        data.put(licensePlate, name);
    }

    public void deregistration(String licensePlate) {
        data.remove(licensePlate);
    }

    public String getOwner(String licensePlate) {
        return data.get(licensePlate);
    }

}
