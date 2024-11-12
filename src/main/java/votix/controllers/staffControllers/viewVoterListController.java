package votix.controllers.staffControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class viewVoterListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox candidateTable;

    @FXML
    void initialize() {
        assert candidateTable != null : "fx:id=\"candidateTable\" was not injected: check your FXML file 'viewVoterList.fxml'.";

    }

}
