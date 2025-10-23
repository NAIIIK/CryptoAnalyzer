package com.javarush.rodionov.cryptoanalyzer;

import com.javarush.rodionov.cryptoanalyzer.controllers.MainController;
import com.javarush.rodionov.cryptoanalyzer.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.exceptions.IllegalParameterException;

import java.util.Arrays;

public class Application {

    private final MainController controller;

    public Application() {
        controller = new MainController();
    }

    public Result run(String[] args) {
        if (args.length > 0) {
            String command = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return controller.executeCommand(command, parameters);
        } else throw new IllegalParameterException();
    }
}
