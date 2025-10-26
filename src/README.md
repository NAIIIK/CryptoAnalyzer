# Проект криптоанализатор

## Шифр Цезаря

Репозиторий проекта «CryptoAnalyzer» — консольное приложения на Java, предоставляющее функциональность
шифрования/дешифрования файлов с помощью шифра Цезаря и автоматический подбор (взлом) ключа методом перебора
(brute-force).

**Описание**

CryptoAnalyzer — утилита для работы с простым симметричным шифром Цезаря. Позволяет:

* шифровать содержимое файлов со сдвигом (ключом);

* дешифровать файлы по заданному ключу;

* пытаться восстановить исходный текст методом полного перебора возможных ключей (взлом/cryptanalysis);

* взаимодействовать с пользователем через консоль

**Структура проекта**

    /resources
        java.txt                                                    # пример текста для шифрования
    /src
        /main
            /java
                /com/javarush/rodionov/cryptoanalyzer
                    /cipher_api
                        /entities
                            /executables
                                BruteForce.java                     # реализация взлома шифра
                                Decoder.java                        # реализация дешифрования
                                Encoder.java                        # реализация шифрования
                                Executable.java
                            Result.java
                    /file_handler
                        FileHandler.java                            # чтение/запись файлов
                    /presenter
                        Presenter.java
                    /UI
                        /commands                                   # комманды для меню
                            BruteForceDecode.java
                            Command.java
                            Decode.java
                            Encode.java
                            Exit.java
                        ConsoleUI.java                              # реализация взаимодействия с пользователем через консоль
                        MainMenu.java                               # меню комманд доступных пользователю
                        View.java
                    ApplicationRunner.java                          # точка входа
        README.md

### **!!!ВАЖНО**

Файлы для шифрования должны находиться в директории **_resources_**, результаты выполнения программы
в виде файлов также будут создаваться именно там.