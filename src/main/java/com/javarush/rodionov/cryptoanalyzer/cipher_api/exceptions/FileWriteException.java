package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

public class FileWriteException extends AppException {
    public FileWriteException(String fileName, Throwable cause) {
        super("Ошибка записи файла: " + fileName, cause);
    }
}
