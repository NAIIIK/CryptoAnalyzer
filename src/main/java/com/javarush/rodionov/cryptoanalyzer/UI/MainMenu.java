package com.javarush.rodionov.cryptoanalyzer.UI;

import com.javarush.rodionov.cryptoanalyzer.UI.commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Главное меню консольного интерфейса,
 * предоставляющее пользователю доступ к доступным командам приложения.
 *
 * <p>Экземпляр меню формируется на основе списка команд
 * и используется для отображения доступных операций и выполнения
 * выбранной пользователем команды.</p>
 */
public class MainMenu {
    private final List<Command> commands;

    public MainMenu(ConsoleUI consoleUI) {
        this.commands = new ArrayList<>();
        commands.add(new Encode(consoleUI));
        commands.add(new Decode(consoleUI));
        commands.add(new BruteForceDecode(consoleUI));
        commands.add(new Exit(consoleUI));
    }

    /**
     * Формирует и возвращает многострочную текстовую строку,
     * представляющую пункты меню для отображения пользователю.
     *
     * <p>Каждая строка содержит:
     * <ul>
     *     <li>Порядковый номер пункта</li>
     *     <li>Описание команды</li>
     * </ul>
     *
     *
     * @return строка с визуализацией меню
     */
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

    /**
     * Выполняет команду, выбранную пользователем,
     * согласно переданному номеру пункта меню.
     *
     * @param choice номер команды, начиная с 1
     */
    public void executeCommand(int choice) {
        Command command = commands.get(choice - 1);
        command.execute();
    }

    public int getSize() {
        return commands.size();
    }
}
