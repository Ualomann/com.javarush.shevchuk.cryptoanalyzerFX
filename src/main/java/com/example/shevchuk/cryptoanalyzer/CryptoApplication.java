package com.example.shevchuk.cryptoanalyzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CryptoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CryptoApplication.class.getResource("cryptoanalyzer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 665, 480);
        stage.setTitle("JRU Halloween Cryptoanalyzer!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}