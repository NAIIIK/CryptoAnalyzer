package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.Map;

public class Encoder implements Executable {

    private final FileHandler fileHandler;

    public Encoder() {
        fileHandler = new FileHandler();
    }

    public Encoder(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public Result execute(String src, int key, String dest) {
        String content = fileHandler.read(Constants.PATH + src);
        if (content == null || content.isEmpty()) return new Result("Отсутствует содержимое файла", false);

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
            return new Result("Содержимое файла " + src + " успешно зашифровано. Результат сохранен в файл " + dest, true);
        } catch (AppException e) {
            return new Result("Ошибка приложения: " + e.getMessage(), false);
        }
    }
}
