package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.Map;

/**
 * Encoder — реализация шифратора для сдвигового шифра (Caesar-like).
 *
 * <p>Класс читает содержимое файла, приводит текст к нижнему регистру,
 * нормализует ключ и применяет сдвиг ко всем символам, входящим в {@code Constants.SYMBOLS}.</p>
 */
public class Encoder implements Executable {

    private final FileHandler fileHandler;

    public Encoder() {
        fileHandler = new FileHandler();
    }

    public Encoder(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Выполняет шифрование содержимого файла src с ключом key и сохраняет результат в dest.
     *
     * @param src  имя исходного файла (без префикса {@code Constants.PATH})
     * @param key  целочисленный ключ сдвига (нормализуется внутри метода)
     * @param dest имя выходного файла (без префикса {@code Constants.PATH})
     * @return {@link Result} с сообщением и флагом успешности
     */
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
