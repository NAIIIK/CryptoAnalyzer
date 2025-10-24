package com.javarush.rodionov.cryptoanalyzer.executables;

import com.javarush.rodionov.cryptoanalyzer.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.Map;

public class Decoder implements Executable {

    private final FileHandler fileHandler;

    public Decoder() {
        fileHandler = new FileHandler();
    }

    public Decoder(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public Result execute(String[] parameters) {
        String src = parameters[0];
        String dest = parameters[2];
        int key;

        try {
            key = Integer.parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            return new Result("Key must be an integer", false);
        }


        String encodedContent = fileHandler.read(Constants.PATH + src);
        if (encodedContent == null) return new Result("Source file is emmpty", false);

        char[] symbols = Constants.SYMBOLS;


        key = CipherUtils.normalizeKey(-key, symbols.length);
        Map<Character, Integer> indexMap = CipherUtils.buildSymbolMap(symbols);

        char[] encodedChars = encodedContent.toLowerCase().toCharArray();

        for (int i = 0; i < encodedChars.length; i++) {
            encodedChars[i] = CipherUtils.getShiftChar(encodedChars[i], key, symbols, indexMap);
        }

        String decodedContent = new String(encodedChars);

        try {
            fileHandler.write(decodedContent, Constants.PATH + dest);
            return new Result("Decoding completed successfully", true);
        } catch (AppException e) {
            return new Result("Something went wrong", false);
        }
    }
}
