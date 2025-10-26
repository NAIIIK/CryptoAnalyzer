package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

/**
 * Класс IllegalParameterException наследует AppException.
 * Создан для перехвата ошибки параметра.
 */
public class IllegalParameterException extends AppException {
    public IllegalParameterException (Throwable cause) {
        super("Ошибка параметра: ", cause);
    }
}
