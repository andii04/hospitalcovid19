package hospital;

public class IDCardReader {
    public boolean verify(IDCard idCard, String pin){
        if(idCard.strategyAlgorithm.decrypt(idCard.magneticStripe,idCard.encryptionKey) == pin) return true;
        return false;
    }

}
