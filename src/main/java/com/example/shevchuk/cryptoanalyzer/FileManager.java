package com.example.shevchuk.cryptoanalyzer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;

public class FileManager {
    public static char[] readFile(Path path) {
        try {
            String readF = Files.readString(Paths.get(path.toString()),StandardCharsets.UTF_8);
            char[] charText = readF.toCharArray();
            return charText;
        } catch (IOException e) {
            System.out.println("Файла по указанному пути не существует!");
        }
        return null;
    }

    public static void writeFile(char[] text, String state, Path path) {
        try {
            String fileName = path.getFileName().toString().trim();

            if(fileName.endsWith(".txt")){
                fileName = fileName.substring(0,fileName.length()-4);
            }
            if(fileName.endsWith(" ШИФР")){
                fileName = fileName.substring(0,fileName.length()-5);
            }

            String endFileName = fileName+" " + state + ".txt";
            Path endPath = path.getParent().resolve(endFileName);
            String con = new String(text);

            Files.write(endPath, con.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println(state);
        } catch (IOException e) {
            System.out.println("Указанного пути не существует");
        }
    }

    public static HashSet readRusWordbook() {
        try {
            List<String> readF = Files.readAllLines(Paths.get("RUS111.txt"));
            HashSet set = new HashSet<>(readF);
            return set;
        } catch (IOException e) {
            System.out.println("Файла по указанному пути не существует!");
        }
        return null;
    }
}


