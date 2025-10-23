package com.javarush.rodionov.cryptoanalyzer.entities;

import com.javarush.rodionov.cryptoanalyzer.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.executables.*;

public enum Commands {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce());

    private final Executable executable;

    Commands (Executable executable) {
        this.executable = executable;
    }

    public static Executable find(String commandName) {
        try {
            Commands value = Commands.valueOf(commandName.toUpperCase());
            return value.executable;
        } catch (IllegalArgumentException e) {
            throw new AppException();
        }
    }
}
