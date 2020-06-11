package hospital;

import shared.Configuration;

public class IDCard {
    IEnryptionStrategy strategyAlgorithm;
    String encryptionKey = "dh$bw20!20";
    String magneticStripe;

    public IDCard(Configuration chosenAlgo, String myPinforCard) {
        if (chosenAlgo == Configuration.AES) {
            strategyAlgorithm = new AESAlgorithm();
        } else if (chosenAlgo == Configuration.DES) {
            strategyAlgorithm = new DESAlgorithm();
        }
        magneticStripe = strategyAlgorithm.encrypt(myPinforCard, encryptionKey);
    }
}
