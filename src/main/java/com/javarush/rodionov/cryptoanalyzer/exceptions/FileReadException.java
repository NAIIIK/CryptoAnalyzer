package com.javarush.rodionov.cryptoanalyzer.exceptions;

public class FileReadException extends AppException {
    public FileReadException(String fileName, Throwable cause) {
        super("Error reading file: " + fileName, cause);
    }
}
