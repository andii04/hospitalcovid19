package hospital;

import java.util.Random;

public class DesinfactionRobot implements IVisitorRobot {
    @Override
    public void visit(EmergencyVehicle emergencyVehicle) {
        System.out.println("Desinfcation with Chloroxid");
        emergencyVehicle.setAmbientAir(cleanAir(emergencyVehicle.getAmbientAir()));
    }

    @Override
    public void visit(BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle) {
        System.out.println("Desinfcation with Ethylenoxid");
        bioSafetyEmergencyVehicle.setAmbientAir(cleanAir(bioSafetyEmergencyVehicle.getAmbientAir()));
    }

    Random r = new Random();
    private String[][][] cleanAir(String[][][] ambientAir) {
        for (String[][] strings : ambientAir) {
            for (String[] strings2 : strings) {
                for (String string1 : strings2) {
                    if (string1 =="v"){
                        string1 = cleanAIRPool[r.nextInt(cleanAIRPool.length)];
                    }
                }
            }
        }
        return ambientAir;
    }


    String[] cleanAIRPool = new String[]{"A","I","R"};
}
