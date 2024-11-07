module org.example.sdaprojectvotix {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires java.sql;

    opens org.example.sdaprojectvotix to javafx.fxml;
    exports org.example.sdaprojectvotix;
}