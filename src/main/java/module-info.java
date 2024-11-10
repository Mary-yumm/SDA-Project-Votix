module org.example.sdaprojectvotix {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.sql;

    opens votix to javafx.fxml;
    exports votix;
    exports votix.controllers;
    opens votix.controllers to javafx.fxml;
}