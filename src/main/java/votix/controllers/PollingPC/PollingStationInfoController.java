package votix.controllers.PollingPC;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import votix.models.Voter;
import votix.models.PollingStation;

import java.util.List;

public class PollingStationInfoController {

    @FXML
    private VBox voterTable;

    private PollingStation pollingStation; // Reference to the PollingStation object

    public void setPollingStation(PollingStation station) {
        this.pollingStation = station;
        if(pollingStation != null) {
            System.out.println("polling station in polling station info" + pollingStation.getStationID());
        }
        populateVoterTable();
    }

    private void populateVoterTable() {
        List<Voter> voters = pollingStation.getVoters();
        for (Voter voter : voters) {
            addVoterRow(voter);
        }
    }

    private void addVoterRow(Voter voter) {
        // Create the main AnchorPane for the row
        AnchorPane row = new AnchorPane();
        row.getStyleClass().add("table-row");

        // CNIC Label
        Label cnicLabel = new Label(voter.getId());
        cnicLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(cnicLabel, 10.0);
        row.getChildren().add(cnicLabel);

        // Status Label
        Label statusLabel = new Label(voter.getStatus() ? "Voted" : "Not Voted");
        statusLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(statusLabel, 350.0);
        row.getChildren().add(statusLabel);

        // Add the row to the voter table
        voterTable.getChildren().add(row);
    }
}
