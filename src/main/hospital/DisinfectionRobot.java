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
        for (int i = 0; i < ambientAir.length; i++){
            for (int a = 0; a < ambientAir[i].length; a++){
                for (int b = 0; b < ambientAir[i][a].length; b++){ {
                    if (ambientAir[i][a][b] == "v") {
                        ambientAir[i][a][b] = cleanAIRPool[r.nextInt(cleanAIRPool.length)];
                    }
                    }
                }
            }
        }
        return ambientAir;
    }
}
