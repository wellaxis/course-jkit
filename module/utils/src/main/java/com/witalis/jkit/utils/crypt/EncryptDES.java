package com.witalis.jkit.utils.crypt;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EncryptDES {
    public static final String DES = "DES";
    private static KeyGenerator keyGenerator;

    public EncryptDES() {
        try {
            keyGenerator = KeyGenerator.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypt a value.
     */
    public static String encrypt(String value) {
        Key key = keyGenerator.generateKey();
        // create encryption cipher
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(value.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encrypted != null ? new String(encrypted) : null;
    }

    /**
     * Decrypt a value.
     */
    public static String decrypt(String value) {
        Key key = keyGenerator.generateKey();
        // create encryption cipher
        Cipher cipher = null;
        byte[] decrypted = null;
        try {
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(value.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decrypted != null ? new String(decrypted) : null;
    }

    public static void main(String[] args) throws Exception {
        String value = "public static void main(String[] args) throws Exception {...}";
        log.info("Original: {}", value);
        String encrypted = EncryptDES.encrypt(value);
        log.info("Encrypted: {}", encrypted);
        String decrypted = EncryptDES.decrypt(encrypted);
        log.info("Decrypted: {}", decrypted);
    }
}