package com.javarush.rodionov.cryptoanalyzer.exceptions;

public class FileWriteException extends AppException {
    public FileWriteException(String fileName, Throwable cause) {
        super("Error writing file: " + fileName, cause);
    }
}
