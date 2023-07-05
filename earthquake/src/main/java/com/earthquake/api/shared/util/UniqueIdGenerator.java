package com.earthquake.api.shared.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Semih, 5.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Component
@NoArgsConstructor
public class UniqueIdGenerator {

    private static final String SEPARATOR = "/";

    private final static Random random = new SecureRandom();

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!?&";

    private final static int LENGTH = 6;

    private final static String NUMBERS = "0123456789";

    public static String uniqueId() {
        return UUID.randomUUID().toString() + SEPARATOR + generateRandomString();
    }


    public static String generateRandomString() {

        StringBuilder id = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {

            if (i < 3) {
                id.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            }
            else {
                id.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }

        }
        return new String(id);

    }

    public static String generateRandom() {

        StringBuilder id = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            id.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }

        return new String(id);

    }
}
