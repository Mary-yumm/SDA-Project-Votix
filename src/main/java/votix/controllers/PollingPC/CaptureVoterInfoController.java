package votix.controllers.PollingPC;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import votix.ElectionManagementSystem;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CaptureVoterInfoController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField cnicTextField;

    @FXML
    private Label biometricInstructionLabel; // New label for biometric instructions

    private ElectionManagementSystem ems; // Your Election Management System instance
    public void setElectionManagementSystem(ElectionManagementSystem electionManagementSystem) {

        this.ems = electionManagementSystem;
        if(ems!=null){
            System.out.println("Election Management System is already set");
        }
        else{
            System.out.println("Election Management System is null in capture voter info set");
        }
    }

    @FXML
    public void initialize() {
        restrictNameField();
        restrictCnicField();
    }

    private void restrictNameField() {
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                nameTextField.setText(oldValue);
            }
        });
    }

    private void restrictCnicField() {
        cnicTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cnicTextField.setText(oldValue);
            }
        });
    }

    @FXML
    private void handleSubmit() {
        boolean isValid = !nameTextField.getText().isEmpty() && !cnicTextField.getText().isEmpty();

        if (isValid) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/CastVote.fxml"));
                AnchorPane castVotePane = loader.load();
                CastVoteController controller = loader.getController();

                if (controller != null) {
                    //check if ems is null
                    if (ems == null){
                        System.out.println("EMS is null in capture voter info !");
                    }
                    else{
                        System.out.println("EMS is not null in capture voter info !");
                    }
                    controller.setElectionManagementSystem(ems); // Only set the EMS, populateCandidates will be called
                }

                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("Cast Vote");
                Scene castVoteScene = new Scene(castVotePane);
                secondaryStage.setScene(castVoteScene);

                Screen secondScreen = Screen.getScreens().size() > 1 ? Screen.getScreens().get(1) : Screen.getPrimary();
                Rectangle2D bounds = secondScreen.getVisualBounds();
                secondaryStage.setX(bounds.getMinX());
                secondaryStage.setY(bounds.getMinY());
                secondaryStage.setWidth(bounds.getWidth() - 20);
                secondaryStage.setHeight(bounds.getHeight() - 20);

                secondaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            biometricInstructionLabel.setVisible(false);
        }
    }

//    private void simulateFingerprintCapture() {
//        // Simulate fingerprint data capture
//        String fingerprintData = "sample_fingerprint_data"; // Replace with actual capture
//
//        try {
//            // Hash the fingerprint data
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(fingerprintData.getBytes());
//            String hashString = Base64.getEncoder().encodeToString(hash);
//
//            System.out.println("Fingerprint Hash: " + hashString); // Print the hash
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
}
