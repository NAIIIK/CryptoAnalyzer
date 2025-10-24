package com.javarush.rodionov.cryptoanalyzer.file_handler;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.FileReadException;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.FileWriteException;

import java.io.*;

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