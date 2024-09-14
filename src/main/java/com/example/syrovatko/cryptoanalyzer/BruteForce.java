package com.example.syrovatko.cryptoanalyzer;

import java.nio.file.Path;
import java.util.HashSet;


public class BruteForce {


    public static void bruteForce(Path path) {
        Validator.isEmpty(path);
        char[] text = FileManager.readFile(path);
        HashSet<String> wordBook = FileManager.readRusWordbook();
        Validator.isEmpty(text);
        OUTER:
        for (int key = 0; key < Alphabet.ALPHABET.length; key++) {
            char[] text2 = text.clone();


            MIDDLE:
            for (int i = 0; i < text2.length; i++) {
                boolean isUpperCase = Character.isUpperCase(text[i]);
                char aboutCase = Character.toLowerCase(text2[i]);

                INNER:
                for (int j = 0; j < Alphabet.ALPHABET.length; j++) {

                    if (Character.toLowerCase(Alphabet.ALPHABET[j]) == aboutCase) {
                        char decryptChar = Alphabet.ALPHABET[(j - key + Alphabet.ALPHABET.length) % Alphabet.ALPHABET.length];
                        text2[i] = isUpperCase? Character.toUpperCase(decryptChar):decryptChar;
                        break;
                    }
                }
            }


            String textToDecrypt = new String(text2);
            String stroka = textToDecrypt.substring(0, 60).toLowerCase();


            String[] words = stroka.split("\\s+");

            for (String word : words) {
                if (wordBook.contains(word)) {

                    text = text2;
                    break OUTER;
                }
            }
        }
        FileManager.writeFile(text, "БРУТ", path);

    }
}
