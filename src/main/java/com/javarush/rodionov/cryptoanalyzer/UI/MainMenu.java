package com.javarush.rodionov.cryptoanalyzer.UI;

import com.javarush.rodionov.cryptoanalyzer.UI.Commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private final List<Command> commands;

    public MainMenu(ConsoleUI consoleUI) {
        this.commands = new ArrayList<>();
        commands.add(new Encode(consoleUI));
        commands.add(new Decode(consoleUI));
        commands.add(new BruteForceDecode(consoleUI));
        commands.add(new Exit(consoleUI));
    }

    public String getMenu() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < commands.size(); i++) {
            stringBuilder.append(i + 1)
                    .append(". ")
                    .append(commands.get(i).getDescription())
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    public void executeCommand(int choice) {
        Command command = commands.get(choice - 1);
        command.execute();
    }

    public int getSize() {
        return commands.size();
    }
}
