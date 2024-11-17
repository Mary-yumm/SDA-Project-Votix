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
import javafx.stage.Screen;
import javafx.stage.Stage;
import votix.controllers.PollingPC.CaptureVoterInfoController;
import votix.models.Candidate;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.PollingPCElectionManagementSystem;

public class AdminMenuController {

    @FXML   //view candidate
    private Button viewCandidate;

    @FXML   //add cand
    private Button addCand;

    @FXML   //view election form
    private Button viewForm;

    @FXML   //view form
    private Button viewreport;

    @FXML   //view staff
    private Button viewStaff;

    @FXML
    private Button pollingStaff;

    @FXML
    private Button staffUpdation;

    @FXML
    private Button viewresult;

    @FXML
    private AnchorPane contentPane;
    private AdminElectionManagementSystem ems;
    private Stage primaryStage;
    private PersistenceHandler ph;


    public void setElectionManagementSystem(AdminElectionManagementSystem electionManagementSystem) {
        this.ems = electionManagementSystem;
        if (ems != null) {
            System.out.println("ems set in Admin menu: " + (ems != null));  // Debugging line
        } else {
            System.out.println("ems set in admin menu: null");
        }
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Method to set the database connection
    public void setConnection(PersistenceHandler ph) {
        this.ph = ph;
    }

    public void viewCandidateBtn() {
        setActiveButton(viewCandidate);
    }

    public void addCandBtn() { setActiveButton(addCand); }

    public void viewFormBtn() {
        setActiveButton(viewForm);
    }

    public void viewReportBtn() {
        setActiveButton(viewreport);
    }

    public void viewStaffBtn() { setActiveButton(viewStaff);}

    public void viewresultBtn() {
        setActiveButton(viewresult);
    }

    public void pollingStaffBtn() {
        setActiveButton(pollingStaff);
    }

    public void staffUpdationBtn() {
        setActiveButton(staffUpdation);
    }


    @FXML
    void addCandidate() {
        addCandBtn();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/addCandidate.fxml"));
            AnchorPane addCandidatePane = loader.load();
            addCandidateController controller = loader.getController();

            // Check if controller is not null and set EMS
            if (controller != null) {
                System.out.println("setting");
                //this.primaryStage.getScene();
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
    void viewCandidateList() {
        viewCandidateBtn();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/CandidateList.fxml"));
            AnchorPane addCandidatePane = loader.load();
            candidateListController controller = loader.getController();

            if (controller != null) {
                System.out.println("setting");
                this.primaryStage.getScene();
               controller.setElectionManagementSystem(this.ems, this.primaryStage);
            } else {
                System.out.println("addCandidateController is null!");
            }
            contentPane.getChildren().setAll(addCandidatePane);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewElectionForm() {
        viewFormBtn();
    }

    @FXML
    void viewStaffAssignments() {
        viewStaffBtn();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/staffAssignments.fxml"));
            AnchorPane addCandidatePane = loader.load();
            staffAssignmentsController controller = loader.getController();

            // Check if controller is not null and set EMS
            if (controller != null) {
                System.out.println("setting");
                this.primaryStage.getScene();
                controller.setElectionManagementSystem(this.ems, this.primaryStage);  // Pass the ems instance
            } else {
                System.out.println("addCandidateController is null!");  // Debugging line
            }
            contentPane.getChildren().setAll(addCandidatePane);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewElectionResult(ActionEvent actionEvent) {
        viewresultBtn();
    }

    public void update_DeactivaeStaff(ActionEvent actionEvent) {
        staffUpdationBtn();
    }

    public void addPollingStaff(ActionEvent actionEvent) {
        pollingStaffBtn();
    }

    public void viewElectionReport(ActionEvent actionEvent) {
        viewReportBtn();
    }

    // Method to set the active button
    private void setActiveButton(Button activeButton) {
        // Remove the 'selected' class from all buttons
        viewStaff.getStyleClass().remove("selected");
        viewForm.getStyleClass().remove("selected");
        addCand.getStyleClass().remove("selected");
        viewCandidate.getStyleClass().remove("selected");
        viewreport.getStyleClass().remove("selected");
        pollingStaff.getStyleClass().remove("selected");
        staffUpdation.getStyleClass().remove("selected");
        viewresult.getStyleClass().remove("selected");

        // Add the 'selected' class to the clicked button
        activeButton.getStyleClass().add("selected");
    }

}
