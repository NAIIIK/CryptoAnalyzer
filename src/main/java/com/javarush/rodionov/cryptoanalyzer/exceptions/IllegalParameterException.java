package com.javarush.rodionov.cryptoanalyzer.exceptions;

public class IllegalParameterException extends AppException {
    public IllegalParameterException () {
        super("Parameters were not found...");
    }
}
