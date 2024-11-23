package votix.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import votix.services.PersistenceHandler;

import java.io.IOException;

public class MainPageController {

    public Button about;
    @FXML
    private Button admin;

    @FXML
    private Button staff;

    private Stage primaryStage;
    private PersistenceHandler ph;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    // This method handles the "ADMIN" button click
    @FXML
    private void handleAdminLogin(MouseEvent event) {
        System.out.println("Admin button clicked!");
        loadLoginScene("admin");
        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/login.fxml"));
            Scene adminScene = new Scene(loader.load(), 600, 400);
            primaryStage.setScene(adminScene);  // Set the new scene
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    // This method handles the "STAFF" button click
    @FXML
    private void handleStaffLogin(MouseEvent event) {
        System.out.println("Staff button clicked!");
        loadLoginScene("staff");
       /* try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/login.fxml"));
            Scene staffScene = new Scene(loader.load(), 600, 400);
            primaryStage.setScene(staffScene);  // Set the new scene
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    private void handleAbout(MouseEvent event)  {

        try{
            System.out.println("hereeee1");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/about.fxml"));
        Scene Scene = new Scene(loader.load(), 1920, 1080);
        System.out.println("hereeee2");
        AboutController Controller = loader.getController();
        Controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(Scene); // Set the new scene
            System.out.println("hereeee3");
        primaryStage.show();
            System.out.println("hereeee");

        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load about.fxml. Check the file path.");
        }
    }
    private void loadLoginScene(String role) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/login.fxml"));
            Scene loginScene = new Scene(loader.load(), 600, 400);

            // Get the LoginController and pass the role
            LoginController loginController = loader.getController();
            loginController.setRole(role);
            loginController.setph(ph);

            loginController.setPrimaryStage(primaryStage);
            primaryStage.setScene(loginScene); // Set the new scene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setph(PersistenceHandler p) {
        this.ph = p;
    }
}
