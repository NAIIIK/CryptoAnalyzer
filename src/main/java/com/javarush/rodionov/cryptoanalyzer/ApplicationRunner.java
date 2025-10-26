package com.javarush.rodionov.cryptoanalyzer;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;
import com.javarush.rodionov.cryptoanalyzer.UI.View;

/**
 * Точка входа в приложение.
 *
 * <p>Класс инициализирует консольный пользовательский интерфейс
 * и запускает основной цикл взаимодействия с пользователем.</p>
 */
public class ApplicationRunner {
    public static void main(String[] args) {
        View view = new ConsoleUI();
        view.start();
    }
}
