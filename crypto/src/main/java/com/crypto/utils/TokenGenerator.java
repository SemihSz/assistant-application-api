package com.crypto.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Semih, 25.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Component
public class TokenGenerator {

    private final Random random = new SecureRandom();

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final static String NUMBERS = "0123456789";


    public String generateRandomString(int length) {

        StringBuilder id = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            if (i < 6) {
                id.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            }
            else {
                id.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }

        }
        return new String(id);

    }
}
