package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

public class IllegalParameterException extends AppException {
    public IllegalParameterException (Throwable cause) {
        super("Ошибка параметра: ", cause);
    }
}
