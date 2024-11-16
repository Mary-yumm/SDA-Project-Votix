package votix.controllers.AdminControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import votix.services.AdminElectionManagementSystem;
import votix.services.ElectionManagementSystem;
import votix.services.PersistenceHandler;

public class registerCandidateController {

    @FXML
    private TextField age;

    @FXML
    private Button checkeligibilitybutton;

    @FXML
    private TextField cnic;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private TextField name;

    private boolean eli;
    @FXML
    private ComboBox<String> nationality;

    @FXML
    private ComboBox<String> politicalparty;

    @FXML
    private Button registerbutton;

    @FXML
    private AnchorPane titlebar;

    private ElectionManagementSystem ems; // Your Election Management System instance
    private Stage primaryStage;
    private PersistenceHandler ph; // Database connection handler

    @FXML
    void initialize() {
 // Populate political parties ComboBox
        loaddata();
    }

    private void loaddata() {
        // Sample list of political parties, replace this with data from your system/database
        ObservableList<String> politicalParties = FXCollections.observableArrayList(
                "Party A", "Party B", "Party C", "Party D"
        );
        politicalparty.setItems(politicalParties);
        nationality.setItems(politicalParties);
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Method to set the database connection
    public void setConnection(PersistenceHandler ph) {
        this.ph = ph;
    }
    // Setter to receive ElectionManagementSystem from the MainApp
    public void setElectionManagementSystem(AdminElectionManagementSystem electionManagementSystem) {
        this.ems = electionManagementSystem;
    }

    //controller ftns

    public boolean checkForEligibility() {

        if (nationality.getValue().equals("Pakistani") && Integer.parseInt(age.getText()) >= 25) {
            this.eli = true;
        }
        else{ this.eli = false;}

        boolean status = this.ems.checkEligibility(Integer.parseInt(age.getText()),cnic.getText(), nationality.getValue());

        if(status == true){
            //cand is eligible
        }
        else {
            //not eligible
        }

        return this.eli;
    }
}
