package votix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votix.controllers.PollingPC.PollingPcController;

import java.io.IOException;

public class DEMO extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        // Initialize the ElectionManagementSystem in the start method (you already do this in main, but we'll use it here too)
        FXMLLoader fxmlLoader = new FXMLLoader(DEMO.class.getResource("/fxmlFiles/PollingPC/PollingPc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("E-Voting System");

        // After loading the FXML, get the controller and set the ElectionManagementSystem
        PollingPcController controller = fxmlLoader.getController();

        // Initialize the ElectionManagementSystem and PersistenceHandler
        String etype = "General";
        PersistenceHandler handler = new mysql("jdbc:mysql://100.91.228.86/votix", "username", "password");
        ElectionManagementSystem EMS = new ElectionManagementSystem(etype);
        EMS.setPersistenceHandler(handler);

        // Set the ElectionManagementSystem in the controller
        controller.setElectionManagementSystem(EMS);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException {
        // Launch the JavaFX application (already done in start)
        launch();
    }
}
