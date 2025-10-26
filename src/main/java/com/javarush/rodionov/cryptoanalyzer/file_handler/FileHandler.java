package com.javarush.rodionov.cryptoanalyzer.file_handler;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.FileReadException;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.FileWriteException;

import java.io.*;

/**
 * Утилитарный класс для простого чтения и записи текстовых файлов.
 *
 * <p>Реализация использует {@link BufferedReader}/{@link BufferedWriter} поверх
 * {@link FileReader}/{@link FileWriter} и оператор try-with-resources для безопасного
 * закрытия потоков. Методы ориентированы на работу с текстовыми файлами и
 * возвращают/принимают содержимое целиком.</p>
 *
 * <p>Особенности реализации:
 * <ul>
 *   <li>Метод {@link #read(String)} возвращает содержимое файла как одну строку,
 *       причём к каждой прочитанной строке добавляется {@link System#lineSeparator()},
 *       включая последнюю — это может привести к завершающему символу новой строки
 *       в возвращаемой строке.</li>
 *   <li>Метод {@link #write(String, String)} перезаписывает целевой файл
 *       (аналогично поведению {@link FileWriter} без флага дозаписи).</li>
 * </ul>
 *
 */
public class FileHandler {

    public String read(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line)
                        .append(System.lineSeparator());
                }
                return stringBuilder.toString();
            } catch (IOException e) {
            throw new FileReadException(fileName, e);
        }
    }

    public void write(String content, String fileName) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new FileWriteException(fileName, e);
        }
    }
}