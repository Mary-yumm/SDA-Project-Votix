package votix.controllers.AdminControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import votix.Candidate;
import votix.ElectionManagementSystem;

public class candidateListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox candidateTable;

    private ElectionManagementSystem ems; // Assuming this is passed into the controller

    // This method is called to inject the ElectionManagementSystem object
    public void setElectionManagementSystem(ElectionManagementSystem system) {
        this.ems = system;
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

    private void addCandidateRow(Candidate candidate) {
        // Create a new row for the candidate
        HBox row = new HBox(10); // Spacing of 10px between elements
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
