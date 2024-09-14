module com.example.syrovatko.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.syrovatko.cryptoanalyzer to javafx.fxml;
    exports com.example.syrovatko.cryptoanalyzer;
}