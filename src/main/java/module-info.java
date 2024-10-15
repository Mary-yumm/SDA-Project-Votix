module org.example.sdaprojectvotix {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sdaprojectvotix to javafx.fxml;
    exports org.example.sdaprojectvotix;
}