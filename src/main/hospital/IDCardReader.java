package hospital;

public class IDCardReader {
    public boolean verify(IDCard idCard, String pin) {
        return idCard.strategyAlgorithm.decrypt(idCard.magneticStripe, idCard.encryptionKey) == pin;
    }

}
