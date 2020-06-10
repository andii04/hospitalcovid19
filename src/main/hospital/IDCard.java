package hospital;

import shared.Configuration;

public class IDCard {
    private IEnryptionStrategy strategyAlgorithm;
    public IDCard(Configuration chosenAlgo){
        if(chosenAlgo == Configuration.AES){
            strategyAlgorithm = new AESAlgorithm();
        }
        else if(chosenAlgo == Configuration.DES){
            strategyAlgorithm = new DESAlgorithm();
        }
    }
    private String magneticStripe;
}
