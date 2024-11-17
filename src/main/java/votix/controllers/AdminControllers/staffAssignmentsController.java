package votix.controllers.AdminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import votix.services.AdminElectionManagementSystem;

import java.util.ArrayList;

public class staffAssignmentsController {

    public Button searchbtn;
    private AdminElectionManagementSystem ems;
    private Stage stage;
    @FXML
    ComboBox <String> area;
    public void setElectionManagementSystem(AdminElectionManagementSystem ems, Stage primaryStage) {
    this.ems = ems;
    this.stage = primaryStage;
        ArrayList<String> ar = ems.getAreaID();
        ObservableList<String> array = FXCollections.observableArrayList(ar);
        area.setItems(array);
    }


    public void searchByStaff(ActionEvent actionEvent) {
        //this should filer the information for a particular staff
    }
}



