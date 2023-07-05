package com.earthquake.api.exception;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@SuppressWarnings("serial")
public class TokenException extends RuntimeException {

    public TokenException(Exception pCause) {
        super(pCause);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }
}
