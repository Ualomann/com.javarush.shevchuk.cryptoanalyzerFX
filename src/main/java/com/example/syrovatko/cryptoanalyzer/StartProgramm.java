package com.example.syrovatko.cryptoanalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StartProgramm {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Здравствуйте, что вы хотите сделать ?");
        System.out.println("1.Зашифровать текст.\n" +
                "2.Расшифровать текст.\n" +
                "3.Brut Force");
        System.out.println("Введите число:");
        String choise = console.nextLine();
        if(choise.equals(1+"")){
            System.out.println("Введите путь к файлу который хотите зашифровать:");
            Path path = Paths.get(console.nextLine());
            System.out.println("Введите ключ шифровки(Число):");
            int key = console.nextInt();
            Sipher.encryptDecrypt(key,path,true);
        }
        else if(choise.equals(2+"")){
            System.out.println();
            System.out.println("Введите путь к файлу который хотите расшифровать:");
            Path path = Paths.get(console.nextLine());
            System.out.println("Введите ключ расшифровки:");
            int key = console.nextInt();
            Sipher.encryptDecrypt(key,path,false);
        }
        else if(choise.equals(3+"")){
            System.out.println();
            System.out.println("Введите путь к файлу который хотите брутфорсить:");
            Path path = Paths.get(console.nextLine());
            BruteForce.bruteForce(path);
        }
        else{
            System.out.println("Неправильный ввод");
        }
    }
}
