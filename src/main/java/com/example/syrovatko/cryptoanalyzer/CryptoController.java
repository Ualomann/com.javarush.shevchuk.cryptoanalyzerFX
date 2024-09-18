package com.example.syrovatko.cryptoanalyzer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CryptoController {
    ///////////////////////////////////////////////////ШИФРОВКА ////////////////////////////////////////////////////
    private int key;
    private File file;
    @FXML
    private TextField keyField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;
    @FXML
    private Label okFileLabel;

    @FXML
    private Button encryptButton;


    @FXML
    protected void onOpenFileButtonClick() {


        // ПО НАЖАТИЮ НА КНОПКУ "ОТКРЫТЬ ФАЙЛ" ИЗ ИНТЕРФЕЙСА (fxml)
        // ВЫЗЫВАЕТСЯ МЕТОД КОНТРОЛЛЕРА onOpenFileButtonClick()
        FileChooser fileChooser = new FileChooser();
        // УСТАНАВЛИВАЕМ ЗАГОЛОВОК ОКНА
        fileChooser.setTitle("ОТКРЫТЬ ФАЙЛ");
        // УСТАНАВЛИВАЕМ ФИЛЬТР КОТОРЫЙ ОТСЕКАЕТ ВСЕ ФОРМАТЫ КРОМЕ TXT
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)","*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        // В МОМЕНТ ВЫБОРА ФАЙЛА ПУТЬ К НЕМУ ЗАПИШЕТСЯ В ПЕРЕМЕННУЮ file
        file = fileChooser.showOpenDialog(new Stage());
        //Проверка на случай если пользователь проведёт какие-то манипуляции с файлом обходя фильтр
        if(file!=null){
            if (!file.getName().endsWith(".txt")){
                errorLabel.setText("Неверный формат файла. Пожалуйста, выберите .txt файл.");
                file = null;
                encryptButton.setDisable(true);
            }
        }
        else{
            System.out.println(file);
            errorLabel.setText("");
            checkIfReady();
        }
        okFileLabel.setText("✔");
        if(file == null){
            okFileLabel.setText("");
        }

        // ВЫВОДИМ ИМЯ ФАЙЛА В КОНСОЛЬ, ИЛИ ИСПОЛЬЗУЕМ ПО НАЗНАЧЕНИЮ
        System.out.println(file);
        checkIfReady();
    }

        @FXML
        protected void onEncryptButtonClick () {
            System.out.println("test button");
            handleEncryptButton();
            // Блокировка кнопки и сброс состояния после шифрования
            encryptButton.setDisable(true);
            keyField.clear(); // Сброс поля ключа, если необходимо
        }


    @FXML
    private void handleEncryptButton() {
        errorLabel.setText("");
        successLabel.setText("");
        okFileLabel.setText("");
        try {
            key = Integer.parseInt(keyField.getText()); // Присваиваем значение ключа
            if (file.toPath() != null) {
                System.out.println("Ключ: " + key + ", Файл: " + file.getAbsolutePath());
                errorLabel.setText("");
                Sipher.encryptDecrypt(key, file.toPath(),true);
                successLabel.setText("Текст зашифрован!");
            } else if(file.toPath() == null){
                errorLabel.setText("Файл не выбран");
            }
        } catch (NumberFormatException e) {
            successLabel.setText("");
            errorLabel.setText("Некорректный ключ");
        }
    }
    @FXML
    private void checkIfReady() {
        String keyInput = keyField.getText();
        boolean isKeyValid = !keyInput.isEmpty(); // Проверяем, введён ли ключ
        boolean isFileSelected = file != null; // Проверяем, выбран ли файл

        // Разблокируем кнопку, если оба условия выполнены
        encryptButton.setDisable(!(isKeyValid && isFileSelected));
        if (isKeyValid && isFileSelected) {
            successLabel.setText(""); // Очищаем надпись о неправильном ключе
            errorLabel.setText("");
        }
    }

    ///////////////////////////////////////////////////ДЕШИФРОВКА ////////////////////////////////////////////////////
    @FXML
    private Label errorLabel2;
    @FXML
    private Label successLabelDecrypt;
    @FXML
    private Button decryptButton2;
    @FXML
    private Label okFileLabel2;
    @FXML
    private TextField keyField2;

    @FXML
    protected void onOpenFileButtonClick2() {


        // ПО НАЖАТИЮ НА КНОПКУ "ОТКРЫТЬ ФАЙЛ" ИЗ ИНТЕРФЕЙСА (fxml)
        // ВЫЗЫВАЕТСЯ МЕТОД КОНТРОЛЛЕРА onOpenFileButtonClick()
        FileChooser fileChooser = new FileChooser();
        // УСТАНАВЛИВАЕМ ЗАГОЛОВОК ОКНА
        fileChooser.setTitle("ОТКРЫТЬ ФАЙЛ");
        // УСТАНАВЛИВАЕМ ФИЛЬТР КОТОРЫЙ ОТСЕКАЕТ ВСЕ ФОРМАТЫ КРОМЕ TXT
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)","*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        // В МОМЕНТ ВЫБОРА ФАЙЛА ПУТЬ К НЕМУ ЗАПИШЕТСЯ В ПЕРЕМЕННУЮ file
        file = fileChooser.showOpenDialog(new Stage());
        //Проверка на случай если пользователь проведёт какие-то манипуляции с файлом обходя фильтр
        if(file!=null){
            if (!file.getName().endsWith(".txt")){
                errorLabel2.setText("Неверный формат файла. Пожалуйста, выберите .txt файл.");
                file = null;
                decryptButton2.setDisable(true);
            }
        }
        else{
            System.out.println(file);
            errorLabel2.setText("");
            checkIfReadyDecrypt();
        }
        okFileLabel2.setText("✔");
        if(file == null){
            okFileLabel2.setText("");
        }

        // ВЫВОДИМ ИМЯ ФАЙЛА В КОНСОЛЬ, ИЛИ ИСПОЛЬЗУЕМ ПО НАЗНАЧЕНИЮ
        System.out.println(file);
        checkIfReady();
    }

    @FXML
    protected void onDecryptButtonClick () {
        System.out.println("test button");
        handleDecryptButton();
        // Блокировка кнопки и сброс состояния после дешифрования
        decryptButton2.setDisable(true);
        keyField2.clear(); // Сброс поля ключа, если необходимо
    }


    @FXML
    private void handleDecryptButton() {
        errorLabel2.setText("");
        successLabelDecrypt.setText("");
        okFileLabel2.setText("");
        try {
            key = Integer.parseInt(keyField2.getText()); // Присваиваем значение ключа
            if (file.toPath() != null) {
                System.out.println("Ключ: " + key + ", Файл: " + file.getAbsolutePath());
                errorLabel2.setText("");
                Sipher.encryptDecrypt(key, file.toPath(),false);
                successLabelDecrypt.setText("Текст Расшифрован!");
            } else if(file.toPath() == null){
                errorLabel2.setText("Файл не выбран");
            }
        } catch (NumberFormatException e) {
            successLabelDecrypt.setText("");
            errorLabel2.setText("Некорректный ключ");
        }
    }

    @FXML
    private void checkIfReadyDecrypt() {
        String keyInput = keyField2.getText();
        boolean isKeyValid = !keyInput.isEmpty(); // Проверяем, введён ли ключ
        boolean isFileSelected = file != null; // Проверяем, выбран ли файл

        // Разблокируем кнопку, если оба условия выполнены
        decryptButton2.setDisable(!(isKeyValid && isFileSelected));
        if (isKeyValid && isFileSelected) {
            successLabelDecrypt.setText(""); // Очищаем надпись о неправильном ключе
            errorLabel2.setText("");
        }
    }
    /////////////////////////////////////////////////////////////// БРУТФОРС////////////////////////////////////////////

    @FXML
    private Button bruteForceButton;
    @FXML
    private Label errorLabel3;
    @FXML
    private Label successLabelBrutForce;
    @FXML
    private Label okFileLabel3;

    @FXML
    protected void onOpenFileButtonClick3() {


        // ПО НАЖАТИЮ НА КНОПКУ "ОТКРЫТЬ ФАЙЛ" ИЗ ИНТЕРФЕЙСА (fxml)
        // ВЫЗЫВАЕТСЯ МЕТОД КОНТРОЛЛЕРА onOpenFileButtonClick()
        FileChooser fileChooser = new FileChooser();
        // УСТАНАВЛИВАЕМ ЗАГОЛОВОК ОКНА
        fileChooser.setTitle("ОТКРЫТЬ ФАЙЛ");
        // УСТАНАВЛИВАЕМ ФИЛЬТР КОТОРЫЙ ОТСЕКАЕТ ВСЕ ФОРМАТЫ КРОМЕ TXT
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files(*.txt)","*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        // В МОМЕНТ ВЫБОРА ФАЙЛА ПУТЬ К НЕМУ ЗАПИШЕТСЯ В ПЕРЕМЕННУЮ file
        file = fileChooser.showOpenDialog(new Stage());
        //Проверка на случай если пользователь проведёт какие-то манипуляции с файлом обходя фильтр
        if(file!=null){
            if (!file.getName().endsWith(".txt")){
                errorLabel3.setText("Неверный формат файла. Пожалуйста, выберите .txt файл.");
                file = null;
                bruteForceButton.setDisable(true);
            }
        }
        else{
            System.out.println(file);
            errorLabel3.setText("");
            checkIfReadyDecrypt();
        }
        okFileLabel3.setText("✔");
        if(file == null){
            okFileLabel3.setText("");
        }

        // ВЫВОДИМ ИМЯ ФАЙЛА В КОНСОЛЬ, ИЛИ ИСПОЛЬЗУЕМ ПО НАЗНАЧЕНИЮ
        System.out.println(file);
        checkIfReadyBruteForce();
    }
    @FXML
    protected void onDecryptButtonClick3 () {
        System.out.println("test button");
        handleBruteForceButton();
        // Блокировка кнопки и сброс состояния после дешифрования
        bruteForceButton.setDisable(true);
    }


    @FXML
    private void onBruteForceButtonClick() {
        errorLabel3.setText("");
        successLabelBrutForce.setText("");
        okFileLabel3.setText("");

            if (file.toPath() != null) {
                System.out.println("Файл: " + file.getAbsolutePath());
                errorLabel3.setText("");
                BruteForce.bruteForce(file.toPath());
                successLabelBrutForce.setText("Текст Расшифрован!");
            } else if(file.toPath() == null){
                errorLabel3.setText("Файл не выбран");
            }

    }
    @FXML
    private void handleBruteForceButton() {
        errorLabel2.setText("");
        successLabelDecrypt.setText("");
        okFileLabel2.setText("");
        try {
            key = Integer.parseInt(keyField2.getText()); // Присваиваем значение ключа
            if (file.toPath() != null) {
                System.out.println("Ключ: " + key + ", Файл: " + file.getAbsolutePath());
                errorLabel2.setText("");
                Sipher.encryptDecrypt(key, file.toPath(),false);
                successLabelDecrypt.setText("Текст Расшифрован!");
            } else if(file.toPath() == null){
                errorLabel2.setText("Файл не выбран");
            }
        } catch (NumberFormatException e) {
            successLabelDecrypt.setText("");
            errorLabel2.setText("Некорректный ключ");
        }
    }


    @FXML
    private void checkIfReadyBruteForce() {
        boolean isFileSelected = file != null; // Проверяем, выбран ли файл

        // Разблокируем кнопку, если оба условия выполнены
        bruteForceButton.setDisable(!(isFileSelected));
        if (isFileSelected) {
            successLabelBrutForce.setText(""); // Очищаем надпись о неправильном ключе
            errorLabel3.setText("");
        }
    }

}


