module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdom;
    requires java.prefs;

    opens org.example to javafx.fxml;
    opens org.example.default_methods.installer to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    exports org.example;
}