package com.javarush.rodionov.cryptoanalyzer.cipher_api.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Утилитарный класс для выполнения вспомогательных операций,
 * связанных с циклическим сдвигом символов в массиве алфавита.
 *
 * <p>Класс содержит только статические методы и не предполагает
 * создание экземпляров, что гарантируется приватным конструктором.</p>
 */
public final class CipherUtils {

    private CipherUtils() {}

    /**
     * Строит отображение символа в его индекс в массиве допустимых символов.
     *
     * @param symbols массив символов, представляющий алфавит шифра.
     *                Порядок элементов определяет логику циклического сдвига
     * @return отображение, сопоставляющее каждому символу его индекс в массиве
     * @throws NullPointerException если symbols равен null
     */
    public static Map<Character, Integer> buildSymbolMap(char[] symbols) {
        Map<Character, Integer> indexOfChar = new HashMap<>();

        for (int i = 0; i < symbols.length; i++) {
            indexOfChar.put(symbols[i], i);
        }

        return indexOfChar;
    }

    /**
     * Выполняет циклический сдвиг символа на указанный ключ в рамках заданного алфавита.
     *
     * <p>Если символ отсутствует в карте indexOf, он возвращается без изменений.</p>
     *
     * @param c символ для обработки
     * @param key значение сдвига (может быть отрицательным)
     * @param symbols массив символов, задающий алфавит
     * @param indexOf отображение символов в их индексы в массиве symbols
     * @return новый символ после применения циклического сдвига;
     *         если исходный символ отсутствует в алфавите, возвращается без изменений
     * @throws NullPointerException если symbols или indexOf равны null
     */
    public static char getShiftChar(char c, int key, char[] symbols, Map<Character, Integer> indexOf) {
        Integer pos = indexOf.get(c);
        if (pos == null) return c;
        int newPos = (pos + key + symbols.length) % symbols.length;
        return symbols[newPos];
    }

    /**
     * Нормализует значение ключа, приводя его в диапазон от 0 до symbolsLength - 1.
     *
     * <p>Позволяет корректно работать с ключами,
     * которые превышают диапазон алфавита или имеют отрицательные значения.</p>
     *
     * @param key исходное значение ключа
     * @param symbolsLength длина алфавита, используемого при шифровании
     * @return нормализованный ключ в диапазоне [0, symbolsLength - 1]
     * @throws ArithmeticException если symbolsLength равен 0
     */
    public static int normalizeKey(int key, int symbolsLength) {
        return ((key % symbolsLength) + symbolsLength) % symbolsLength;
    }
}
