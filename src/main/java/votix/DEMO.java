package votix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import votix.controllers.AdminControllers.AdminMenuController;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.mysql;

import java.io.IOException;

public class DEMO extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(DEMO.class.getResource("/fxmlFiles/AdminControlled/AdminMenu.fxml"));

        // Load the scene from the FXML file
        Scene scene = new Scene(fxmlLoader.load());

        // Get screen bounds (primary screen)
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();

        // Set the scene size to fit the screen (for example, 80% of the screen size)
        // stage.setWidth(screenWidth);   // Set 80% of screen width
        // stage.setHeight(screenHeight); // Set 80% of screen height

        // Set window title
        stage.setTitle("Voting System");

        // After loading the FXML, get the controller and set the ElectionManagementSystem
        AdminMenuController controller = fxmlLoader.getController();
        PersistenceHandler handler = new mysql("jdbc:mysql://100.91.228.86/votix", "username", "password");

        AdminElectionManagementSystem ad = new AdminElectionManagementSystem(handler);
        // Set the primary stage in the controller
        controller.setPrimaryStage(stage);
        controller.setConnection(handler);
        controller.setElectionManagementSystem(ad);

        // Set the scene to the stage
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // Launch the JavaFX application (already done in start)
        launch();
    }
}