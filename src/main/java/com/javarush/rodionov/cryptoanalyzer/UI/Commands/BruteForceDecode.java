package com.javarush.rodionov.cryptoanalyzer.UI.Commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

/**
 * Команда для выполнения операции автоматического подбора ключа (brute-force)
 * и последующего дешифрования содержимого файла.
 *
 * <p>Реализация является конкретной командой в рамках паттерна Command.
 * При выполнении команды вызывается метод {@link ConsoleUI#bruteForce()},
 * который инициирует взаимодействие с пользователем (запрос имен файлов и т.д.)
 * и делегирует операцию компоненту, выполняющему перебор ключей и декодирование.</p>
 */
public class BruteForceDecode extends Command {

    public BruteForceDecode(ConsoleUI consoleUI) {
        super("Взломать шифр", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().bruteForce();
    }
}
