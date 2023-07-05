package com.crypto.exception;

/**
 * Created by Semih, 1.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@SuppressWarnings("serial")
public class CryptoException extends RuntimeException {

    public CryptoException(Exception pCause) {
        super(pCause);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoException(String message) {
        super(message);
    }
}
