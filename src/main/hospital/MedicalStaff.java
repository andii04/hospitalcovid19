package hospital;

import shared.Human;

public class MedicalStaff extends Human {
    private String id;
    private boolean hasProtection = false;

    public MedicalStaff(String id){
        this.id = id;
    }
    public void disinfect(){
        System.out.println("Medical Staff ID "+ id +" desinfect");
        hasProtection = false;
    }
}
