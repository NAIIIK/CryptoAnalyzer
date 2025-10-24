package com.javarush.rodionov.cryptoanalyzer.cipher_api.utils;

import java.util.HashMap;
import java.util.Map;

public final class CipherUtils {

    private CipherUtils() {}

    public static Map<Character, Integer> buildSymbolMap(char[] symbols) {
        Map<Character, Integer> indexOfChar = new HashMap<>();

        for (int i = 0; i < symbols.length; i++) {
            indexOfChar.put(symbols[i], i);
        }

        return indexOfChar;
    }

    public static char getShiftChar(char c, int key, char[] symbols, Map<Character, Integer> indexOf) {
        Integer pos = indexOf.get(c);
        if (pos == null) return c;
        int newPos = (pos + key + symbols.length) % symbols.length;
        return symbols[newPos];
    }

    public static int normalizeKey(int key, int symbolsLength) {
        return ((key % symbolsLength) + symbolsLength) % symbolsLength;
    }
}
