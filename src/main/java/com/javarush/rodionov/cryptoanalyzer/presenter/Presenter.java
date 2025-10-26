package com.javarush.rodionov.cryptoanalyzer.presenter;

import com.javarush.rodionov.cryptoanalyzer.UI.View;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.service.Service;

/**
 * Presenter — слой представления, отвечающий за координацию взаимодействия
 * между пользовательским интерфейсом ({@code View}) и бизнес-логикой ({@code Service}).
 *
 * <p>Класс делегирует выполнение операций шифрования/дешифрования/взлома
 * эквивалентным методам {@link Service} и выводит пользователю результат
 * через интерфейс {@link View}.</p>
 *
 * <p>Presenter является легковесным фасадом: не содержит бизнес-логики,
 * не выполняет преобразований данных и не бросает исключений наружу;
 * все сообщения результата операции передаются в {@code View} методом
 * {@link View#printMessage(String)}.</p>
 */
public class Presenter {
    private final View view;
    private final Service service;

    public Presenter(View view) {
        this.view = view;
        this.service = new Service();
    }

    public void executeEncode(String src, int key, String dest) {
        Result result = service.executeEncode(src, key, dest);
        view.printMessage(result.getMessage());
    }

    public void executeDecode(String src, int key, String dest) {
        Result result = service.executeDecode(src, key, dest);
        view.printMessage(result.getMessage());
    }

    public void executeBruteForce(String src, String dest) {
        Result result = service.executeBruteForce(src, dest);
        view.printMessage(result.getMessage());
    }
}
