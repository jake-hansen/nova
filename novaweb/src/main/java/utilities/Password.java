package utilities;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Password {
    private SecureRandom random = new SecureRandom();
    private final static int SALT_BYTE_LENGTH = 16;
    private final static int ITERATIONS = 100000;
    private final static String ALGORITHM = "PBKDF2WithHmacSHA256";
    private final static String SECUR_RAND_INSTANCE = "SHA1PRNG";

    /**
     * Generates a random salt.
     * @return - Byte array of salt.
     * @throws NoSuchAlgorithmException
     */
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance(SECUR_RAND_INSTANCE);
        byte[] salt = new byte[SALT_BYTE_LENGTH];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Create a new hash for given password with random salt.
     * @param password - Password to hash.
     * @return - Hashed password.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String hash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();

        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Converts given byte array into hex.
     * @param array - Byte array to convert.
     * @return - Hex representation of byte array.
     * @throws NoSuchAlgorithmException
     */
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        else {
            return hex;
        }
    }

    /**
     * Validates plaintext password with hashed password.
     * @param unhashedPassword - Plaintext password to validate.
     * @param hashedPassword - Hash to validate.
     * @return - True if plaintext password matches hash, false otherwise.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static boolean validate(char[] unhashedPassword, String hashedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] hashedPassParts = hashedPassword.split(":");
        int iterations = Integer.parseInt(hashedPassParts[0]);
        byte[] salt = fromHex(hashedPassParts[1]);
        byte[] hash = fromHex(hashedPassParts[2]);

        PBEKeySpec spec = new PBEKeySpec(unhashedPassword, salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * Converts from hex to byte array.
     * @param hex - Hex string to convert.
     * @return - Byte array representation of hex.
     * @throws NoSuchAlgorithmException
     */
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
