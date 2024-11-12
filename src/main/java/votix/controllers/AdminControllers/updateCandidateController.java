package votix.controllers.AdminControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class updateCandidateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField age;

    @FXML
    private TextField cnic;

    @FXML
    private ComboBox<?> nationality;

    @FXML
    private ComboBox<?> nationality1;

    @FXML
    private ComboBox<?> politicalparty;

    @FXML
    private ComboBox<?> politicalparty1;

    @FXML
    private Button registerButton;

    @FXML
    void initialize() {
        assert age != null : "fx:id=\"age\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";
        assert cnic != null : "fx:id=\"cnic\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";
        assert nationality != null : "fx:id=\"nationality\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";
        assert nationality1 != null : "fx:id=\"nationality1\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";
        assert politicalparty != null : "fx:id=\"politicalparty\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";
        assert politicalparty1 != null : "fx:id=\"politicalparty1\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'UpdateCandidate.fxml'.";

    }

}
