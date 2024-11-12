
package votix.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import votix.*;
import votix.controllers.*;
import votix.controllers.PollingPC.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;

    private Stage primaryStage;
    //private PersistenceHandler handler;
    private ElectionManagementSystem EMS;

    
    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    

    public void setElectionManagementSystem(ElectionManagementSystem EMS) {
        this.EMS = EMS;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simulated login validation
        if ("admin".equals(username) && "password".equals(password)) {
            try {
                // Load PollingPc.fxml and switch to it on successful login
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/PollingPc.fxml"));
                Scene pollingScene = new Scene(fxmlLoader.load(), 1920, 1080);
                
                // Get the controller for PollingPC and set necessary dependencies
                PollingPcController pollingController = fxmlLoader.getController();
                pollingController.setElectionManagementSystem(EMS);
                
                // Switch the scene
                primaryStage.setScene(pollingScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials");
        }
    }
}
