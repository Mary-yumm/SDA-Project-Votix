package votix.controllers.AdminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import votix.services.AdminElectionManagementSystem;

import java.io.IOException;
import java.util.ArrayList;

public class staffAssignmentsController {

    public AnchorPane contentPane;
    @FXML
    private Button searchbtn;
    @FXML
    private Button removeFilter;
    @FXML
    private VBox staffTable;
    @FXML
    private TextField name;
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
        loadStaffAssignments();
    }


    public void searchByStaff(ActionEvent actionEvent) {
        if (!staffTable.getChildren().isEmpty() && staffTable.getChildren().size() > 1) {
            staffTable.getChildren().remove(1, staffTable.getChildren().size());
        }
        ArrayList<Object> list = ems.getStaffAssignments();

        for (Object obj : list) {

            Object[] list1 = (Object[]) obj;
            if (((String) list1[1]).equals(name.getText())) {
                HBox row = new HBox(200);
                row.setPrefWidth(1300);
                row.setPrefHeight(44);

                row.getStyleClass().add("table-row");

                // staff id (index 0 should be Integer)
                if (list1[0] instanceof Integer) {
                    Label idlabel = new Label(Integer.toString((Integer) list1[0]));
                    idlabel.getStyleClass().add("table-cell");
                    row.getChildren().add(idlabel);
                    idlabel.setMinWidth(110);

                } else {
                    System.out.println("Invalid staff ID format: " + list1[0]);
                }

                // staff name (index 1 should be String)
                if (list1[1] instanceof String) {
                    Label namelabel = new Label((String) list1[1]);
                    namelabel.getStyleClass().add("table-cell");
                    row.getChildren().add(namelabel);
                    namelabel.setMinWidth(200);
                }

                // area id (index 3 should be Integer)
                if (list1[3] instanceof Integer) {
                    Label areaidlabel = new Label(Integer.toString((Integer) list1[3]));
                    areaidlabel.getStyleClass().add("table-cell");
                    row.getChildren().add(areaidlabel);
                    areaidlabel.setMinWidth(200);
                } else {
                    System.out.println("Invalid area ID format: " + list1[3]);
                }

                // area name (index 4 should be String)
                if (list1[4] instanceof String) {
                    Label areaname = new Label((String) list1[4]);
                    areaname.getStyleClass().add("table-cell");
                    row.getChildren().add(areaname);
                    areaname.setMinWidth(220);
                }
                // station id (index 2 should be Integer)
                if (list1[2] instanceof Integer) {
                    Label stationlabel = new Label(Integer.toString((Integer) list1[2]));
                    stationlabel.getStyleClass().add("table-cell");
                    row.getChildren().add(stationlabel);
                    stationlabel.setMinWidth(100);
                } else {
                    System.out.println("Invalid station ID format: " + list1[2]);
                }

                staffTable.getChildren().add(row); // Add row to the table
            }
        }        //ems.searchStaffByStaffName();
    }
    void loadStaffAssignments() {
        ArrayList<Object> list = ems.getStaffAssignments();

        for (Object obj : list) {
            Object[] list1 = (Object[]) obj;

            HBox row = new HBox(200);
            row.setPrefWidth(1300);
            row.setPrefHeight(44);

            row.getStyleClass().add("table-row");

            // staff id (index 0 should be Integer)
            if (list1[0] instanceof Integer) {
                Label idlabel = new Label(Integer.toString((Integer) list1[0]));
                idlabel.getStyleClass().add("table-cell");
                row.getChildren().add(idlabel);
                idlabel.setMinWidth(110);

            } else {
                System.out.println("Invalid staff ID format: " + list1[0]);
            }

            // staff name (index 1 should be String)
            if (list1[1] instanceof String) {
                Label namelabel = new Label((String) list1[1]);
                namelabel.getStyleClass().add("table-cell");
                row.getChildren().add(namelabel);
                namelabel.setMinWidth(200);
            }

            // area id (index 3 should be Integer)
            if (list1[3] instanceof Integer) {
                Label areaidlabel = new Label(Integer.toString((Integer) list1[3]));
                areaidlabel.getStyleClass().add("table-cell");
                row.getChildren().add(areaidlabel);
                areaidlabel.setMinWidth(200);
            } else {
                System.out.println("Invalid area ID format: " + list1[3]);
            }

            // area name (index 4 should be String)
            if (list1[4] instanceof String) {
                Label areaname = new Label((String) list1[4]);
                areaname.getStyleClass().add("table-cell");
                row.getChildren().add(areaname);
                areaname.setMinWidth(220);
            }
            // station id (index 2 should be Integer)
            if (list1[2] instanceof Integer) {
                Label stationlabel = new Label(Integer.toString((Integer) list1[2]));
                stationlabel.getStyleClass().add("table-cell");
                row.getChildren().add(stationlabel);
                stationlabel.setMinWidth(100);
            } else {
                System.out.println("Invalid station ID format: " + list1[2]);
            }

            staffTable.getChildren().add(row); // Add row to the table
        }
    }

    @FXML
    void searchByArea(ActionEvent actionEvent) {

        if (!staffTable.getChildren().isEmpty() && staffTable.getChildren().size() > 1) {
            staffTable.getChildren().remove(1, staffTable.getChildren().size());
        }
        ArrayList<Object> list = ems.getStaffAssignments();

        for (Object obj : list) {

            Object[] list1 = (Object[]) obj;
            if (Integer.toString((Integer) list1[3]).equals(area.getValue())) {
                HBox row = new HBox(200);
                row.setPrefWidth(1300);
                row.setPrefHeight(44);

                row.getStyleClass().add("table-row");

                // staff id (index 0 should be Integer)
                if (list1[0] instanceof Integer) {
                    Label idlabel = new Label(Integer.toString((Integer) list1[0]));
                    idlabel.getStyleClass().add("table-cell");
                    row.getChildren().add(idlabel);
                    idlabel.setMinWidth(110);

                } else {
                    System.out.println("Invalid staff ID format: " + list1[0]);
                }

                // staff name (index 1 should be String)
                if (list1[1] instanceof String) {
                    Label namelabel = new Label((String) list1[1]);
                    namelabel.getStyleClass().add("table-cell");
                    row.getChildren().add(namelabel);
                    namelabel.setMinWidth(200);
                }

                // area id (index 3 should be Integer)
                if (list1[3] instanceof Integer) {
                    Label areaidlabel = new Label(Integer.toString((Integer) list1[3]));
                    areaidlabel.getStyleClass().add("table-cell");
                    row.getChildren().add(areaidlabel);
                    areaidlabel.setMinWidth(200);
                } else {
                    System.out.println("Invalid area ID format: " + list1[3]);
                }

                // area name (index 4 should be String)
                if (list1[4] instanceof String) {
                    Label areaname = new Label((String) list1[4]);
                    areaname.getStyleClass().add("table-cell");
                    row.getChildren().add(areaname);
                    areaname.setMinWidth(220);
                }
                // station id (index 2 should be Integer)
                if (list1[2] instanceof Integer) {
                    Label stationlabel = new Label(Integer.toString((Integer) list1[2]));
                    stationlabel.getStyleClass().add("table-cell");
                    row.getChildren().add(stationlabel);
                    stationlabel.setMinWidth(100);
                } else {
                    System.out.println("Invalid station ID format: " + list1[2]);
                }

                staffTable.getChildren().add(row); // Add row to the table
            }
        }
        //ems.searchStaffByAreaID();
    }

    public void RemoveFilter(ActionEvent actionEvent) {
       if (!staffTable.getChildren().isEmpty() && staffTable.getChildren().size() > 1) {
           staffTable.getChildren().remove(1, staffTable.getChildren().size());
       }
        area.setValue(null);
       name.setText(null);
        // Add row to the table
        loadStaffAssignments();
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
                controller.setPrimaryStage(this.stage);      // Pass the primaryStage instance
            }

            // Update contentPane
            contentPane.getChildren().setAll(addCandidatePane);
            contentPane.requestLayout();  // Request a layout refresh

            // Optionally reset the scene if necessary
            Scene currentScene = this.stage.getScene();
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



