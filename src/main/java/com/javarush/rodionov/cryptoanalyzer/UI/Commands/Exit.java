package com.javarush.rodionov.cryptoanalyzer.UI.Commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

public class Exit extends Command {

    public Exit(ConsoleUI consoleUI) {
        super("Выход", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().quit();
    }
}
