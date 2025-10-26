package com.javarush.rodionov.cryptoanalyzer.UI.commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

/**
 * Команда завершения работы приложения.
 *
 * <p>Реализация представляет конкретную команду в рамках паттерна Command.
 * При выполнении команда вызывает метод {@link ConsoleUI#quit()},
 * который отвечает за корректное завершение программы и закрытие всех
 * используемых ресурсов пользовательского интерфейса.</p>
 */
public class Exit extends Command {

    public Exit(ConsoleUI consoleUI) {
        super("Выход", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().quit();
    }
}
