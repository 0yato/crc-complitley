module com.example.crccct {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.crccct to javafx.fxml;
    exports com.example.crccct;
}