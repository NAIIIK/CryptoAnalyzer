package com.javarush.rodionov.cryptoanalyzer.executables;

import com.javarush.rodionov.cryptoanalyzer.entities.Result;

public interface Executable {
    Result execute(String[] args);
}
