package votix.controllers.PopUps;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorMessageController {
    public Label messageLabel;
    private Stage stage;

    public void setMessageLabel(String msg){
        this.messageLabel.setText(msg);
    }
    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }
}
