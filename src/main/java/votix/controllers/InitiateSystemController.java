package votix.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class InitiateSystemController {

    public ImageView logoImageView;
    @FXML
    private Label timerLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private ProgressIndicator loadingIndicator;  // Add the ProgressIndicator reference

    private int hours = 0;
    private int minutes = 0;
    private int seconds = 5;

    public void initialize() {
        messageLabel.setText("System will be initialized in " + getFormattedTime());

        // Set bold font and size for both labels
        messageLabel.setFont(Font.font("System", FontWeight.SEMI_BOLD,40));
        timerLabel.setFont(Font.font("System", FontWeight.NORMAL, 40));
        loadingIndicator.setPrefHeight(150.0);
        loadingIndicator.setPrefWidth(150.0);

        // Show the loading icon


        // Create a countdown animation
        Timeline countdown = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (seconds > 0) {
                seconds--;
            } else {
                loadingIndicator.setStyle("-fx-accent: #FF5733;"); // Replace with your desired color
                loadingIndicator.setVisible(true);
                messageLabel.setText("Loading Polling Station's data");
                timerLabel.setText("");
                return;
            }
            timerLabel.setText(getFormattedTime());
        }));

        countdown.setCycleCount(seconds + 1); // Runs for countdown time + 1 to update to 0
        countdown.play();
    }

    private String getFormattedTime() {
        return String.format("%02d hours : %02d minutes : %02d seconds", hours, minutes, seconds);
    }
}
