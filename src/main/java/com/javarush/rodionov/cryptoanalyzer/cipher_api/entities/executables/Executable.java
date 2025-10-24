package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;

public interface Executable {
    Result execute(String src, int key, String dest);
}
