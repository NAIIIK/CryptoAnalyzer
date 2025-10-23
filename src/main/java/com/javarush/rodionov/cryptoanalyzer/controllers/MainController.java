package com.javarush.rodionov.cryptoanalyzer.controllers;

import com.javarush.rodionov.cryptoanalyzer.entities.Commands;
import com.javarush.rodionov.cryptoanalyzer.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.executables.Executable;

public class MainController {

    public Result executeCommand(String commandName, String[] parameters) {
        Executable command = Commands.find(commandName);
        return command.execute(parameters);
    }
}
