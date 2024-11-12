package votix;

import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;

public class mysql extends PersistenceHandler{

    private Connection conn;
    private String dbUrl;
    private String username;
    private String password;
    private String databaseName;
    private boolean isConnected;
    private int connectionTimeout;
    private int maxRetries;


    public mysql(String dbUrl,String username,String password) throws ClassNotFoundException {
        // replace with your password
        try {
            // Optional: Load the SQL Server JDBC driver
            // Class.forName("org.mariadb.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(dbUrl, username, password);
            if (conn != null) {
                System.out.println("Connection established successfully.");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void ShowPollingStation(){
            String query = "SELECT * FROM PollingStation";
            try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int stationID = rs.getInt("StationID");
                    String name = rs.getString("Name");
                    String location = rs.getString("Location");
                    int capacity = rs.getInt("Capacity");

                    System.out.println("Station ID: " + stationID +
                            ", Name: " + name +
                            ", Location: " + location +
                            ", Capacity: " + capacity);
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving polling stations: " + e.getMessage());
            }
    }

    @Override
    public ArrayList<Candidate> fetchCandidates() {
        ArrayList<Candidate> candidates = new ArrayList<>();
        try {
            String query = "SELECT * FROM CANDIDATE";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setCid(rs.getInt("candidateId"));  // Correct column name
                candidate.setName(rs.getString("name"));     // Correct column name
                String imagePath = rs.getString("partySymbol");

                // Print the image path to the console
                System.out.println("Image Path: " + imagePath);
                candidate.setPartyName(rs.getString("partyName"));  // Correct column name

                // Maintain original handling for party symbol
                candidate.setPartySymbol(new Image(getClass().getResource("/assets/pti.png").toExternalForm()));
                candidate.setRegistrationDate(rs.getDate("registrationDate"));  // Correct column name
                candidate.setNAPA(rs.getString("naPa"));  // Correct column name

                candidates.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }



    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public boolean disconnect() {
        return false;
    }

    @Override
    public void executeQuery(String query) {

    }

    @Override
    public int executeUpdate(String query) {
        return 0;
    }

    @Override
    public void beginTransaction() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void biometricVerification() {

    }

    @Override
    public void retrieveVotes() {

    }

    @Override
    public void updateCount() {

    }

    @Override
    public void saveReport() {

    }

    @Override
    public ArrayList<String> reportData() {
        return null;
    }

    @Override
    public ArrayList<String> getElectionForm() {
        return null;
    }

    @Override
    public ArrayList<String> electionReportData() {
        return null;
    }

    @Override
    public void log() {

    }

    @Override
    public void updatePollingStaffAccount(PollingStaff staff) {

    }

    @Override
    public void addPollingStaffAccount(PollingStaff staff) {

    }

    @Override
    public void deactivatePollingStaffAccount(PollingStaff staff) {

    }

    @Override
    public void addCandidate(Candidate candidate) {

    }

    @Override
    public ArrayList<Candidate> loadCandidateData() {
        return null;
    }

    @Override
    public ArrayList<PollingStaff> loadPollingStaffAssignments() {
        return null;
    }

    @Override
    public boolean verifyStaff(String login, String password, int areaID, int stationID) {
        return false;
    }

//    @Override
//    public boolean connect() {
//        return false;
//    }
}
