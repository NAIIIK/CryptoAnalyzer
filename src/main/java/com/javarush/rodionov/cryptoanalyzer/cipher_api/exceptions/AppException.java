package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

public class AppException extends RuntimeException {
    public AppException() {
        super();
    }

    public AppException(String message) {
        super((message));
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
