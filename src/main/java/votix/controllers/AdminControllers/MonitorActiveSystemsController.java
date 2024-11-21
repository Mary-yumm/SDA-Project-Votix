package votix.controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import votix.models.PollingStationPC;
import votix.services.AdminElectionManagementSystem;

import java.io.IOException;
import java.util.List;

public class MonitorActiveSystemsController {

    public AnchorPane contentPane;
    private Stage stage;

    public Button backbtn;
    @FXML
    private VBox systemTable; // VBox to dynamically add system rows
    private AdminElectionManagementSystem ems;

    public void initialize() {
        // Initialization
    }

    public void setElectionManagementSystem(AdminElectionManagementSystem system, Stage st) {
        this.ems = system;
        this.stage = st;
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

    public void returnToMenu(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/AdminControlled/AdminMenu.fxml"));
            AnchorPane addCandidatePane = loader.load();
            AdminMenuController controller = loader.getController();

            // Check if controller is not null and set EMS and primaryStage
            if (controller != null) {
                System.out.println("setting admin");
                controller.setElectionManagementSystem(this.ems);  // Pass the ems instance
                controller.setPrimaryStage(this.stage);      // Pass the primaryStage instance
            }

            // Update contentPane
            contentPane.getChildren().setAll(addCandidatePane);
            contentPane.requestLayout();  // Request a layout refresh

            System.out.println(contentPane);
            System.out.println("contentPane visible: " + contentPane.isVisible());
            System.out.println("contentPane parent: " + contentPane.getParent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
