package votix.controllers.PollingPC;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class InitiateSystemController {

    public ImageView logoImageView;
    public AnchorPane pane;

    @FXML
    private Label timerLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private ProgressIndicator loadingIndicator; // Add the ProgressIndicator reference

    private int hours = 0;
    private int minutes = 0;
    private int seconds = 5;

    private Stage primaryStage; // Reference to the primary stage

    private Runnable onCountdownComplete;

    private Timeline countdown; // Declare countdown as a field

    public void setOnCountdownComplete(Runnable onCountdownComplete) {
        this.onCountdownComplete = onCountdownComplete;
    }

    public void initialize() {
        // Create the countdown Timeline
        countdown = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (seconds > 0) {
                seconds--;
            } else {
                // Stop the countdown
                countdown.stop();

                // Perform actions when the countdown reaches zero
                loadingIndicator.setStyle("-fx-accent: #FF5733;");
                loadingIndicator.setVisible(true);
                messageLabel.setText("Loading Polling Station's data...");
                timerLabel.setText("");

                // Transition to Polling PC
                if (onCountdownComplete != null) {
                    onCountdownComplete.run();
                } else {
                    transitionToPollingPC(); // Fallback
                }
            }
            timerLabel.setText(getFormattedTime());
        }));

        countdown.setCycleCount(seconds + 1);
        countdown.play();
    }


    private String getFormattedTime() {
        return String.format("%02d hours : %02d minutes : %02d seconds", hours, minutes, seconds);
    }

    private void transitionToPollingPC() {
        try {
            // Load the PollingPC.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/PollingPC/PollingPC.fxml"));
          //  Scene pollingPCScene = new Scene(loader.load());
            AnchorPane pollingpane=loader.load();

            PollingPcController pollingPcController = loader.getController();

            System.out.println("here");
            if (pollingPcController != null) {
                System.out.println("Transitioning to Polling PC...");
                // Optional: Pass the primaryStage to the PollingPcController
                pollingPcController.setPrimaryStage(this.primaryStage);
            }

            // Replace the current scene on the same stage
            //primaryStage.setScene(pollingPCScene);
            pane.getChildren().setAll(pollingpane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }
}

