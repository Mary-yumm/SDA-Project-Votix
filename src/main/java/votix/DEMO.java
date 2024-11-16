package votix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import votix.controllers.AdminControllers.AdminMenuController;
import votix.controllers.AdminControllers.candidateListController;
import votix.controllers.AdminControllers.registerCandidateController;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.mysql;

import java.io.IOException;

public class DEMO extends Application {

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(DEMO.class.getResource("/fxmlFiles/AdminControlled/AdminMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 730);
        stage.setTitle("Voting System");


        // After loading the FXML, get the controller and set the ElectionManagementSystem
       AdminMenuController controller = fxmlLoader.getController();
        PersistenceHandler handler = new mysql("jdbc:mysql://100.91.228.86/votix", "username", "password");

        AdminElectionManagementSystem ad = new AdminElectionManagementSystem(handler);
        // Set the primary stage in the controller
        controller.setPrimaryStage(stage);
        controller.setConnection(handler);
        controller.setElectionManagementSystem(ad);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException {
        // Launch the JavaFX application (already done in start)
        launch();
    }
}
