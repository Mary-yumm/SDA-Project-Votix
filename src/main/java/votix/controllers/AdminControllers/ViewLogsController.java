package votix.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import votix.models.Log;
import votix.services.AdminElectionManagementSystem;

import java.util.List;
import java.util.stream.Collectors;

public class ViewLogsController {

    @FXML
    private VBox logTable; // Reference to the VBox in the FXML file
    @FXML
    private TextField filterTextField; // Filter by action
    @FXML
    private TextField areaFilterTextField; // Filter by area ID
    @FXML
    private TextField pollingStationFilterTextField; // Filter by polling station ID
    @FXML
    private Button filterButton; // Button to apply filters

    private AdminElectionManagementSystem ems; // Reference to the EMS object

    // Set the EMS and populate logs
    public void setEMS(AdminElectionManagementSystem ems) {
        this.ems = ems;
        if (ems != null) {
            System.out.println("EMS set in ViewLogsController");
            populateLogTable(); // Populate logs immediately after EMS is set
        }
    }

    // Populate the log table with logs from the EMS
    private void populateLogTable() {
        List<Log> logs = ems.viewLogs(); // Retrieve logs using the EMS method
        if (logs.isEmpty()) {
            System.out.println("No logs available to display.");
            return;
        }

        for (Log log : logs) {
            addLogRow(log); // Add each log as a row to the log table
        }
    }

    // Add a row to the log table for a specific log
    private void addLogRow(Log log) {
        // Create the main AnchorPane for the row
        AnchorPane row = new AnchorPane();
        row.getStyleClass().add("table-row");

        // Log ID Label
        Label logIdLabel = new Label(String.valueOf(log.getLogId()));
        logIdLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(logIdLabel, 10.0);
        row.getChildren().add(logIdLabel);

        // Action Label
        Label actionLabel = new Label(log.getAction());
        actionLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(actionLabel, 250.0);
        row.getChildren().add(actionLabel);

        // Timestamp Label
        Label timestampLabel = new Label(log.getTimeStamp());
        timestampLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(timestampLabel, 950.0);
        row.getChildren().add(timestampLabel);

        // Add the row to the log table
        logTable.getChildren().add(row);
    }

    // Apply filters for action, area ID, and polling station ID
    @FXML
    private void applyFilters() {
//        String filterText = filterTextField.getText().toLowerCase(); // Filter by action text
//        String areaFilterText = areaFilterTextField.getText(); // Area ID filter
//        String pollingStationFilterText = pollingStationFilterTextField.getText(); // Polling station filter
//
//        // Filter logs based on the given criteria
//        List<Log> filteredLogs = ems.viewLogs().stream()
//                .filter(log -> log.getAction().toLowerCase().contains(filterText) // Action filter
//                        && (areaFilterText.isEmpty() || log.getAreaId().equals(areaFilterText)) // Area filter
//                        && (pollingStationFilterText.isEmpty() || log.getPollingStationId().equals(pollingStationFilterText))) // Polling station filter
//                .collect(Collectors.toList());
//
//        logTable.getChildren().clear(); // Clear existing logs
//        for (Log log : filteredLogs) {
//            addLogRow(log); // Add filtered logs
//        }
    }

    @FXML
    private void filterLogs() {
//        String filterText = filterTextField.getText().toLowerCase(); // Filter by action text
//        String areaFilterText = areaFilterTextField.getText(); // Area ID filter
//        String pollingStationFilterText = pollingStationFilterTextField.getText(); // Polling station filter
//
//        // Filter logs based on the given criteria
//        List<Log> filteredLogs = ems.viewLogs().stream()
//                .filter(log -> log.getAction().toLowerCase().contains(filterText) // Action filter
//                        && (areaFilterText.isEmpty() || log.getAreaId().equals(areaFilterText)) // Area filter
//                        && (pollingStationFilterText.isEmpty() || log.getPollingStationId().equals(pollingStationFilterText))) // Polling station filter
//                .collect(Collectors.toList());
//
//        logTable.getChildren().clear(); // Clear existing logs
//        for (Log log : filteredLogs) {
//            addLogRow(log); // Add filtered logs
//        }
    }
}
