package com.javarush.rodionov.cryptoanalyzer.executables;

import com.javarush.rodionov.cryptoanalyzer.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.Map;

public class Encoder implements Executable {
    @Override
    public Result execute(String[] parameters) {
        FileHandler fileHandler = new FileHandler();

        String src = parameters[0];
        String dest = parameters[2];
        int key;

        try {
            key = Integer.parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return new Result("Key must be an integer", false);
        }

        String content = fileHandler.read(Constants.PATH + src);
        if (content == null) return new Result("Source file is empty", false);

        char[] symbols = Constants.SYMBOLS;

        Map<Character, Integer> indexMap = CipherUtils.buildSymbolMap(symbols);
        key = CipherUtils.normalizeKey(key, symbols.length);

        char[] contentChars = content.toLowerCase().toCharArray();
        for (int i = 0; i < contentChars.length; i++) {
            contentChars[i] = CipherUtils.getShiftChar(contentChars[i], key, symbols, indexMap);
        }

        String encodedContent = new String(contentChars);

        try {
            fileHandler.write(encodedContent, Constants.PATH + dest);
            return new Result("Encoding completed successfully", true);
        } catch (AppException e) {
            return new Result("Something went wrong", false);
        }
    }
}
