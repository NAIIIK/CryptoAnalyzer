package com.javarush.rodionov.cryptoanalyzer.UI.commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

/**
 * Команда для выполнения операции шифрования содержимого файла.
 *
 * <p>Класс представляет конкретную реализацию команды в паттерне Command.
 * При выполнении команды вызывается метод {@link ConsoleUI#encode()},
 * который инициирует процесс запроса данных у пользователя и делегирует
 * операцию шифрования соответствующим компонентам приложения.</p>
 */
public class Encode extends Command {

    public Encode(ConsoleUI consoleUI) {
        super("Зашифровать содержимое файла", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().encode();
    }
}
