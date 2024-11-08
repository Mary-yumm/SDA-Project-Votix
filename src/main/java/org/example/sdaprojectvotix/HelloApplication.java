package org.example.sdaprojectvotix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Helloooooooooooooooooooo!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {

        PersistenceHandler handler = new mysql("jdbc:mysql://100.91.228.86/votix", "username", "password");

        //PersistenceHandler handler = new mysql("jdbc:mysql://localhost:3306/LMS", "root", "16033004");
        launch();


    }
}