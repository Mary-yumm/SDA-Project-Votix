package votix.controllers.AdminControllers;

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
import votix.services.ElectionManagementSystem;
import votix.services.PersistenceHandler;

import java.io.IOException;
import java.util.List;

public class ElectionFormController {

    public AnchorPane contentPane;
    private Stage primaryStage;
    @FXML
    private VBox candidateTable; // The VBox to hold the candidate rows

    private AdminElectionManagementSystem ems; // Assuming this is passed into the controller

    // This method is called to inject the ElectionManagementSystem object
    public void setElectionManagementSystem(AdminElectionManagementSystem system, Stage p) {
    this.primaryStage = p;
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
