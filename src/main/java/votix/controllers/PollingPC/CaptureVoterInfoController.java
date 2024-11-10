package votix.controllers.PollingPC;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CaptureVoterInfoController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField cnicTextField;

    @FXML
    private Label biometricInstructionLabel; // New label for biometric instructions

    @FXML
    private void initialize() {
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
        // Check if the entered information is valid (you can replace this with actual validation logic)
        boolean isValid = !nameTextField.getText().isEmpty() && !cnicTextField.getText().isEmpty();

        if (isValid) {
            // Display the biometric instruction label
            biometricInstructionLabel.setVisible(true);
        } else {
            // Optionally handle the case where input is invalid
            biometricInstructionLabel.setVisible(false);
            // Show a message or prompt the user to enter valid data
        }
    }
}
