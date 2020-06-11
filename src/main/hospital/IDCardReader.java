package hospital;

public class IDCardReader {
    public boolean verify(IDCard idCard, String pin) {
        boolean verified = idCard.strategyAlgorithm.decrypt(idCard.magneticStripe, idCard.encryptionKey).equals(pin);
        if (verified) System.out.println("IDCardReader: right pin --> Access");
        else System.out.println("IDCardReader: wrong pin --> Access denied");
        return verified;
    }

}
