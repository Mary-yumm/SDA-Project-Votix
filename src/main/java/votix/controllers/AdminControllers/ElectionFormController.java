package votix.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import votix.models.Candidate;
import votix.services.ElectionManagementSystem;

import java.util.List;

public class ElectionFormController {

    @FXML
    private VBox candidateTable; // The VBox to hold the candidate rows

    private ElectionManagementSystem ems; // Assuming this is passed into the controller

    // This method is called to inject the ElectionManagementSystem object
    public void setElectionManagementSystem(ElectionManagementSystem system) {
        this.ems = system;
    }

    // This method will be called to initialize the table with data
    @FXML
    public void initialize() {
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
