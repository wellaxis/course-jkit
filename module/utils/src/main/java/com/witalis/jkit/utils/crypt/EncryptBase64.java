package com.witalis.jkit.utils.crypt;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class EncryptBase64 {
    public static final String BASE64 = "BASE64";

    public static void main(String[] args) throws Exception {

        // string encoding
        {
            String value = "public static void main(String[] args) throws Exception {...}";
            log.info("Original[String]: {}", value);

            String encrypted = EncryptBase64.encryptString(value);
            log.info("Encrypted[String]: {}", encrypted);

            String decrypted = EncryptBase64.decryptString(encrypted);
            log.info("Decrypted[String]: {}", decrypted);
        }

        // URL encoding
        {
            String url = "www.google.com.ua/maps";
            log.info("Original[URL]: {}", url);

            String encrypted = EncryptBase64.encryptURL(url);
            log.info("Encrypted[URL]: {}", encrypted);

            String decrypted = EncryptBase64.decryptURL(encrypted);
            log.info("Decrypted[URL]: {}", decrypted);
        }
    }

    /**
     * Encrypt a string value.
     */
    public static String encryptString(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = value.getBytes(StandardCharsets.UTF_16);
        return encoder.encodeToString(bytes);
    }

    /**
     * Decrypt a string value.
     */
    public static String decryptString(String value) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(value);
        return new String(bytes, StandardCharsets.UTF_16);
    }

    /**
     * Encrypt an URL value.
     */
    public static String encryptURL(String url) {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        return new String(encoder.encode(url.getBytes()));
    }

    /**
     * Decrypt an URL value.
     */
    public static String decryptURL(String url) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        return new String(decoder.decode(url));
    }
}
