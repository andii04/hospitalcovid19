package hospital;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class KeyStore {
    //Here are keys for the normal vehicles are safed. A employee of the hospital can get the key to a certain vehicle with the serial number
    private Map<Integer, Key> keys = new HashMap<>();

    static String randomString(int len) {
        Random rnd = new Random();
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    Key getKeyForEmergencyVehicle(String serialNumber) {
        return keys.get(serialNumber);
    }

    //create a suitable key to access the vehicle
    public void createKey(Integer serialNumber, String signature) {
        Key key = new Key(signature);
        keys.put(serialNumber, key);
    }

}
