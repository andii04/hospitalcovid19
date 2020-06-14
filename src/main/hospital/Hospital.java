package hospital;

import shared.Human;

import java.io.Reader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hospital {
    CarPark carPark;
    Stack<Floor> floors;
    private String name;
    private int countHospitalGuest = 0;
    private String encryptionKeyforCases= "vRMbp7kiSXAVA59X";
    private String encryptedKey;

    ArrayList<Case> allCases = new ArrayList<>();

    public Hospital(String name, Stack<Floor> floors, CarPark carPark) {
        this.name = name;
        this.floors = floors;
        this.carPark = carPark;

        //later have access to the vehicles
        ((BSEmergencyDepartment) floors.get(0).getDepartments(0)).setCarPark(carPark);
        ((EmergencyDepartment) floors.get(0).getDepartments(1)).setCarPark(carPark);
        ((BSEmergencyDepartment) floors.get(0).getDepartments(0)).setHospital(this);
        ((EmergencyDepartment) floors.get(0).getDepartments(1)).setHospital(this);
        LinkedList<HospitalBed> oneFreeBed = new LinkedList<>();
        oneFreeBed.add(getFreeBed());
        ((BSEmergencyDepartment) getFloor(0).getDepartments(0)).setEmptyHospitalBedList(oneFreeBed);
        AESAlgorithm aesAlgorithm = new AESAlgorithm();
        encryptedKey = aesAlgorithm.encrypt("HospitalPass1234", encryptionKeyforCases);
    }

    //general IDs for new patients
    public int getNewHospitalPatientID() {
        countHospitalGuest++;
        return countHospitalGuest;
    }

    //get the floors, departments etc..
    public Floor getFloor(int floorID) {
        return floors.get(floorID);
    }

    //get a RANDOM bed form (1) the first department(floor=1) or (2) the second department(floor=2)
    public HospitalBed getFreeBed() {
        Random r = new Random();
        ArrayList<HospitalBed> availableBeds;
        ArrayList<int[]> bedInfo;
        //two possible floors for beds
        for (int i = 1; i <= 2; i++) {
            availableBeds = new ArrayList<>();
            bedInfo = new ArrayList<>();
            //-->interate over the rooms, departments
            for (int j = 0; j < floors.get(i).getDepartments(0).getNumberOfStations(); j++) {
                for (int k = 0; k < floors.get(i).getDepartments(0).getStation(j).getNumberOfRooms(); k++) {
                    for (int l = 0; l < floors.get(i).getDepartments(0).getStation(j).getRoom(k).getNumberOfBeds(); l++) {
                        if (floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l) != null &&
                                floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l).isEmpty()) {
                            /* System.out.println("Hospital: Found free bed in Department " + Floor.getNameDepartmentFloor(i) +
                                    " / Station " + floors.get(i).getDepartments(0).getStation(j).getName()+
                                    " / Room " + k + 1 +
                                    " /Bed " + l+1);*/
                            HospitalBed freeBed = floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l);
                            availableBeds.add(freeBed);
                            bedInfo.add(new int[]{i, j, k, l});
                        }
                    }
                }
            }
            //if a collection of free beds >0 --> select random --> print
            if (availableBeds.size() != 0) {
                int randomNumber = r.nextInt(availableBeds.size());
                int[] bedInfoChosenBed = bedInfo.get(randomNumber);
                HospitalBed chosenBed = availableBeds.get(randomNumber);
                floors.get(i).getDepartments(0).stations.get(bedInfoChosenBed[1]).rooms.get(bedInfoChosenBed[2]).setHospitalBed(bedInfoChosenBed[3], null);
                System.out.println("Hospital: Found free bed in Department " + floors.get(i).getDepartments(0).getName() +
                        " / Station " + floors.get(i).getDepartments(0).getStation(bedInfoChosenBed[1]).getName() +
                        " / Room " + bedInfoChosenBed[2] + 1 +
                        " /Bed " + bedInfoChosenBed[3] + 1);
                return chosenBed;
            }
        }
        return null;
    }

    //get a RANDOM bed in the station where the last bed was moved
    public HospitalBed getBedByStation(String name, String station) {
        //same code as method above
        int floorID = 1;
        int stationID = Station.getStationNumberFromNameID(station);
        if (name.equals(DepartmentsName.Pulmonology)) {
            floorID = 2;
        }
        Random r = new Random();
        ArrayList<HospitalBed> availableBeds = new ArrayList<>();
        ArrayList<int[]> bedInfo = new ArrayList<>();
        for (int k = 0; k < floors.get(floorID).getDepartments(0).getStation(stationID).getNumberOfRooms(); k++) {
            for (int l = 0; l < floors.get(floorID).getDepartments(0).getStation(stationID).getRoom(k).getNumberOfBeds(); l++) {
                if (floors.get(floorID).getDepartments(0).stations.get(stationID).rooms.get(k).getHospitalBed(l) != null &&
                        floors.get(floorID).getDepartments(0).stations.get(stationID).rooms.get(k).getHospitalBed(l).isEmpty()) {
                             /* System.out.println("Hospital: Found free bed in Department " + Floor.getNameDepartmentFloor(stationID) +
                                    " / Station " + floors.get(floorID).getDepartments(0).getStation(stationID).getName()+
                                    " / Room " + k + 1 +
                                    " /Bed " + l+1);*/
                    HospitalBed freeBed = floors.get(floorID).getDepartments(0).stations.get(stationID).rooms.get(k).getHospitalBed(l);
                    availableBeds.add(freeBed);
                    bedInfo.add(new int[]{floorID, stationID, k, l});

                }
            }
        }
        //if a collection of free beds >0 --> select random --> print
        if (availableBeds.size() != 0) {
            int randomNumber = r.nextInt(availableBeds.size());
            int[] bedInfoChosenBed = bedInfo.get(randomNumber);
            HospitalBed chosenBed = availableBeds.get(randomNumber);
            floors.get(floorID).getDepartments(0).stations.get(bedInfoChosenBed[1]).rooms.get(bedInfoChosenBed[2]).setHospitalBed(bedInfoChosenBed[3], null);
            System.out.println("Hospital: Found free bed in Department " + floors.get(floorID).getDepartments(0).getName() +
                    " / Station " + floors.get(floorID).getDepartments(0).getStation(bedInfoChosenBed[1]).getName() +
                    " / Room " + (bedInfoChosenBed[2] + 1) +
                    " /Bed " + (bedInfoChosenBed[3] + 1) +
                    "--> will be moved to BSEmergencyDepartment");
            return chosenBed;
        }
        //nothing free in station --> go somewhere else
        //return getFreeBed();
        return getFreeBed();
    }

    //get a RANDOM free space where bed can be placed
    public String[] getFreeSpace() {
        Random r = new Random();
        ArrayList<String[]> freeSpace;
        //two possible floors
        for (int i = 1; i <= 2; i++) {
            freeSpace = new ArrayList<>();
            for (int j = 0; j < floors.get(i).getDepartments(0).getNumberOfStations(); j++) {
                for (int k = 0; k < floors.get(i).getDepartments(0).getStation(j).getNumberOfRooms(); k++) {
                    for (int l = 0; l < floors.get(i).getDepartments(0).getStation(j).getRoom(k).getNumberOfBeds(); l++) {
                        if (floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l) == null) {
                            /*  System.out.println("Hospital: Found free bed in Department " + Floor.getNameDepartmentFloor(i) +
                                    " / Station " + floors.get(i).getDepartments(0).getStation(j).getName()+
                                    " / Room " + k + 1 +
                                    " /Bed " + l+1);*/
                            freeSpace.add(new String[]{
                                    Integer.toString(i), //Floor
                                    Floor.getNameDepartmentFloor(i).toString(), //departmentName
                                    floors.get(i).getDepartments(0).getStation(j).getName(), //stationName
                                    Integer.toString(k + 1), //RoomID
                                    Integer.toString(l + 1)}); //BedID
                        }
                    }
                }
            }
            if (freeSpace.size() != 0) {
                int randomNumber = r.nextInt(freeSpace.size());
                String[] freeSpaceInfo = freeSpace.get(randomNumber);
                System.out.println("Hospital: Found free space where bed will be moved in Department " + freeSpaceInfo[1] +
                        " / Station " + freeSpaceInfo[2] +
                        " / Room " + freeSpaceInfo[3] +
                        " /Bed " + freeSpaceInfo[4]);
                return freeSpaceInfo;
            }
        }
        System.out.println("Nothing found");
        return null;
    }

    public void addCaseInDP(Case newCase) {
        allCases.add(newCase);
    }

    //Builder
    public static class Builder {

        Stack<Floor> floors;
        CarPark carPark;
        private String name;

        public Hospital.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Hospital.Builder setFloors(Stack<Floor> floors) {
            this.floors = floors;
            return this;
        }

        public Hospital.Builder setCarPark(CarPark carPark) {
            this.carPark = carPark;
            return this;
        }

        public Hospital build() {
            return new Hospital(name, floors, carPark);
        }
    }



    //getting Data --> security mechanismus
    public Case getMyCase(Human human){
        for (Case casePatient: allCases) {
            if(casePatient.getFirstName().equals(human.getFirstName()) && casePatient.getLastName().equals(human.getLastName()) && casePatient.getBirthDate().equals(human.getFirstName())){
                return casePatient;
            };
        }
        return null;
    }

    //verify the password
    private boolean verify(String masterKeyforCases) {
        AESAlgorithm aesAlgorithm = new AESAlgorithm();
        if (encryptedKey.equals(aesAlgorithm.encrypt(masterKeyforCases, encryptionKeyforCases))) {
            return true;
        } else return false;
    }

    //get case from a specific patient id --> verify with a password
    public Case getCaseFromPatient(String password, int id){
        if(verify(password)){
            for (Case casePatient :allCases) {
                if(casePatient.getID() == id){
                    return casePatient;
                }
            }
        }
        System.out.println("Wrong password or id unknown");
        return null;
    }
    //
    public void getAnalytics(String password){
        if (verify(password)){
        System.out.println("Hospital:");
        Map<String, Character> mapNametoStation = new HashMap<>();
        //1)
        getFloor(1).getDepartments(0).getStations().forEach(station -> {
            station.getRooms().forEach(room -> {
                for(int i=0;i<room.getNumberOfBeds();i++){
                    if(room.getHospitalBed(i)!=null && room.getHospitalBed(i).getHuman()!=null){
                        mapNametoStation.put(room.getHospitalBed(i).getHuman().getLastName(),station.getName().charAt(0));
                    }
                }
            });
        });
        System.out.println("List of patients in stations sorted");
        mapNametoStation.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .sorted(Map.Entry.comparingByKey())
                .forEach(stringStringEntry -> System.out.println("On Station " + stringStringEntry.getValue()+ " is Patient " + stringStringEntry.getKey()));

        System.out.println();

        //2)
        AtomicInteger counterPatients = new AtomicInteger();
        getFloor(1).getDepartments(0).getStations().forEach(station -> {
            station.getRooms().forEach(room -> {
                for(int i=0;i<room.getNumberOfBeds();i++){
                    if(room.getHospitalBed(i)!=null && room.getHospitalBed(i).getHuman()!=null){
                        counterPatients.getAndIncrement();
                    }
                }
            });
        });
        System.out.println("Total patients in hospital: " + counterPatients);
        System.out.println();

        //3)
        HashMap<Character, Integer> counterStation = new HashMap<>();
        getFloor(1).getDepartments(0).getStations().forEach(station -> {
            AtomicInteger counterPatientsStation = new AtomicInteger();
            station.getRooms().forEach(room -> {
                for(int i=0;i<room.getNumberOfBeds();i++){
                    if(room.getHospitalBed(i)!=null && room.getHospitalBed(i).getHuman()!=null){
                        counterPatientsStation.getAndIncrement();
                    }
                }
                counterStation.put(station.getName().charAt(0),counterPatientsStation.intValue());
            });
        });
        counterStation.entrySet().stream().forEach(entry -> System.out.println("On Station " + entry.getKey() + " are " + entry.getValue() + " patients."));
        System.out.println();
        }
        else{
            System.out.println("Wrong password");
        }
    }
}