package com.javarush.rodionov.cryptoanalyzer;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;
import com.javarush.rodionov.cryptoanalyzer.UI.View;

public class ApplicationRunner {
    public static void main(String[] args) {
        View view = new ConsoleUI();
        view.start();
    }
}
