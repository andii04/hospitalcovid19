package hospital;

import java.util.ArrayList;

public class Station {
    ArrayList<Room> rooms = new ArrayList<>();
    private String id;

    public Station(String id, ArrayList<Room> rooms) {
        this.id = id;
        this.rooms = rooms;
    }

    public static int getStationNumberFromNameID(String s) {
        switch (s) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
        }
        return -1;
    }

    public Room getRoom(int i) {
        return rooms.get(i);
    }

    public int getNumberOfRooms() {
        return rooms.size();
    }

    public String getName() {
        return id;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
