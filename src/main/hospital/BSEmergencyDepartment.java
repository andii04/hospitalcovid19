package hospital;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Random;

public class BSEmergencyDepartment extends EmergencyDepartment{
    //Department has robot
    RemoteControlRobot remoteControlRobot;

    public BSEmergencyDepartment(){
        remoteControlRobot = new RemoteControlRobot(new DesinfactionRobot());
    }

    public void welcome(BioSafetyEmergencyVehicle vehicle){
        Random r = new Random();

        MedicalStaff chosenMedicalStaff = vehicle.getMedicalStaffs(r.nextInt(3));
        HospitalBed bedForPassenger = hospital.getFreePlace();
        chosenMedicalStaff.goWithPassengerToBed(bedForPassenger, vehicle.getStretcher());
        //@todo stretcher leeren


        vehicle.move("CarPark");
        //chose one of 3 persons
        chosenMedicalStaff = vehicle.getMedicalStaffs(r.nextInt(3));
        chosenMedicalStaff.disinfectVehicle(vehicle, remoteControlRobot);

        chosenMedicalStaff = vehicle.getMedicalStaffs(r.nextInt(3));
        //@todo abschließen

        for (int i=0;i<3;i++){
            vehicle.getMedicalStaffs(i).disinfect();
        }

        carPark.park(vehicle);

        String[] roomInfo = bedForPassenger.getRoomInfo();
        Case newCase = new Case(Integer.parseInt(roomInfo[0]),roomInfo[1], roomInfo[2], Integer.parseInt(roomInfo[3]),new SimpleDateFormat().toString());
    }
}
