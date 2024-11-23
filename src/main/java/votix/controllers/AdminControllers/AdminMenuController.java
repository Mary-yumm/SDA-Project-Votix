package votix.controllers.AdminControllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;

public class AdminMenuController {

    @FXML
    public Button viewLogs;
    @FXML
    public Button monitorSystems;
    @FXML
    private Button viewCandidate;
    @FXML
    private Button addCand;
    @FXML
    private Button viewForm;
    @FXML
    private Button viewreport;
    @FXML
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

    public void viewLogsBtn() {
        setActiveButton(viewLogs);
    }

    public void monitorSystemsBtn() {
        setActiveButton(monitorSystems);
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

    public void viewElectionResult() throws IOException {
        viewresultBtn();

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/ElectionResult.fxml"));
        VBox root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception if the FXML loading fails
            return;  // Exit if loading fails
        }

        ElectionResultController controller = loader.getController();

        // Ensure that the controller is correctly initialized
        if (controller != null) {
            System.out.println("ElectionResultController initialized successfully.");
            controller.set(this.ems, this.primaryStage);  // Pass the ems instance
        } else {
            System.out.println("Error: ElectionResultController is null!");
        }

        // Set the loaded VBox as the scene
        this.primaryStage.setScene(new Scene(root));  // Set the new scene
        this.primaryStage.show();  // Ensure that the primary stage is shown after the scene is set
    }


    @FXML
    public void update_DeactivaeStaff() {
        staffUpdationBtn();
    }

    @FXML
    public void addPollingStaff() {
        pollingStaffBtn();
    }

    @FXML
    public void viewElectionReport() {
        viewReportBtn();
    }

    @FXML
    public void monitorActiveSystems() {monitorSystemsBtn();}

    @FXML
    public void viewLogs() {viewLogsBtn();}

    @FXML
    public void viewElectionForm() {
        viewFormBtn();
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
        viewLogs.getStyleClass().remove("selected");
        monitorSystems.getStyleClass().remove("selected");

        // Add the 'selected' class to the clicked button
        activeButton.getStyleClass().add("selected");
    }


}
