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
import votix.ElectionManagementSystem;

public class registerCandidateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    @FXML
    private ComboBox<String> nationality;

    @FXML
    private ComboBox<String> politicalparty;

    @FXML
    private Button registerbutton;

    @FXML
    private AnchorPane titlebar;

    private ElectionManagementSystem ems; // Your Election Management System instance

    @FXML
    void initialize() {
        // Assert injections for other fields (keeping it as is)
        assert age != null : "fx:id=\"age\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert checkeligibilitybutton != null : "fx:id=\"checkeligibilitybutton\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert cnic != null : "fx:id=\"cnic\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert label1 != null : "fx:id=\"label1\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert label2 != null : "fx:id=\"label2\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert label3 != null : "fx:id=\"label3\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert label4 != null : "fx:id=\"label4\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert label5 != null : "fx:id=\"label5\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert nationality != null : "fx:id=\"nationality\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert politicalparty != null : "fx:id=\"politicalparty\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert registerbutton != null : "fx:id=\"registerbutton\" was not injected: check your FXML file 'registerCandidate.fxml'.";
        assert titlebar != null : "fx:id=\"titlebar\" was not injected: check your FXML file 'registerCandidate.fxml'.";

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

    // Setter to receive ElectionManagementSystem from the MainApp
    public void setElectionManagementSystem(ElectionManagementSystem electionManagementSystem) {
        this.ems = electionManagementSystem;
    }
}
