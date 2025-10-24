package com.javarush.rodionov.cryptoanalyzer.presenter;

import com.javarush.rodionov.cryptoanalyzer.UI.View;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.entities.Result;
import com.javarush.rodionov.cryptoanalyzer.cipher_api.service.Service;

public class Presenter {
    private View view;
    private Service service;

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
