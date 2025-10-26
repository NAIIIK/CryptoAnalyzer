package com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.executables;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;

/**
 * Executable — базовый интерфейс для компонентов, выполняющих преобразование файлового содержимого
 * (шифрование/дешифрование/взлом и т.п.).
 *
 * <p>Реализации читают входной файл (параметр {@code src}), выполняют преобразование с учётом
 * параметра {@code key} и записывают результат в файл {@code dest}. Результат операции
 * возвращается в объекте {@link Result}.</p>
 */
public interface Executable {
    Result execute(String src, int key, String dest);
}
