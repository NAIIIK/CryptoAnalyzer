package com.javarush.rodionov.cryptoanalyzer.cipher_api.utils;

import java.io.File;

/**
 * Утилитарный класс, содержащий глобальные константы, используемые в модуле
 * шифрования/дешифрования.
 *
 * <p>Класс не предназначен для создания экземпляров (приватный конструктор).
 * Поля — публичные статические константы, которые определяют набор допустимых
 * символов (алфавиты + знаки пунктуации) и базовый путь к ресурсам.</p>
 *
 * <p>Порядок символов в {@link #SYMBOLS} критичен для корректной работы сдвигового
 * шифра: индексы символов и операции сдвига рассчитываются относительно этого
 * порядка.</p>
 */
public final class Constants {

    private Constants() {}

    private static final String RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String EN = "abcdefghijklmnopqrstuvwxyz";
    private static final String CH = ".,:;-\" ";

    public static final char[] SYMBOLS = (RU + EN + CH).toCharArray(); // length = 66

    public static final String PATH = System.getProperty("user.dir") + File.separator + "resources" + File.separator;
}
