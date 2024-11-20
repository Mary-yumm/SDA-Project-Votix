package votix.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import votix.models.PollingStationPC;
import votix.services.AdminElectionManagementSystem;

import java.util.List;

public class MonitorActiveSystemsController {

    @FXML
    private VBox systemTable; // VBox to dynamically add system rows
    private AdminElectionManagementSystem ems;

    public void initialize() {
        // Initialization logic
    }

    public void setElectionManagementSystem(AdminElectionManagementSystem system) {
        this.ems = system;
        populateSystemTable();
    }

    private void populateSystemTable() {
        List<PollingStationPC> systems = ems.getPollingPCs();
        for (PollingStationPC system : systems) {
            addSystemRow(system);
        }
    }

    private void addSystemRow(PollingStationPC system) {
        // Create the main AnchorPane for the row
        AnchorPane row = new AnchorPane();
        row.getStyleClass().add("table-row");

        // System ID Label
        Label systemIdLabel = new Label(system.getSystemID());
        systemIdLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(systemIdLabel, 60.0);
        row.getChildren().add(systemIdLabel);

        // Station ID Label
        Label stationIdLabel = new Label(String.valueOf(system.getStationID()));
        stationIdLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(stationIdLabel, 320.0);
        row.getChildren().add(stationIdLabel);

        // Status Label
        Label statusLabel = new Label(system.getSystemStatus() ? "Active" : "Inactive");
        statusLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(statusLabel, 540.0);
        row.getChildren().add(statusLabel);

        // Config Label
        Label configLabel = new Label(system.getConfigurationSettings());
        configLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(configLabel, 720.0);
        row.getChildren().add(configLabel);

        // Area Name Label
        Label areaNameLabel = new Label(system.getAreaName());
        areaNameLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(areaNameLabel, 1000.0);
        row.getChildren().add(areaNameLabel);

        // Add the row to the system table
        systemTable.getChildren().add(row);
    }
}
