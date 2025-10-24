package com.javarush.rodionov.cryptoanalyzer.cipher_api.service;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables.BruteForce;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables.Decoder;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables.Encoder;

public class Service {
    private final Encoder encoder;
    private final Decoder decoder;
    private final BruteForce bruteForce;

    public Service() {
        encoder = new Encoder();
        decoder = new Decoder();
        bruteForce = new BruteForce();
    }

    public Result executeEncode(String src, int key, String dest) {
        return encoder.execute(src, key, dest);
    }

    public Result executeDecode(String src, int key, String dest) {
        return decoder.execute(src, key, dest);
    }

    public Result executeBruteForce(String src, String dest) {
        return bruteForce.execute(src, 0, dest);
    }
}
