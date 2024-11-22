
package votix.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import votix.controllers.AdminControllers.AdminMenuController;
import votix.controllers.PollingPC.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.PollingPCElectionManagementSystem;

public class LoginController {

    //@FXML
    public Button LoginButton;

    //private Label roleLabel;

    private String role;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    private PersistenceHandler ph;

    private Stage primaryStage;  // To hold the primary stage

    public void setPEMS(PollingPCElectionManagementSystem PEMS) {
        this.PEMS = PEMS;
    }

    private PollingPCElectionManagementSystem PEMS;
    private AdminElectionManagementSystem AEMS;


    // Add this method to set the primary stage
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setConnection(PersistenceHandler ph){
        this.ph = ph;
    }


    public void setRole(String role) {this.role = role;}

    public String getRole() {return role;}

    @FXML
    private void handleLogin() {

        if (Objects.equals(getRole(), "staff")) {
            staffLogin();
        }
        else if (Objects.equals(getRole(), "admin")) {
            adminLogin();
        }
        else
            System.out.println("Error finding a login page");

    }


        private void staffLogin()
        {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String mac_address = getCurrentMacAddress();

            try {
                PEMS = new PollingPCElectionManagementSystem();
                PEMS.setPersistenceHandler(ph);
                // Use the dbHandler to verify staff credentials and MAC address
                if (PEMS.authorizePollingStaff(username, password,mac_address)) {
                    PEMS.initializeArrays();

                    // Load PollingPc.fxml and switch to it on successful login
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/PollingPc.fxml"));
                    Scene pollingScene = new Scene(fxmlLoader.load(), 1920, 1080);

                    // Get the controller for PollingPC and set necessary dependencies
                    PollingPcController pollingController = fxmlLoader.getController();
                    pollingController.setElectionManagementSystem(PEMS);

                    // Switch the scene
                    if (primaryStage != null) {
                        primaryStage.setScene(pollingScene);
                    } else {
                        System.out.println("Primary stage is null.");
                    }
                } else {
                    System.out.println("Invalid credentials or unauthorized device.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    private void adminLogin()
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            AEMS = new AdminElectionManagementSystem();
            AEMS.setPersistenceHandler(ph);
            // Use the dbHandler to verify staff credentials and MAC address
            if (AEMS.authorizeAdmin(username, password)) {

                // Load PollingPc.fxml and switch to it on successful login
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/AdminMenu.fxml"));
                Scene Scene = new Scene(fxmlLoader.load(), 1920, 1080);

                // Get the controller for PollingPC and set necessary dependencies
                AdminMenuController controller = fxmlLoader.getController();
                AdminElectionManagementSystem ad = new AdminElectionManagementSystem(ph);

                controller.setConnection(ph);
                controller.setElectionManagementSystem(ad);

                // Switch the scene
                if (primaryStage != null) {
                    primaryStage.setScene(Scene);
                } else {
                    System.out.println("Primary stage is null.");
                }
            } else {System.out.println("Invalid credentials");}

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

        private String getCurrentMacAddress() {
        try {
            // Get all network interfaces (returns Enumeration)
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // Skip non-physical interfaces (e.g., loop back, docker)
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                // Ensure networkInterface has a MAC address
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    StringBuilder macAddress = new StringBuilder();
                    for (byte macByte : mac) {
                        macAddress.append(String.format("%02X:", macByte));
                    }

                    // Remove trailing colon
                    if (!macAddress.isEmpty()) {
                        macAddress.setLength(macAddress.length() - 1);
                    }

                    System.out.println("Detected MAC Address: " + macAddress);
                    return macAddress.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Unable to retrieve MAC address.");
        return null;
    }

    public void setph(PersistenceHandler ph) {
        this.ph=ph;
    }

    public void handleBackButtonAction(javafx.scene.input.MouseEvent mouseEvent) {
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
}

