package hospital;

public interface IEnryptionStrategy {
    String decrypt(String s, String key);

    String encrypt(String s, String key);
}
