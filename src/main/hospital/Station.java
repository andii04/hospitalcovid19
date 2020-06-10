package hospital;

import java.util.ArrayList;

public class Station {
    private String id;

    ArrayList<Room> rooms = new ArrayList<>();
    public Station(String id, ArrayList<Room> rooms) {
        this.id = id;
        this.rooms = rooms;
    }

    public Room getRoom(int i ){
        return rooms.get(i);
    }
    public int getNumberOfRooms(){
        return rooms.size();
    }

    public String getName() {
        return id;
    }
}
