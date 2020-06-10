package hospital;

import shared.Configuration;

public class IDCard {
    IEnryptionStrategy strategyAlgorithm;
    String encryptionKey = "dh$bw20!20";
    public IDCard(Configuration chosenAlgo){
        if(chosenAlgo == Configuration.AES){
            strategyAlgorithm = new AESAlgorithm();
        }
        else if(chosenAlgo == Configuration.DES){
            strategyAlgorithm = new DESAlgorithm();
        }
    }
    String magneticStripe;
}
