package com.example.syrovatko.cryptoanalyzer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Validator {
    public static void isValidKey(int key){
        try{}catch(InputMismatchException e){
            System.out.println("Неправильно указан ключ, ключ является числом!");
        }
    }

    public static void isEmpty(Path path) {
        if (!Files.exists(path)) {
            System.out.println("Файла по такому пути не существует!");
            System. exit(0);
        }
    }
    public static void isEmpty(char[] text) {
        if (text.length == 0 || text == null) {
            System.out.println("Файл пуст!");
            System. exit(0);
        }
    }
}
