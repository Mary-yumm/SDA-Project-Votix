package votix.controllers.AdminControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import votix.models.Candidate;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;

public class candidateListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox candidateTable;

    private Stage primaryStage; // To hold the primary stage
    private AdminElectionManagementSystem ems; // Election Management System instance
    private PersistenceHandler ph; // Database connection handler

    // Method to inject the ElectionManagementSystem and populate candidates
    public void setElectionManagementSystem(AdminElectionManagementSystem system, Stage st) {
        this.ems = system;
        this.primaryStage = st;
        populateCandidates(); // Call to populate candidates after setting EMS
    }

    // Method to populate candidates in the UI
    private void populateCandidates() {
        if (ems != null) {
            System.out.println("EMS is not null, loading candidates...");
            List<Candidate> candidates = ems.getCands();

            if (candidates == null || candidates.isEmpty()) {
                System.out.println("No candidates found!");
            } else {
                System.out.println("Found " + candidates.size() + " candidates.");
                for (Candidate candidate : candidates) {
                    addCandidateRow(candidate);
                }
            }
        } else {
            System.out.println("EMS is null!");
        }
    }

    // Method to add a row to the candidate table for each candidate
    private void addCandidateRow(Candidate candidate) {
        HBox row = new HBox(30); // Create a row with spacing
        row.getStyleClass().add("table-row");

        // Candidate Name
        Label nameLabel = new Label(candidate.getName());
        nameLabel.getStyleClass().add("table-cell");
        row.getChildren().add(nameLabel);

        // Party Name
        Label partyLabel = new Label(candidate.getPartyName());
        partyLabel.getStyleClass().add("table-cell");
        row.getChildren().add(partyLabel);

        // Party Symbol
        ImageView partySymbolView = new ImageView(candidate.getPartySymbol());
        partySymbolView.setFitHeight(40);
        partySymbolView.setFitWidth(40);
        row.getChildren().add(partySymbolView);

        // Placeholder for vote count or other candidate-specific details
        Label voteCountLabel = new Label("0"); // Example placeholder, can be replaced with actual data
        voteCountLabel.getStyleClass().add("table-cell");
        row.getChildren().add(voteCountLabel);

        candidateTable.getChildren().add(row); // Add row to the table
    }


}



/*package votix.controllers.AdminControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import votix.models.Candidate;
import votix.services.AdminElectionManagementSystem;
import votix.services.ElectionManagementSystem;
import votix.services.PersistenceHandler;
import votix.services.PollingPCElectionManagementSystem;

public class candidateListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox candidateTable;

    private Stage primaryStage;  // To hold the primary stage
    private AdminElectionManagementSystem ems;
    private PersistenceHandler ph;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setConnection(PersistenceHandler ph){
        this.ph = ph;
    }
    // This method is called to inject the ElectionManagementSystem object
    public void setElectionManagementSystem(AdminElectionManagementSystem system) {
        this.ems = system;
        populateCandidates();
    }

    @FXML
    void initialize() {
        assert candidateTable != null : "fx:id=\"candidateTable\" was not injected: check your FXML file 'CandidateList.fxml'.";

        if (ems != null) {
            List<Candidate> candidates = ems.getCands(); // Fetch the list of candidates
            for (Candidate candidate : candidates) {
                addCandidateRow(candidate); // Add each candidate as a row
            }
        }
    }


    // New method to populate candidates after ems is set
    private void populateCandidates() {
        if (ems != null) {
            System.out.println("EMS is not null, loading candidates...");
            List<Candidate> candidates = ems.getCands();
            for (Candidate candidate : candidates) {
                addCandidateRow(candidate);
            }
        } else {
            System.out.println("EMS is null!");
        }
    }
    private void addCandidateRow(Candidate candidate) {
        // Create a new row for the candidate
        HBox row = new HBox(30); // Spacing of 30px between elements
        row.getStyleClass().add("table-row");

        // Candidate Name
        Label nameLabel = new Label(candidate.getName());
        nameLabel.getStyleClass().add("table-cell");
        row.getChildren().add(nameLabel);

        // Party Name
        Label partyLabel = new Label(candidate.getPartyName());
        partyLabel.getStyleClass().add("table-cell");
        row.getChildren().add(partyLabel);

        // Party Symbol
        ImageView partySymbolView = new ImageView(candidate.getPartySymbol());
        partySymbolView.setFitHeight(40);
        partySymbolView.setFitWidth(40);
        row.getChildren().add(partySymbolView);

        // Vote Count (Placeholder for now, you can replace this with actual data)
        Label voteCountLabel = new Label("0"); // Set the vote count here
        voteCountLabel.getStyleClass().add("table-cell");
        row.getChildren().add(voteCountLabel);

        // Add this row to the candidate table
        candidateTable.getChildren().add(row);
    }
}
*/