package com.javarush.rodionov.cryptoanalyzer.UI.Commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

public class Encode extends Command {

    public Encode(ConsoleUI consoleUI) {
        super("Зашифровать содержимое файла", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().encode();
    }
}
