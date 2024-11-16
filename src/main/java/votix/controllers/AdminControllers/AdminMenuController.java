package votix.controllers.AdminControllers;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import votix.controllers.PollingPC.CaptureVoterInfoController;
import votix.models.Candidate;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.PollingPCElectionManagementSystem;

public class AdminMenuController {

    @FXML   //view candidate
    private Button submitButton11;

    @FXML   //add cand
    private Button submitButton1;

    @FXML   //update cand
    private Button submitButton2;

    @FXML   //register cand
    private Button submitButton3;

    @FXML   //view form
    private Button submitButton4;

    @FXML   //view staff
    private Button submitButton41;

    @FXML
    private AnchorPane contentPane;
    private AdminElectionManagementSystem ems; // Your Election Management System instance
    private Stage primaryStage;
    private PersistenceHandler ph; // Database connection handler


    // Setter to receive ElectionManagementSystem from the MainApp
    public void setElectionManagementSystem(AdminElectionManagementSystem electionManagementSystem) {
        this.ems = electionManagementSystem;
        if (ems != null) {
            System.out.println("ems set in Admin menu: " + (ems != null));  // Debugging line
        } else {
            System.out.println("ems set in admin menu: null");
        }
    }

    public void selectbtn11() {
        setActiveButton(submitButton11);
    }

    public void selectbtn1() {
        setActiveButton(submitButton1);
    }

    public void selectbtn2() {
        setActiveButton(submitButton2);
    }

    public void selectbtn3() {
        setActiveButton(submitButton3);
    }

    public void selectbtn4() {
        setActiveButton(submitButton4);
    }

    public void selectbtn41() {
        setActiveButton(submitButton41);
    }

    @FXML
    void addCandidate() {
        selectbtn1();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/addCandidate.fxml"));
            AnchorPane addCandidatePane = loader.load();
            addCandidateController controller = loader.getController();

            // Check if controller is not null and set EMS
            if (controller != null) {
                System.out.println("setting");
                this.primaryStage.setMaxHeight(850);
                this.primaryStage.setMaxWidth(1000);
                this.primaryStage.getScene();
                controller.setElectionManagementSystem(this.ems, this.primaryStage, this.primaryStage.getScene());  // Pass the ems instance
            } else {
                System.out.println("addCandidateController is null!");  // Debugging line
            }
            contentPane.getChildren().setAll(addCandidatePane);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerCandidate() {

    }

    @FXML
    void updateCandidate() {

    }

    @FXML
    void viewCandidateList() {
    }

    @FXML
    void viewElectionForm() {

    }

    @FXML
    void viewStaffAssignments() {

    }

    // Method to set the active button
    private void setActiveButton(Button activeButton) {
        // Remove the 'selected' class from all buttons
        submitButton41.getStyleClass().remove("selected");
        submitButton4.getStyleClass().remove("selected");
        submitButton3.getStyleClass().remove("selected");
        submitButton2.getStyleClass().remove("selected");
        submitButton1.getStyleClass().remove("selected");
        submitButton11.getStyleClass().remove("selected");
        // Add the 'selected' class to the clicked button
        activeButton.getStyleClass().add("selected");
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Method to set the database connection
    public void setConnection(PersistenceHandler ph) {
        this.ph = ph;
    }
}
