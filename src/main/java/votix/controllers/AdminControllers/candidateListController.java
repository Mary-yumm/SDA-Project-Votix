package votix.controllers.AdminControllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import votix.models.Candidate;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;

public class candidateListController {

    public AnchorPane contentPane;
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
            List<Candidate> candidates = ems.getAllCand();

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
        HBox row = new HBox(200); // Create a row with spacing
        row.setPrefWidth(1300);
        row.setPrefHeight(44);
        row.getStyleClass().add("table-row");

        // Candidate Name
        Label nameLabel = new Label(candidate.getName());
        nameLabel.getStyleClass().add("table-cell");
        row.getChildren().add(nameLabel);
        nameLabel.setMinWidth(210);

        // Party Name
        Label partyLabel = new Label(candidate.getPartyName());
        partyLabel.getStyleClass().add("table-cell");
        row.getChildren().add(partyLabel);
        partyLabel.setMinWidth(350);

        // Party Symbol
        ImageView partySymbolView = new ImageView(candidate.getPartySymbol());
        partySymbolView.setFitHeight(40);
        partySymbolView.setFitWidth(40);
        row.getChildren().add(partySymbolView);

        // Placeholder for vote count or other candidate-specific details
        Label voteCountLabel = new Label("0"); // Example placeholder, can be replaced with actual data
        voteCountLabel.getStyleClass().add("table-cell");
        row.getChildren().add(voteCountLabel);
        voteCountLabel.setMinWidth(330);

        candidateTable.getChildren().add(row); // Add row to the table
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
                controller.setPrimaryStage(this.primaryStage);      // Pass the primaryStage instance
            }

            // Update contentPane
            contentPane.getChildren().setAll(addCandidatePane);
            contentPane.requestLayout();  // Request a layout refresh

            // Optionally reset the scene if necessary
            Scene currentScene = this.primaryStage.getScene();
            if (currentScene != null) {
                currentScene.setRoot(contentPane);  // Ensure contentPane is the root
            }

            System.out.println(contentPane);
            System.out.println("contentPane visible: " + contentPane.isVisible());
            System.out.println("contentPane parent: " + contentPane.getParent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

