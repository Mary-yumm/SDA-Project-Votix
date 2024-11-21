
package votix.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import votix.controllers.PollingPC.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;

import votix.services.PersistenceHandler;
import votix.services.PollingPCElectionManagementSystem;

public class LoginController {


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage primaryStage;  // To hold the primary stage

    private PollingPCElectionManagementSystem EMS;

    private PersistenceHandler ph;

    // Add this method to set the primary stage
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setConnection(PersistenceHandler ph){
        this.ph = ph;
    }

    public void setElectionManagementSystem(PollingPCElectionManagementSystem EMS) {
        this.EMS = EMS;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String mac_address = getCurrentMacAddress();

        try {
            EMS = new PollingPCElectionManagementSystem();
            EMS.setPersistenceHandler(ph);
            // Use the dbHandler to verify staff credentials and MAC address
            if (EMS.authorizePollingStaff(username, password,mac_address)) {
                EMS.initializeArrays();

                // Load PollingPc.fxml and switch to it on successful login
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/PollingPc.fxml"));
                Scene pollingScene = new Scene(fxmlLoader.load(), 1920, 1080);

                // Get the controller for PollingPC and set necessary dependencies
                PollingPcController pollingController = fxmlLoader.getController();
                pollingController.setElectionManagementSystem(EMS);

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

    private String getCurrentMacAddress() {
        try {
            // Get all network interfaces (returns Enumeration)
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // Skip non-physical interfaces (e.g., loopback, docker)
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
                    if (macAddress.length() > 0) {
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

}

