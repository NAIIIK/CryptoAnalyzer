package com.javarush.rodionov.cryptoanalyzer.executables;

import com.javarush.rodionov.cryptoanalyzer.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.HashMap;
import java.util.Map;

public class BruteForce implements Executable {
    @Override
    public Result execute(String[] parameters) {
        FileHandler fileHandler = new FileHandler();

        String encodedContent = fileHandler.read(Constants.PATH + parameters[0]);

        char[] original = encodedContent.toCharArray();
        char[] symbols = Constants.SYMBOLS;

        Map<Character, Integer> indexOfChar = CipherUtils.buildSymbolMap(symbols);

        int bestKey = 0;
        int bestScore = 0;

        for (int shift = 0; shift <= symbols.length; shift++) {

            char[] map = new char[symbols.length];
            for (int j = 0; j < symbols.length; j++) {
                map[j] = symbols[j - shift < 0 ? j - shift + symbols.length : j - shift];
            }

            char[] decoded = new char[original.length];
            for (int i = 0; i < decoded.length; i++) {
                Integer pos = indexOfChar.get(original[i]);
                decoded[i] = pos != null ? map[pos] : original[i];
            }

            int score = scoreByCommaSpace(decoded);

            if (score > bestScore) {
                bestScore = score;
                bestKey = shift;
            }
        }

        parameters[1] = String.valueOf(bestKey);

        Executable decoder = new Decoder();

        Result result = decoder.execute(parameters);

        if (result.isSuccess()) {
            result.setMessage("Brute force decoding completed successfully, the key is " + bestKey);
        }

        return result;
    }

    private int scoreByCommaSpace(char[] text) {
        int counter = 0;

        for (int i = 0; i < text.length - 1; i++) {
            if (text[i] == ',' && text[i + 1] == ' ') counter += 1;
        }

        return counter;
    }
}
