package votix.controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import votix.models.Log;
import votix.services.AdminElectionManagementSystem;

import java.util.List;
import java.util.stream.Collectors;

public class ViewLogsController {

    public Button pollingStation;
    public Button Area;
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

    @FXML
    public void initialize() {
        setupTextFieldValidator(pollingStationFilterTextField);
        setupTextFieldValidator(areaFilterTextField);
        setupFocusListeners();
    }

    // Method to set up focus listeners for text fields
    private void setupFocusListeners() {
        filterTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // If filterTextField gains focus
                areaFilterTextField.clear();
                pollingStationFilterTextField.clear();
            }
        });

        areaFilterTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // If areaFilterTextField gains focus
                filterTextField.clear();
                pollingStationFilterTextField.clear();
            }
        });

        pollingStationFilterTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) { // If pollingStationFilterTextField gains focus
                filterTextField.clear();
                areaFilterTextField.clear();
            }
        });
    }

    // Method to apply number-only input restriction
    private void setupTextFieldValidator(TextField textField) {
        TextFormatter<Integer> numberFormatter = new TextFormatter<>(
                new IntegerStringConverter(),  // Use IntegerStringConverter to only allow numbers
                0,                             // Default value (optional)
                change -> {
                    // Allow the change only if the new text is a valid number
                    return change.getControlNewText().matches("\\d*") ? change : null;
                }
        );
        textField.setTextFormatter(numberFormatter);
    }


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
            addLogRow(log, null, null); // Add each log as a row to the log table without highlighting
        }
    }

    private void addLogRow(Log log, String highlightText, String filterType) {
        // Create the main AnchorPane for the row
        AnchorPane row = new AnchorPane();
        row.getStyleClass().add("table-row");

        // Log ID Label
        Label logIdLabel = new Label(String.valueOf(log.getLogId()));
        logIdLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(logIdLabel, 10.0);
        row.getChildren().add(logIdLabel);

        // Action Label with Highlight
        Label actionLabel = new Label();
        actionLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(actionLabel, 250.0);

        if (highlightText != null && !highlightText.isEmpty()) {
            switch (filterType.toLowerCase()) {
                case "action":
                    actionLabel.setGraphic(createHighlightedText(log.getAction(), highlightText));
                    break;
                case "pollingstation":
                    if (log.getAction().toLowerCase().contains("polling station " + highlightText)) {
                        actionLabel.setGraphic(createHighlightedText(log.getAction(), "Polling Station " + highlightText));
                    } else {
                        actionLabel.setText(log.getAction());
                    }
                    break;
                case "area":
                    if (log.getAction().toLowerCase().contains("area " + highlightText)) {
                        actionLabel.setGraphic(createHighlightedText(log.getAction(), "Area " + highlightText));
                    } else {
                        actionLabel.setText(log.getAction());
                    }
                    break;
                default:
                    actionLabel.setText(log.getAction());
            }
        } else {
            actionLabel.setText(log.getAction());
        }
        row.getChildren().add(actionLabel);

        // Timestamp Label
        Label timestampLabel = new Label(log.getTimeStamp());
        timestampLabel.getStyleClass().add("table-cell");
        AnchorPane.setLeftAnchor(timestampLabel, 950.0);
        row.getChildren().add(timestampLabel);

        // Add the row to the log table
        logTable.getChildren().add(row);
    }


    // Helper method to create a label with highlighted text
    private HBox createHighlightedText(String fullText, String highlight) {
        String lowerFullText = fullText.toLowerCase();
        String lowerHighlight = highlight.toLowerCase();

        int startIndex = lowerFullText.indexOf(lowerHighlight);
        if (startIndex == -1) return new HBox(new Label(fullText)); // No match, return plain label

        int endIndex = startIndex + highlight.length();

        String before = fullText.substring(0, startIndex);
        String highlighted = fullText.substring(startIndex, endIndex);
        String after = fullText.substring(endIndex);

        Label beforeLabel = new Label(before);
        Label highlightLabel = new Label(highlighted);
        Label afterLabel = new Label(after);

        String commonStyle = "-fx-font-size: 18px;"; // Ensure consistent font size
        beforeLabel.setStyle(commonStyle);
        highlightLabel.setStyle(commonStyle + " -fx-background-color: yellow; -fx-font-weight: bold;");
        afterLabel.setStyle(commonStyle);

        return new HBox(beforeLabel, highlightLabel, afterLabel);
    }




    public void applyFiltersPollingStation(ActionEvent actionEvent) {
        String pollingStationFilterText = pollingStationFilterTextField.getText().toLowerCase(); // Polling station filter text

        // Clear other text fields
        areaFilterTextField.clear();
        filterTextField.clear();

        // Filter logs based on polling station ID mentioned in the action column
        List<Log> filteredLogs = ems.viewLogs().stream()
                .filter(log -> pollingStationFilterText.isEmpty() || log.getAction().toLowerCase().contains("polling station " + pollingStationFilterText))
                .toList();

        // Keep headers and clear the log table content
        logTable.getChildren().retainAll(logTable.getChildren().get(0)); // Retain the header row only
        for (Log log : filteredLogs) {
            addLogRow(log, pollingStationFilterText, "pollingStation"); // Highlight Polling Station
        }
    }


    public void applyFiltersArea(ActionEvent actionEvent) {
        String areaFilterText = areaFilterTextField.getText().toLowerCase(); // Area ID filter text

        // Clear other text fields
        pollingStationFilterTextField.clear();
        filterTextField.clear();

        // Filter logs based on area ID mentioned in the action column
        List<Log> filteredLogs = ems.viewLogs().stream()
                .filter(log -> areaFilterText.isEmpty() || log.getAction().toLowerCase().contains("area " + areaFilterText))
                .toList();

        // Keep headers and clear the log table content
        logTable.getChildren().retainAll(logTable.getChildren().get(0)); // Retain the header row only
        for (Log log : filteredLogs) {
            addLogRow(log, areaFilterText, "area"); // Highlight Area
        }
    }


    @FXML
    private void filterLogs() {
        String filterText = filterTextField.getText().toLowerCase(); // Filter by action text

        // Clear other text fields
        pollingStationFilterTextField.clear();
        areaFilterTextField.clear();

        // Filter logs based on the action
        List<Log> filteredLogs = ems.viewLogs().stream()
                .filter(log -> filterText.isEmpty() || log.getAction().toLowerCase().contains(filterText))
                .toList();

        // Keep headers and clear the log table content
        logTable.getChildren().retainAll(logTable.getChildren().get(0)); // Retain the header row only
        for (Log log : filteredLogs) {
            addLogRow(log,filterText,"action");
        }
    }


}
