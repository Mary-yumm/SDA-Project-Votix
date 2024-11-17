package votix.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ElectionReportController {

    @FXML
    private ChoiceBox<String> electionTypeChoiceBox;

    @FXML
    private Label electionTypeLabel;

    @FXML
    private ImageView companyLogo;

    @FXML
    private Text electionResultText;

    @FXML
    public void initialize() {
        // Load an image into the ImageView (assuming image is in resources folder)
        //Image logoImage = new Image(getClass().getResource("/images/VotixCompanyLogo2.png").toExternalForm());
        //companyLogo.setImage(logoImage);

        // Set up options for election types in ChoiceBox
        electionTypeChoiceBox.getItems().addAll("National Assembly", "Provisional Assembly", "Local");

        // Set a default value for the ChoiceBox if desired
        electionTypeChoiceBox.setValue("Select Election Type");

        // Add a listener to handle selection changes in the ChoiceBox
        electionTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onElectionTypeSelected(newValue);
        });
    }

    private void onElectionTypeSelected(String electionType) {
        // Display the selected election type in the label or perform other actions as needed
        if (electionType != null) {
            electionTypeLabel.setText("Selected Election: " + electionType);
        }
    }
}
