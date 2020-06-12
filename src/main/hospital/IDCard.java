package hospital;

import shared.Configuration;

public class IDCard {
    IEnryptionStrategy strategyAlgorithm;
    String encryptionKey = "dh$bw20!20";
    String magneticStripe;

    //IDCard set magnetStripe with the suitable pin
    public IDCard(Configuration chosenAlgo, String myPinforCard) {
        if (chosenAlgo == Configuration.AES) {
            strategyAlgorithm = new AESAlgorithm();
        } else if (chosenAlgo == Configuration.DES) {
            strategyAlgorithm = new DESAlgorithm();
        }
        magneticStripe = strategyAlgorithm.encrypt(myPinforCard, encryptionKey);
    }
}
