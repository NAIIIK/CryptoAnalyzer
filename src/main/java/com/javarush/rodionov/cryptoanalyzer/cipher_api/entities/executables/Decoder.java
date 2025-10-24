package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.AppException;
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
    public Result execute(String src, int key, String dest) {
        String encodedContent = fileHandler.read(Constants.PATH + src);
        if (encodedContent == null || encodedContent.isEmpty()) return new Result("Отсутствует содержимое файла", false);

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
            return new Result("Содержимое файла " + src + " успешно дешифровано. Результат сохранен в файл " + dest, true);
        } catch (AppException e) {
            return new Result("Ошибка приложения: " + e.getMessage(), false);
        }
    }
}
