package com.crypto.exception;

/**
 * Created by Semih, 3.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@SuppressWarnings("serial")
public class WhitelistAuthenticationTokenException extends RuntimeException {

    public WhitelistAuthenticationTokenException(Exception pCause) {
        super(pCause);
    }

    public WhitelistAuthenticationTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public WhitelistAuthenticationTokenException(String message) {
        super(message);
    }{

    }
}
