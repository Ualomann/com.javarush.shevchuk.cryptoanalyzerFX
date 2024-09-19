module com.example.syrovatko.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shevchuk.cryptoanalyzer to javafx.fxml;
    exports com.example.shevchuk.cryptoanalyzer;
}