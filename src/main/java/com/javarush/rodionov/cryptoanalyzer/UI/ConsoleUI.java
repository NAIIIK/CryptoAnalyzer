package com.javarush.rodionov.cryptoanalyzer.UI;

import com.javarush.rodionov.cryptoanalyzer.cipher_api.exceptions.AppException;
import com.javarush.rodionov.cryptoanalyzer.presenter.Presenter;

import java.util.Scanner;

public class ConsoleUI implements View{
    private final Presenter presenter;
    private final Scanner scanner;
    private boolean action;
    private final MainMenu mainMenu;

    public ConsoleUI() {
        this.presenter = new Presenter(this);
        this.scanner = new Scanner(System.in);
        this.action = true;
        this.mainMenu = new MainMenu(this);
    }

    @Override
    public void start() {
        printMessage("Добро пожаловать!\n");
        while (action) {
            printMenu();
            choice();
        }
    }

    public void printMenu() {
        System.out.println(mainMenu.getMenu());
    }

    private void choice() {
        printMessage("Выберите из доступных вариантов: ");
        String choiceStr = scanner.nextLine();
        try {
            int choice = Integer.parseInt(choiceStr);
            if (choice < 1 || choice > mainMenu.getSize()) error();
            else mainMenu.executeCommand(choice);
        } catch (NumberFormatException e) {
            error();
        }
    }

    public void encode() {
        String src = inputSrcFileName();
        String dest = inputDestFileName();
        int key = inputKeyValue();
        try {
            presenter.executeEncode(src, key, dest);
        } catch (AppException e) {
            printMessage(e.getMessage() + "\n");
        }
    }

    public void decode() {
        String src = inputSrcFileName();
        String dest = inputDestFileName();
        int key = inputKeyValue();
        try {
            presenter.executeDecode(src, key, dest);
        } catch (AppException e) {
            printMessage(e.getMessage() + "\n");
        }
    }

    public void bruteForce() {
        String src = inputSrcFileName();
        String dest = inputDestFileName();
        try {
            presenter.executeBruteForce(src, dest);
        } catch (AppException e) {
            printMessage(e.getMessage() + "\n");
        }
    }

    public void quit() {
        printMessage("Выход...");
        action = false;
    }

    private void error() {
        printMessage("Выберите из доступных вариантов (1-" + mainMenu.getSize() + ")");
    }

    private String inputSrcFileName() {
        printMessage("Введите название входного файла <src>: ");
        return scanner.nextLine();
    }

    private String inputDestFileName() {
        printMessage("Введите название файла с результатом выполнения <dest>: ");
        return scanner.nextLine();
    }

    private int inputKeyValue() {
        printMessage("Введите значение ключа: ");
        int key = 0;
        boolean valid = true;
        do {
            try {
                key = Integer.parseInt(scanner.nextLine());
                valid = false;
            } catch (NumberFormatException e) {
                printMessage("Убедитесь, что введённое значение является числом");
            }
        } while (valid);

        return key;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
