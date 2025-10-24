package com.javarush.rodionov.cryptoanalyzer.UI.Commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

public class BruteForceDecode extends Command {

    public BruteForceDecode(ConsoleUI consoleUI) {
        super("Взломать шифр", consoleUI);
    }

    @Override
    public void execute() {
        getConsoleUI().bruteForce();
    }
}
