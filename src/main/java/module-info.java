module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdom;
    requires java.prefs;

    opens org.allvens to javafx.fxml;
    opens org.allvens.default_methods.installer to javafx.fxml;
    opens org.allvens.controllers to javafx.fxml;
    exports org.allvens;
}