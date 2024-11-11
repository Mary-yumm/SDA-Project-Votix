package votix.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainPageController {

    @FXML
    private Button adminButton;

    @FXML
    private Button staffButton;

    private Stage primaryStage;

    // This method is used to set the primary stage (window)
    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    // This method handles the "ADMIN" button click
    @FXML
    private void handleAdminLogin(MouseEvent event) {
        // Logic for handling admin login
        System.out.println("Admin button clicked!");

        // For example, you could change the scene here (for admin login)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminPage.fxml"));
            Scene adminScene = new Scene(loader.load(), 600, 400);
            primaryStage.setScene(adminScene);  // Set the new scene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method handles the "STAFF" button click
    @FXML
    private void handleStaffLogin(MouseEvent event) {
        // Logic for handling staff login
        System.out.println("Staff button clicked!");

        // You can similarly load another scene for staff login
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/StaffPage.fxml"));
            Scene staffScene = new Scene(loader.load(), 600, 400);
            primaryStage.setScene(staffScene);  // Set the new scene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Any other initialization logic can go here (e.g., setting default values, etc.)
    public void initialize() {
        // Initialization code
    }
}
