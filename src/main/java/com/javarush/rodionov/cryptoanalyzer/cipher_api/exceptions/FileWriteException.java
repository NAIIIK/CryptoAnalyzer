package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

/**
 * Класс FileWriteException наследует AppException.
 * Создан для перехвата ошибки записи файла.
 */
public class FileWriteException extends AppException {
    public FileWriteException(String fileName, Throwable cause) {
        super("Ошибка записи файла: " + fileName, cause);
    }
}
