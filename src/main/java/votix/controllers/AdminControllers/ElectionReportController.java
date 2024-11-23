package votix.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import votix.models.ElectionResult;
import votix.services.AdminElectionManagementSystem;
import votix.services.PersistenceHandler;

import java.util.ArrayList;

public class ElectionReportController {

    @FXML
    private TextField areaField;

    @FXML
    private Label areaNameDisplay;

    @FXML
    private Label electionDate;

    @FXML
    private ChoiceBox<?> electionType;

    @FXML
    private Label femaleVotes;

    @FXML
    private TextField loserName;

    @FXML
    private TextField loserParty;

    @FXML
    private TextField loserVotes;

    @FXML
    private Label maleVotes;

    @FXML
    private Button searchButton;

    @FXML
    private Label totalVotes;

    @FXML
    private Label voterPercentage;

    @FXML
    private Label votesCasted;

    @FXML
    private TextField winnerName;

    @FXML
    private TextField winnerParty;

    @FXML
    private TextField winnerVotes;

    private PersistenceHandler ph;
    private Stage PrimaryStage;
    private AdminElectionManagementSystem ems;



    @FXML
    void handleSearch(MouseEvent event) {
        String searchArea = areaField.getText(); // Using AreaName as area search field
        if (!searchArea.isEmpty()) {
            ArrayList<ElectionResult> winner = ems.WinnerByArea(searchArea);
            System.out.println("Search by area successful");
            loadData(winner);

            int totalvotes = ems.fetchTotalVotesByArea(searchArea);
            loadSummary(totalvotes,searchArea);

        } else {
            // If search field is empty, show all results
            System.out.println("no area");
            clearData();
        }
    }



    public void initialize() {

    }
    private void loadData(ArrayList<ElectionResult> results) {
        if (results != null && results.size() >= 2) {
            // Get the winner and loser data (assuming the first two results are winner and loser)
            ElectionResult winnerResult = results.get(0);
            ElectionResult loserResult = results.get(1);

            // Set the winner's information
            winnerName.setText(winnerResult.getCandidateName());
            winnerParty.setText(winnerResult.getPartyName());
            winnerVotes.setText(String.valueOf(winnerResult.getVoteCount()));

            // Set the loser's information
            loserName.setText(loserResult.getCandidateName());
            loserParty.setText(loserResult.getPartyName());
            loserVotes.setText(String.valueOf(loserResult.getVoteCount()));


        } else {
            // Handle the case where there aren't enough results (e.g., no data for winner and loser)
            clearData();
        }
    }
    private void loadSummary(int totalCastedVotes, String area) {
        // Update total votes and voter turnout
        int TotalRegVoters = (int) (totalCastedVotes * 1.5);
        this.totalVotes.setText(String.valueOf(TotalRegVoters)); // Display total votes directly

        this.votesCasted.setText(String.valueOf(totalCastedVotes));

        // Calculate and display voter turnout percentage
        int voterPercentage = (totalCastedVotes * 100) / TotalRegVoters; // Avoid floating-point arithmetic
        this.voterPercentage.setText(voterPercentage + "%");

        // Display the area name
        areaNameDisplay.setText(area);

        // Calculate and display male and female vote counts
        int maleVotesCount = (int) (totalCastedVotes * 0.6); // Assume 60% male votes
        int femaleVotesCount = totalCastedVotes - maleVotesCount; // Remaining are female votes
        this.maleVotes.setText(String.valueOf(maleVotesCount));
        this.femaleVotes.setText(String.valueOf(femaleVotesCount));
    }

    private void clearData() {
        // Clear all the fields if no results are found or if there's an error
        winnerName.clear();
        winnerParty.clear();
        winnerVotes.clear();

        loserName.clear();
        loserParty.clear();
        loserVotes.clear();

        areaNameDisplay.setText("");
        electionDate.setText("");
        maleVotes.setText("");
        femaleVotes.setText("");
        totalVotes.setText("");
        voterPercentage.setText("");
        votesCasted.setText("");
    }


    private void loadData() {
    }

    public PersistenceHandler getPh() {
        return ph;
    }

    public void setPh(PersistenceHandler ph) {
        this.ph = ph;
    }

    public Stage getPrimaryStage() {
        return PrimaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        PrimaryStage = primaryStage;
    }

    public AdminElectionManagementSystem getEms() {
        return ems;
    }

    public void setEms(AdminElectionManagementSystem AEMS) {
        this.ems = AEMS;
    }

    public void set(AdminElectionManagementSystem ems, Stage primaryStage) {
        setEms(ems);
        setPrimaryStage(primaryStage);
    }
}
