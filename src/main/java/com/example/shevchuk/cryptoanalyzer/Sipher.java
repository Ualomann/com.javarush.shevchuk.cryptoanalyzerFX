package com.example.shevchuk.cryptoanalyzer;

import java.nio.file.Path;
import java.util.Arrays;

public class Sipher {

    public static String encryptDecrypt(int key, Path path, boolean endDec, boolean isReturn) {

        Validator.isEmpty(path);
        Validator.isValidKey(key);
        char[] text = FileManager.readFile(path);
        Validator.isEmpty(text);
        String state = "";

        for (int i = 0; i < text.length; i++) {
            boolean isUpperCase = Character.isUpperCase(text[i]);
            char aboutCase = Character.toLowerCase(text[i]);


            for (int j = 0; j < Alphabet.ALPHABET.length; j++) {

                if (Alphabet.ALPHABET[j] == aboutCase) {
                    if (endDec) {
                        char newBigChar = Alphabet.ALPHABET[(j + key) % (Alphabet.ALPHABET.length)];
                        text[i] = isUpperCase ? Character.toUpperCase(newBigChar) : newBigChar;
                        state = "ШИФР";
                        break;
                    } else {
                        char newBigChar = Alphabet.ALPHABET[(j - key + Alphabet.ALPHABET.length) % (Alphabet.ALPHABET.length)];
                        text[i] = isUpperCase ? Character.toUpperCase(newBigChar) : newBigChar;
                        state = "ДЕШИФР";
                        break;
                    }
                }

            }
        }
        if (!isReturn) {
            FileManager.writeFile(text, state, path);
        } else if (isReturn) {
            if (text.length > 60) {
                String outText = new String(text);
                return outText.substring(0, 60);
            } else if (text.length < 100) {
                String outText = new String(text);
                return outText.substring(0, outText.length());
            }
        }
        return "ЕСЛИ ВЫ ЭТО ВИДИТЕ ЗНАЧИТ ЧТО-ТО ПОШЛО НЕ ТАК";
    }
}
