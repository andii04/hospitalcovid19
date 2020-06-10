package hospital;

import java.util.LinkedList;

public class EmergencyDepartment extends Department {
    private HospitalPatient currentHospitalPatient;
    private LinkedList<HospitalBed> emptyHospitalBedList;

    //Mergency Department has Access to the carPark, and free rooms
    CarPark carPark;
    Hospital hospital;

    public void setCarPark(CarPark carPark){
        this.carPark = carPark;
    }



    public void welcome (EmergencyVehicle vehicle){

    }

    /*nochmal schauen wegen Rückgabewert Stretcher
    private Stretcher unload(EmergencyVehicle vehicle){
        return Stretcher;
    }*/
    private void register(HospitalPatient hospitalPatient){

    }

    public void move(HospitalBed hospitalBed,Case thecase){

    }

    //own
    public void choseAssistant(){

    }

}
