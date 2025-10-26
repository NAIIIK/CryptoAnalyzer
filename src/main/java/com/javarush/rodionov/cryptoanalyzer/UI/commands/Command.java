package com.javarush.rodionov.cryptoanalyzer.UI.commands;

import com.javarush.rodionov.cryptoanalyzer.UI.ConsoleUI;

/**
 * Абстрактная команда в архитектуре управления пользовательским интерфейсом.
 *
 * <p>Класс описывает объект команды, который содержит краткое текстовое
 * описание и ссылку на {@link ConsoleUI}, что позволяет выполнять действия,
 * связанные с консольным пользовательским интерфейсом.</p>
 *
 * <p>Каждая конкретная команда должна наследовать этот класс и реализовывать
 * метод {@link #execute()}, содержащий прикладную логику.</p>
 *
 * <p>Данный подход соответствует паттерну Command: позволяет инкапсулировать
 * операцию в виде объекта, упрощает расширение и масштабирование набора
 * доступных команд.</p>
 */
public abstract class Command {
    private final String description;
    private final ConsoleUI consoleUI;

    /**
     * Создаёт экземпляр команды с переданными описанием и ссылкой на UI.
     *
     * @param description краткое текстовое описание команды
     *                    для отображения пользователю
     * @param consoleUI объект пользовательского интерфейса,
     *                  в контексте которого будет выполняться команда
     */
    public Command(String description, ConsoleUI consoleUI) {
        this.description = description;
        this.consoleUI = consoleUI;
    }

    public String getDescription() {
        return description;
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }

    public abstract void execute();
}
