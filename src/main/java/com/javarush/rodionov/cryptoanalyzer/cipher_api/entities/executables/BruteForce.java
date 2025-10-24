package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.Map;

public class BruteForce implements Executable {

    private final FileHandler fileHandler;

    public BruteForce() {
        fileHandler = new FileHandler();
    }

    public BruteForce(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public Result execute(String src, int key, String dest) {
        String encodedContent = fileHandler.read(Constants.PATH + src);
        if (encodedContent == null || encodedContent.isEmpty()) return new Result("Отсутствует содержимое файла", false);

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

        key = bestKey;

        Executable decoder = new Decoder();

        Result result = decoder.execute(src, key, dest);

        if (result.isSuccess()) {
            result.setMessage("Взлом шифра успешно завершён. Ключ - " + bestKey + " либо " + (bestKey - symbols.length) + ". Содержимое файла дешифровано и сохранено в файл " + dest);
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
