package com.javarush.rodionov.cryptoanalyzer.utils;

import java.io.File;

public final class Constants {

    private Constants() {}

    private static final String RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String EN = "abcdefghijklmnopqrstuvwxyz";
    private static final String CH = ".,:;-\" ";

    public static final char[] SYMBOLS = (RU + EN + CH).toCharArray(); // length = 66

    public static final String PATH = System.getProperty("user.dir") + File.separator + "resources" + File.separator;
}
