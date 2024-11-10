package votix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Update this line to point to your InitiateSystem FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxmlFiles/InitiateSystem.fxml"));

        Scene scene = new Scene(fxmlLoader.load(),1920,1080);
        stage.setTitle("E-Voting System Initialization");

        // Set the stage to full screen
        //stage.setFullScreen(true);

        // Optional: remove title bar if you want an undecorated stage
        // stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // Your database connection logic (optional for the application startup)
        PersistenceHandler handler = new mysql("jdbc:mysql://100.91.228.86/votix", "username", "password");

        // PersistenceHandler handler = new mysql("jdbc:mysql://localhost:3306/LMS", "root", "16033004");

        // PersistenceHandler handler = new mysql("jdbc:sqlserver://localhost:1433;databaseName=LMS", "sa", "16033004");

        // Launch the JavaFX application
        launch();
    }
}
