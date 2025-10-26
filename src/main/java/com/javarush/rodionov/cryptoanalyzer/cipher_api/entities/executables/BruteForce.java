package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.CipherUtils;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.utils.Constants;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.file_handler.FileHandler;

import java.util.Map;

/**
 * BruteForce — реализация атаки полного перебора для сдвигового (Caesar-like) шифра.
 *
 * <p>
 *      Класс читает зашифрованный файл, пробует все возможные сдвиги по набору символов
 * {@link Constants#SYMBOLS} и выбирает лучший сдвиг по эвристической функции,
 * подсчитывающей число вхождений последовательности {@code ", "}. Затем вызывает
 * {@link Decoder} для фактического декодирования с найденным ключом и сохраняет результат.
 *
 *
 * <p>
 * Зависимости:
 *  <ul>
 *   <li>{@link FileHandler} — чтение файлов;</li>
 *   <li>{@link CipherUtils#buildSymbolMap(char[])} — карта символов → индекс;</li>
 *   <li>{@link Constants#SYMBOLS} — алфавит/таблица символов;</li>
 *   <li>{@link Decoder} — декодер, реализующий интерфейс {@code Executable}.</li>
 *  </ul>
 *
 */
public class BruteForce implements Executable {

    private final FileHandler fileHandler;

    public BruteForce() {
        fileHandler = new FileHandler();
    }

    public BruteForce(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * Выполняет атаку полного перебора и возвращает результат декодирования.
     *
     * <p>
     *     Алгоритм:
     * <ol>
     *   <li>Чтение содержимого исходного файла (путь: {@code Constants.PATH + src}).</li>
     *   <li>Перебор всех сдвигов в диапазоне {@code 0..Constants.SYMBOLS.length}.</li>
     *   <li>Для каждого сдвига построение таблицы подстановки и декодирование текста.</li>
     *   <li>Оценка декодированного текста функцией {@link #scoreByCommaSpace(char[])}.</li>
     *   <li>Выбор сдвига с максимальным значением оценки и декодирование через {@link Decoder}.</li>
     * </ol>
     *
     *
     * @param src  имя исходного зашифрованного файла (без префикса {@code Constants.PATH})
     * @param key  первоначальное значение ключа (игнорируется; метод подставляет найденный ключ)
     * @param dest имя файла для записи расшифрованного текста
     * @return {@link Result} — результат операции; содержит сообщение и флаг успеха.
     */
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
