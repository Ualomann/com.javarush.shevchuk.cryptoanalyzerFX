package com.example.syrovatko.cryptoanalyzer;

import java.nio.file.Files;
import java.nio.file.Path;

public class Sipher {

    public static void encryptDecrypt(int key, Path path, boolean endDec){

        Validator.isEmpty(path);
        Validator.isValidKey(key);
        char[] text = FileManager.readFile(path);
        Validator.isEmpty(text);
        String state = "";

        for (int i = 0; i < text.length; i++) {
            boolean isUpperCase = Character.isUpperCase(text[i]);
            char aboutCase = Character.toLowerCase(text[i]);


            for (int j = 0; j < Alphabet.ALPHABET.length; j++) {

                if(Alphabet.ALPHABET[j] == aboutCase){
                    if(endDec) {
                        char newBigChar = Alphabet.ALPHABET[(j + key) % (Alphabet.ALPHABET.length)];
                        text[i] = isUpperCase ? Character.toUpperCase(newBigChar) : newBigChar;
                        state = "ШИФР";
                        break;
                    }
                    else{
                        char newBigChar = Alphabet.ALPHABET[(j-key + Alphabet.ALPHABET.length)%(Alphabet.ALPHABET.length)];
                        text[i] = isUpperCase ? Character.toUpperCase(newBigChar):newBigChar;
                        state = "ДЕШИФР";
                        break;
                    }
                   }

                }
        }
        FileManager.writeFile(text,state,path);
    }

//    public static void decrypt(int key,Path path){
//        Validator.isEmpty(path);
//        Validator.isValidKey(key);
//        char[] text = FileManager.readFile(path);
//        Validator.isEmpty(text);
//
//        for (int i = 0; i < text.length; i++) {
//            boolean isUpperCase = Character.isUpperCase(text[i]);
//            char aboutCase = Character.toLowerCase(text[i]);
//
//            for (int j = 0; j < Alphabet.ALPHABET.length; j++) {
//
//                if(Alphabet.ALPHABET[j] == aboutCase){
//                    char newBigChar = Alphabet.ALPHABET[(j-key + Alphabet.ALPHABET.length)%(Alphabet.ALPHABET.length)];
//                    text[i] = isUpperCase ? Character.toUpperCase(newBigChar):newBigChar;
//                    break;
//                }
//
//            }
//        }
//        FileManager.writeFile(text,"ДЕШИФР",path);
//    }
//

}
