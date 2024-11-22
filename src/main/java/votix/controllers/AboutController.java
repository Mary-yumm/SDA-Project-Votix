package votix.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class AboutController {

    private Stage primaryStage;

    @FXML
    public void handleBackButtonAction(ActionEvent actionEvent) {
        try {
            // Load the previous screen (MainPage.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/MainPage.fxml"));
            Scene scene = new Scene(loader.load(), 1920, 1080);

            // Get the controller of MainPage and set up necessary bindings again
            MainPageController mainPageController = loader.getController();
            mainPageController.setPrimaryStage(primaryStage);  // Ensure the primaryStage is passed back

            // Set the scene and show the primaryStage
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Set the primary stage so we can use it later for scene switching
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
