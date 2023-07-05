package com.crypto.service.hash;

import com.crypto.utils.CryptoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.function.Consumer;

/**
 * Created by Semih, 26.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class EncryptorAesGcmService implements Consumer<String> {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

    private static final int TAG_LENGTH_BIT = 128;

    private static final int IV_LENGTH_BYTE = 12;

    private static final int AES_KEY_BIT = 256;

    private static final String OUTPUT_FORMAT = "%-30s:%s";

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    @Override
    public void accept(String pText) {

        //String pText = "car wrong salvage damage operation voice help customer fish hospital";

        // encrypt and decrypt need the same key.
        // get AES 256 bits (32 bytes) key
        try {

            final SecretKey secretKey = CryptoUtils.getAESKey(AES_KEY_BIT);

            // encrypt and decrypt need the same IV.
            // AES-GCM needs IV 96-bit (12 bytes)
            byte[] iv = CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);

            byte[] encryptedText = encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);

            final String encryptString = encryptString(pText, secretKey, iv);
            final String decryptString = decryptString(encryptString, secretKey, iv);

            log.info("\n------ AES GCM Encryption ------");
            log.info(encryptString);

            log.info(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
            log.info(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));
            log.info(String.format(OUTPUT_FORMAT, "IV  (hex)", CryptoUtils.hex(iv)));
            log.info(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", CryptoUtils.hex(encryptedText)));
            log.info(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));

            log.info("\n------ AES GCM Decryption ------");
            log.info(String.format(OUTPUT_FORMAT, "Input (hex)", CryptoUtils.hex(encryptedText)));
            log.info(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));
            log.info(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));

            String decryptedText = decryptWithPrefixIV(encryptedText, secretKey);
            log.info(decryptString);
            log.info(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    // AES-GCM needs GCMParameterSpec
    public byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;

    }

    public String encryptString(String s, SecretKey secret, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(s.getBytes(StandardCharsets.UTF_8)));
    }

    // prefix IV length + IV bytes to cipher text
    public byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws Exception {

        byte[] cipherText = encrypt(pText, secret, iv);

        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
                .put(iv)
                .put(cipherText)
                .array();
        return cipherTextWithIv;

    }

    public String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, UTF_8);

    }

    public String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(cText);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        //bb.get(iv, 0, iv.length);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;

    }

    public String decryptString(String ss, SecretKey secret, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        return new String(cipher.doFinal(Base64.getDecoder().decode(ss)));

    }
}
