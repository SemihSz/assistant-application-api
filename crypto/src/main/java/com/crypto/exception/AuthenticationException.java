package com.crypto.exception;

/**
 * Created by Semih, 3.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@SuppressWarnings("serial")
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(Exception pCause) {
        super(pCause);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }{

    }
}
