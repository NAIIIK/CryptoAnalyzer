package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

/**
 * Класс AppException наследует RuntimeException.
 * Создан для удобства перехвата кастомных исключений.
 */
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
