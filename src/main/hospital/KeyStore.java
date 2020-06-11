package hospital;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class KeyStore {

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

    public void createKey(Integer serialNumber, String signature) {
        Key key = new Key(signature);
        keys.put(serialNumber, key);
    }

}
