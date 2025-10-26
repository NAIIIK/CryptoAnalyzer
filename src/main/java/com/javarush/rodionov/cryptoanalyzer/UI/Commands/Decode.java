package com.javarush.rodionov.cryptoanalyzer.UI.Commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

/**
 * Команда для выполнения операции дешифрования содержимого файла.
 *
 * <p>Класс представляет конкретную реализацию команды в паттерне Command.
 * При выполнении команды вызывается метод {@link ConsoleUI#decode()},
 * который инициирует процесс запроса данных у пользователя и делегирует
 * операцию дешифрования соответствующим компонентам приложения.</p>
 */
public class Decode extends Command {

    public Decode(ConsoleUI consoleUI) {
        super("Дешифровать содержимое файла", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().decode();
    }
}
