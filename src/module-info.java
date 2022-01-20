module perpustakaan_01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens controller to javafx.fxml;
    exports controller;
    opens dao to javafx.fxml;
    exports dao;
    opens model to javafx.fxml;
    exports model;
    opens utility to javafx.fxml;
    exports utility;
}