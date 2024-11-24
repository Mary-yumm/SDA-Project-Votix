package votix.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import votix.services.PersistenceHandler;

import java.io.IOException;

public class AboutController {

    public Button backButton;
    public AnchorPane contentPane;
    private Stage primaryStage;
    private PersistenceHandler ph;

    private PersistenceHandler ph;


    @FXML
    public void handleBackButtonAction(MouseEvent actionEvent) {
//        try {
//            // Load the previous screen (MainPage.fxml)
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/MainPage.fxml"));
//            AnchorPane menu = loader.load();
//
//            // Get the controller of MainPage and set up necessary bindings again
//            MainPageController mainPageController = loader.getController();
//            mainPageController.setph(ph);
//            mainPageController.setPrimaryStage(primaryStage);  // Ensure the primaryStage is passed back
//
//            contentPane.getChildren().setAll(menu);
//            contentPane.requestLayout();  // Request a layout refresh
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void setPh(PersistenceHandler ph){
        this.ph=ph;
    }


    // Set the primary stage so we can use it later for scene switching
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        System.out.println("stage");
    }
    public PersistenceHandler getPh() {
        return ph;
    }

    public void setPh(PersistenceHandler ph) {
        this.ph = ph;
    }

}
