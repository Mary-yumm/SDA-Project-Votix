// Import necessary packages

package votix.controllers.PollingPC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import votix.Candidate;
import votix.ElectionManagementSystem;

import java.util.List;

public class CastVoteController {

    @FXML
    private VBox candidateTable;

    private ElectionManagementSystem ems; // This needs to be passed from wherever you call this controller

    private CheckBox lastSelectedCheckbox; // Track the last selected checkbox

    public void setElectionManagementSystem(ElectionManagementSystem system) {
        this.ems = system;
    }

    public void initialize() {
        if (ems != null) {
            List<Candidate> candidates = ems.getCands();
            for (Candidate candidate : candidates) {
                addCandidateRow(candidate);
            }
        }
    }

    private void addCandidateRow(Candidate candidate) {
        // Create the main AnchorPane for the row
        AnchorPane row = new AnchorPane();
        row.getStyleClass().add("table-row");

        // Candidate Name
        Label nameLabel = new Label(candidate.getName());
        nameLabel.getStyleClass().add("table-cell");
        AnchorPane.setTopAnchor(nameLabel, 10.0); // Adjust top to center vertically
        AnchorPane.setLeftAnchor(nameLabel, 10.0); // Set X position for the candidate name
        row.getChildren().add(nameLabel);

        // Party Name
        Label partyLabel = new Label(candidate.getPartyName());
        partyLabel.getStyleClass().add("table-cell");
        AnchorPane.setTopAnchor(partyLabel, 10.0); // Adjust top to center vertically
        AnchorPane.setLeftAnchor(partyLabel, 490.0); // Set X position for the party name
        row.getChildren().add(partyLabel);

        // Party Symbol
        ImageView partySymbolView = new ImageView(candidate.getPartySymbol());
        partySymbolView.setFitHeight(40);
        partySymbolView.setFitWidth(40);
        AnchorPane.setLeftAnchor(partySymbolView, 975.0); // Set X position for the party symbol
        row.getChildren().add(partySymbolView);

        // Checkbox for voting
        CheckBox selectCheckbox = new CheckBox();
        AnchorPane.setLeftAnchor(selectCheckbox, 1400.0); // Set X position for the checkbox
        AnchorPane.setTopAnchor(selectCheckbox, 10.0); // Adjust top to center vertically
        row.getChildren().add(selectCheckbox);

        // Add listener to each checkbox
        selectCheckbox.setOnAction(event -> handleCheckboxSelection(selectCheckbox));

        // Add the row to the candidate table
        candidateTable.getChildren().add(row);
    }

    // Handle checkbox selection and ensure only one checkbox can be selected at a time
    private void handleCheckboxSelection(CheckBox currentCheckbox) {
        // If there was a previously selected checkbox, uncheck it
        if (lastSelectedCheckbox != null && lastSelectedCheckbox != currentCheckbox) {
            lastSelectedCheckbox.setSelected(false);
        }

        // Update the last selected checkbox to the current one
        lastSelectedCheckbox = currentCheckbox;
    }

    // Method to cast vote
    private void castVote(Candidate selectedCandidate) {
        // Cast the vote and update results using the ElectionManagementSystem
        if (ems != null) {
            // Use electionManagementSystem methods here to handle vote
        }
    }

    public void handleSubmit(ActionEvent actionEvent) {
        // Assuming the selected candidate is the one with the checked checkbox
        if (lastSelectedCheckbox != null && lastSelectedCheckbox.isSelected()) {
            // Get the candidate associated with the selected checkbox
            int selectedIndex = candidateTable.getChildren().indexOf(lastSelectedCheckbox.getParent());
            Candidate selectedCandidate = ems.getCands().get(selectedIndex);
            castVote(selectedCandidate);
        }
    }
}
