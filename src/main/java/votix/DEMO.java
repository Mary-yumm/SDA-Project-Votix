package votix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votix.controllers.AdminControllers.ElectionFormController;
import votix.controllers.AdminControllers.registerCandidateController;
import votix.controllers.LoginController;
import votix.controllers.PollingPC.PollingPcController;

import java.io.IOException;

public class DEMO extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(DEMO.class.getResource("/fxmlFiles/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 573, 493);
        stage.setTitle("E-Voting System");

        // After loading the FXML, get the controller and set the ElectionManagementSystem
        LoginController controller = fxmlLoader.getController();

        // Set the primary stage in the controller
        controller.setPrimaryStage(stage);

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
