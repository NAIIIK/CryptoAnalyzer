package com.javarush.rodionov.cryptoanalyzer.UI.Commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

public class Decode extends Command {

    public Decode(ConsoleUI consoleUI) {
        super("Дешифровать содержимое файла", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().decode();
    }
}
