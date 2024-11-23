package votix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import votix.controllers.AdminControllers.AdminMenuController;
import votix.controllers.MainPageController;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.mysql;

import java.io.IOException;

public class DEMO extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        PersistenceHandler handler = new mysql("jdbc:mysql://100.91.228.86/votix", "username", "password");

        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(DEMO.class.getResource("/fxmlFiles/mainpage.fxml"));

        // Load the scene from the FXML file
        Scene scene = new Scene(fxmlLoader.load());

        // Set window title
        stage.setTitle("Voting System");

        // Get screen bounds (primary screen)
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();

        // Set the scene size to fit the screen (for example, 80% of the screen size)
         stage.setWidth(screenWidth);   // Set 80% of screen width
         stage.setHeight(screenHeight); // Set 80% of screen height

        // Main Page Controller
        MainPageController controller = fxmlLoader.getController();

        // Set the primary stage in the controller
        controller.setPrimaryStage(stage);

        // Set the db
        controller.setph(handler);

        // Set the scene to the stage
        stage.setScene(scene);
        stage.setTitle("EMS");

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // Launch the JavaFX application (already done in start)
        launch();
    }
}
