package hospital;

import java.util.Random;

public class DisinfectionRobot implements IVisitorRobot {
    Random r = new Random();
    String[] cleanAIRPool = new String[]{"A", "I", "R"};

    @Override
    public void visit(EmergencyVehicle emergencyVehicle) {
        System.out.println("DisinfectionRobot: Disinfection with Chloroxid");
        emergencyVehicle.setAmbientAir(cleanAir(emergencyVehicle.getAmbientAir()));
    }

    @Override
    public void visit(BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle) {
        System.out.println("DisinfectionRobot: Disinfection with Ethylenoxid");
        bioSafetyEmergencyVehicle.setAmbientAir(cleanAir(bioSafetyEmergencyVehicle.getAmbientAir()));
    }

    private String[][][] cleanAir(String[][][] ambientAir) {
        for (String[][] strings : ambientAir) {
            for (String[] strings2 : strings) {
                for (String string1 : strings2) {
                    if (string1 == "v") {
                        string1 = cleanAIRPool[r.nextInt(cleanAIRPool.length)];
                    }
                }
            }
        }
        return ambientAir;
    }
}
