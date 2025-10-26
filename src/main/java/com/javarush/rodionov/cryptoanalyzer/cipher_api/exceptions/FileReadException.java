package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

/**
 * Класс FileReadException наследует AppException.
 * Создан для перехвата ошибки чтения файла.
 */
public class FileReadException extends AppException {
    public FileReadException(String fileName, Throwable cause) {
        super("Ошибка чтения файла: " + fileName, cause);
    }
}
