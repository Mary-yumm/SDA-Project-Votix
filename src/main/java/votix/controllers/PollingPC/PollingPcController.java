package votix.controllers.PollingPC;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import votix.ElectionManagementSystem;

import java.io.IOException;

public class PollingPcController {
    @FXML
    private AnchorPane contentPane; // The pane where content will be dynamically loaded

    @FXML
    private Button tab1Button; // Capture Voter Info Button
    @FXML
    private Button tab2Button; // Cast Vote Button
    @FXML
    private Button tab3Button; // Polling Station Info Button

    private ElectionManagementSystem ems; // Your Election Management System instance

    // Setter to receive ElectionManagementSystem from the MainApp
    public void setElectionManagementSystem(ElectionManagementSystem electionManagementSystem) {
        this.ems = electionManagementSystem;
    }
    @FXML
    private void initialize() {
        loadCaptureVoterInfo();
        setActiveButton(tab1Button); // Set the initial active tab

    }

    // Change content based on button clicked
    public void selectTab1() {
        loadCaptureVoterInfo();
        setActiveButton(tab1Button); // Set the active tab to Tab 1

    }

    public void selectTab2() {
        loadCastVote();
        setActiveButton(tab2Button); // Set the active tab to Tab 2

    }

    public void selectTab3() {
        loadPollingStationInfo();
        setActiveButton(tab3Button); // Set the active tab to Tab 3

    }

    private void loadCaptureVoterInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/CaptureVoterInfo.fxml"));
            AnchorPane pollingStationInfo = loader.load();
            contentPane.getChildren().setAll(pollingStationInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCastVote() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/CastVote.fxml"));

            // Load the FXML and get the controller instance
            AnchorPane castVotePane = loader.load();
            CastVoteController controller = loader.getController();

            // Set the ElectionManagementSystem instance in the controller
            if (controller != null) {
                controller.setElectionManagementSystem(this.ems);
                controller.initialize();
            }

            // Replace the content with the loaded pane
            contentPane.getChildren().setAll(castVotePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadPollingStationInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/PollingStationInfo.fxml"));
            AnchorPane pollingStationInfo = loader.load();
            contentPane.getChildren().setAll(pollingStationInfo); // Replace current content with Polling Station Info
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to set the active button
    private void setActiveButton(Button activeButton) {
        // Remove the 'selected' class from all buttons
        tab1Button.getStyleClass().remove("selected");
        tab2Button.getStyleClass().remove("selected");
        tab3Button.getStyleClass().remove("selected");

        // Add the 'selected' class to the clicked button
        activeButton.getStyleClass().add("selected");
    }
}
