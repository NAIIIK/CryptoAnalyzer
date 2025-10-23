package com.javarush.rodionov.cryptoanalyzer.executables;

import com.javarush.rodionov.cryptoanalyzer.constants.Constants;
import com.javarush.rodionov.cryptoanalyzer.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.HashMap;
import java.util.Map;

public class Encoder implements Executable {
    @Override
    public Result execute(String[] parameters) {
        FileHandler fileHandler = new FileHandler();

        String src = parameters[0];
        int key = Integer.parseInt(parameters[1]);
        String dest = parameters[2];

        String content = fileHandler.read(Constants.PATH + src);

        if (content == null) return new Result("Source file is empty", false);

        char[] symbols = Constants.SYMBOLS;
        int len = symbols.length;

        int shift = ((key % len) + len) % len;

        Map<Character, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < len; i++) {
            indexMap.put(symbols[i], i);
        }

        StringBuilder stringBuilder = new StringBuilder(content.length());
        for (char c : content.toLowerCase().toCharArray()) {
            Integer pos = indexMap.get(c);
            if (pos == null) stringBuilder.append(c);
            else {
                char encoded = symbols[(pos + shift) % len];
                stringBuilder.append(encoded);
            }
        }

        String encodedContent = stringBuilder.toString();

        try {
            fileHandler.write(encodedContent, Constants.PATH + dest);
            return new Result("Encoding completed successfully", true);
        } catch (AppException e) {
            return new Result("Something went wrong", false);
        }
    }
}
