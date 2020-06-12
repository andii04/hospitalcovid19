package hospital;

import java.util.Random;

public class BSEmergencyDepartment extends EmergencyDepartment {
    //Department has robot
    RemoteControlRobot remoteControlRobot;

    public BSEmergencyDepartment() {
        super(DepartmentsName.BSEmergencyDepartment);
        remoteControlRobot = new RemoteControlRobot(new DisinfectionRobot());

    }

    //welcome of the BSemergency --> calls the super.welcome
    public void welcome(BioSafetyEmergencyVehicle vehicle) {
        System.out.println("BSEmergencyDepartment: BioSafetyEmergencyVehicle arrived");
        Random r = new Random();
        super.welcome(vehicle);

        vehicle.setFlashingLightOff();
        vehicle.move("CarPark");
        //chose one of 3 persons for disinfect
        MedicalStaff chosenMedicalStaffForRobot = vehicle.getMedicalStaffs(r.nextInt(3));
        chosenMedicalStaffForRobot.disinfectVehicle(vehicle, remoteControlRobot);

        //chose one of them to close
        MedicalStaff chosenMedicalStaffforLock = vehicle.getMedicalStaffs(r.nextInt(3));
        chosenMedicalStaffforLock.closeBioSafetyVehicle(vehicle);
        for (int i = 0; i < 3; i++) {
            vehicle.getMedicalStaffs(i).disinfect();
        }
        //park and get vehicle back to pool
        carPark.park(vehicle);
    }

}
