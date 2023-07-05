package com.earthquake.api.exception;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@SuppressWarnings("serial")
public class CoreKandilliException extends RuntimeException {

    public CoreKandilliException(Exception pCause) {
        super(pCause);
    }

    public CoreKandilliException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreKandilliException(String message) {
        super(message);
    }
}