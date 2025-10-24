package com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions;

public class FileReadException extends AppException {
    public FileReadException(String fileName, Throwable cause) {
        super("Ошибка чтения файла: " + fileName, cause);
    }
}
