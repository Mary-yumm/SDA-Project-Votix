package votix.controllers.PollingPC;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import votix.services.PollingPCElectionManagementSystem;

import java.io.IOException;

public class PollingPcController {
    @FXML
    private AnchorPane contentPane; // The pane where content will be dynamically loaded

    @FXML
    private Button tab1Button; // Capture Voter Info Button
//    @FXML
//    private Button tab2Button; // Cast Vote Button
    @FXML
    private Button tab3Button; // Polling Station Info Button

    private PollingPCElectionManagementSystem ems; // Your Election Management System instance

    // Setter to receive ElectionManagementSystem from the MainApp
    public void setElectionManagementSystem(PollingPCElectionManagementSystem electionManagementSystem) {
        this.ems = electionManagementSystem;
        if(ems!=null) {
            System.out.println("ems set in PollingPcController: " + (ems != null));  // Debugging line
            selectTab1();
        }
        else{
            System.out.println("ems set in PollingPcController: null");
        }

    }
//    @FXML
//    private void initialize() {
//       // loadCaptureVoterInfo();
//        setActiveButton(tab1Button); // Set the initial active tab
//        loadCaptureVoterInfo();
//
//    }

    // Change content based on button clicked
    public void selectTab1() {
        loadCaptureVoterInfo();
        setActiveButton(tab1Button); // Set the active tab to Tab 1

    }

//    public void selectTab2() {
//        loadCastVote();
//        setActiveButton(tab2Button); // Set the active tab to Tab 2
//
//    }

    public void selectTab3() {
        loadPollingStationInfo();
        setActiveButton(tab3Button); // Set the active tab to Tab 3

    }

    private void loadCaptureVoterInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/CaptureVoterInfo.fxml"));
            AnchorPane captureVoterInfoPane = loader.load();
            CaptureVoterInfoController controller = loader.getController();

            // Check if controller is not null and set EMS
            if (controller != null) {
                System.out.println("setting");
                controller.setElectionManagementSystem(this.ems,ems.getStationId());  // Pass the ems instance
                controller.initialize();
            } else {
                System.out.println("CaptureVoterInfoController is null!");  // Debugging line
            }

            contentPane.getChildren().setAll(captureVoterInfoPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPollingStationInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/PollingStationInfo.fxml"));
            AnchorPane pollingStationInfo = loader.load();
            PollingStationInfoController controller = loader.getController();
            if (controller != null) {
                System.out.println("setting");
                controller.setPollingStation(ems.getStation(ems.getStationId()));
            }
            else {
                System.out.println("PollingStationInfoController is null!");  // Debugging line
            }
            contentPane.getChildren().setAll(pollingStationInfo); // Replace current content with Polling Station Info
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to set the active button
    private void setActiveButton(Button activeButton) {
        // Remove the 'selected' class from all buttons
        tab1Button.getStyleClass().remove("selected");
        //tab2Button.getStyleClass().remove("selected");
        tab3Button.getStyleClass().remove("selected");

        // Add the 'selected' class to the clicked button
        activeButton.getStyleClass().add("selected");
    }
}
