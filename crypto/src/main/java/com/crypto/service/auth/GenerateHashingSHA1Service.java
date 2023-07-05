package com.crypto.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Semih, 23.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class GenerateHashingSHA1Service {

    private static final String SECRET = "PBKDF2WithHmacSHA1";

    private static final String SECURE_KEY = "SHA1PRNG";

    public boolean validateHash(String originalHash, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {

        String[] parts = storedHash.split(":");

        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalHash.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET);
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;

        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }

        return diff == 0;

    }

    private byte[] fromHex(String hex) throws NoSuchAlgorithmException {

        byte[] bytes = new byte[hex.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;

    }

    public String generateHash(String text) throws NoSuchAlgorithmException, InvalidKeySpecException {

        final int iterations = 1000;
        final char[] chars = text.toCharArray();
        final byte[] salt = getSalt();

        final PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        final SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET);

        final byte[] hash = skf.generateSecret(spec).getEncoded();

        return iterations + ":" + toHex(salt) + ":" + toHex(hash);

    }

    private byte[] getSalt() throws NoSuchAlgorithmException {

        SecureRandom sr = SecureRandom.getInstance(SECURE_KEY);

        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        return salt;

    }

    private String toHex(byte[] array) throws NoSuchAlgorithmException {

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
}
