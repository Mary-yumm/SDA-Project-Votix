package votix.services;

import javafx.scene.image.Image;
import votix.models.*;

import java.sql.*;
import java.util.ArrayList;

public class mysql extends PersistenceHandler {

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

    @Override
    public ArrayList<Candidate> fetchCandidates(int areaId) {
        ArrayList<Candidate> candidates = new ArrayList<>();
        try {
            // SQL query to fetch candidates that belong to the specified areaId
            String query = "SELECT CANDIDATE.* FROM CANDIDATE "
                    + "INNER JOIN CANDIDATE_AREA ON CANDIDATE.candidateId = CANDIDATE_AREA.candidateId "
                    + "WHERE CANDIDATE_AREA.areaId = ?";

            // Prepare the statement
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, areaId);  // Set the areaId parameter

            // Execute the query and retrieve results
            ResultSet rs = ps.executeQuery();

            // Loop through the result set to create Candidate objects
            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setCid(rs.getInt("candidateId"));
                candidate.setName(rs.getString("name"));
                String imagePath = rs.getString("partySymbol");

                // Print the image path to the console
                System.out.println("Image Path: " + imagePath);
                candidate.setPartyName(rs.getString("partyName"));
                candidate.setPartySymbol(new Image(getClass().getResource("/assets/pti.png").toExternalForm()));  // Adjust path as needed
                candidate.setRegistrationDate(rs.getDate("registrationDate"));
                candidate.setNAPA(rs.getString("naPa"));

                // Add the candidate to the list
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }



    @Override
    public int verifyStaff(String username, String password,String currentMac) {
        try {
            // Step 1: Verify staff credentials in the POLLINGSTAFF table
            String query = "SELECT stationId FROM POLLINGSTAFF WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            // If no matching staff is found, return false
            if (!rs.next()) {
                System.out.println("Invalid credentials.");
                return 0;
            }

            // Get the stationId from the POLLINGSTAFF table
            int stationId = rs.getInt("stationId");

            System.out.println("Current PC MAC Address: " + currentMac);

            if (currentMac == null) {
                System.out.println("Unable to retrieve MAC address.");
                return 0;
            }

            // Convert MAC addresses to lowercase for case-insensitive comparison
            currentMac = currentMac.toLowerCase();

            // Step 3: Verify if the stationId's MAC address matches in the POLLINGSTATIONPC table
            query = "SELECT config FROM POLLINGSTATIONPC WHERE stationId = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, stationId);
            rs = ps.executeQuery();

            while (rs.next()) {
                String configMac = rs.getString("config");

                // Convert config MAC address from the database to lowercase
                if (configMac != null && configMac.toLowerCase().equals(currentMac)) {
                    // MAC address matches, verification is successful
                    return stationId;
                }
            }

            System.out.println("MAC address mismatch or station unauthorized.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return false if verification fails
    }



    @Override
    public boolean isVoterRegistered(String name, String cnic, int areaid) {
        boolean isRegistered = false;
        try {
            // First, find all the stationIds for the given areaId
            String stationQuery = "SELECT stationId FROM POLLINGSTATION WHERE areaId = ?";
            PreparedStatement stationStmt = conn.prepareStatement(stationQuery);
            stationStmt.setInt(1, areaid); // Set the areaId as parameter
            ResultSet stationRs = stationStmt.executeQuery();

            // Fetch all stationIds associated with the areaId
            ArrayList<Integer> stationIds = new ArrayList<>();
            while (stationRs.next()) {
                stationIds.add(stationRs.getInt("stationId"));
            }

            // Now, check if any of the stationIds are associated with the given voter
            for (int stationId : stationIds) {
                String query = "SELECT * FROM VOTER WHERE cnic = ? AND name = ? AND stationId = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, cnic);  // Set CNIC as parameter
                ps.setString(2, name);  // Set Name as parameter
                ps.setInt(3, stationId); // Set the current stationId for checking

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    isRegistered = true;
                    break; // If a match is found, no need to check further
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isRegistered;
    }


    @Override
    public int fetchArea(int stationId) {
        // SQL query to fetch areaId using stationId
        String query = "SELECT areaId FROM POLLINGSTATION WHERE stationId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, stationId);  // Set the stationId parameter

            // Execute the query and retrieve the result
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Return the areaId if found
                return rs.getInt("areaId");
            } else {
                // Return a value indicating no area found, you can throw an exception or return -1
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Return -1 or handle error as needed
        }

    }

    @Override
    public void updateVoteCount(int candid, int areaid) {
        try {
            // SQL query to update vote count for the given candidate and area
            String query = "UPDATE ELECTIONRESULT SET voteCount = voteCount + 1 WHERE candidateId = ? AND areaId = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            // Set the parameters for candidateId and areaId
            ps.setInt(1, candid);
            ps.setInt(2, areaid);

            // Execute the update
            int rowsUpdated = ps.executeUpdate();

            // If no rows were updated, candidate and area might not exist
            if (rowsUpdated == 0) {
                System.out.println("No matching record found for candidateId " + candid + " in areaId " + areaid);
            } else {
                System.out.println("Vote count updated successfully for candidateId " + candid + " in areaId " + areaid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public ArrayList<PollingStation> fetchStations(int areaID) {
        ArrayList<PollingStation> pollingStations = new ArrayList<>();

        try {
            // Query to fetch stations by areaID
            String stationQuery = "SELECT * FROM POLLINGSTATION WHERE areaId = ?";
            PreparedStatement stationPs = conn.prepareStatement(stationQuery);
            stationPs.setInt(1, areaID);
            ResultSet stationRs = stationPs.executeQuery();

            while (stationRs.next()) {
                int stationId = stationRs.getInt("stationId");

                // Create PollingStation object
                PollingStation pollingStation = new PollingStation();
                pollingStation.setStationID(stationId);

                // Fetch and set PollingStaff for this station
                ArrayList<PollingStaff> staffList = fetchPollingStaff(stationId);
                pollingStation.setAssignedPollingStaff(staffList);

                // Fetch and set PollingStationPCs for this station
                ArrayList<PollingStationPC> pcList = fetchPollingStationPCs(stationId);
                pollingStation.setPollingPCs(pcList);

                // Fetch and set Voters for this station
                ArrayList<Voter> votersList = fetchVoters(stationId);
                pollingStation.setVoters(votersList);

                pollingStations.add(pollingStation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pollingStations;
    }


    @Override
    public void changeVoterStatus(String cnic) {
        String sql = "UPDATE VOTER SET status = TRUE WHERE cnic = ?";

        try  // Assuming you have a Database class for connection
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, cnic);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Voter status updated to true for CNIC: " + cnic);
            } else {
                System.out.println("No voter found with CNIC: " + cnic);
            }
        } catch (SQLException e) {
            System.out.println("Error updating voter status: " + e.getMessage());
        }
    }

    // Helper method to fetch PollingStaff by stationId
    private ArrayList<PollingStaff> fetchPollingStaff(int stationId) throws SQLException {
        ArrayList<PollingStaff> staffList = new ArrayList<>();

        String staffQuery = "SELECT * FROM POLLINGSTAFF WHERE stationId = ?";
        PreparedStatement staffPs = conn.prepareStatement(staffQuery);
        staffPs.setInt(1, stationId);
        ResultSet staffRs = staffPs.executeQuery();

        while (staffRs.next()) {
            PollingStaff staff = new PollingStaff();
            staff.setStaffID(staffRs.getInt("staffId"));
            staff.setName(staffRs.getString("name"));
            staff.setAssignedStation(staffRs.getInt("stationId"));
            staff.setRole(staffRs.getString("role"));
            staff.setUsername(staffRs.getString("username"));
            staff.setPassword(staffRs.getString("password"));

            staffList.add(staff);
        }
        return staffList;
    }

    // Helper method to fetch PollingStationPCs by stationId
    private ArrayList<PollingStationPC> fetchPollingStationPCs(int stationId) throws SQLException {
        ArrayList<PollingStationPC> pcList = new ArrayList<>();

        String pcQuery = "SELECT * FROM POLLINGSTATIONPC WHERE stationId = ?";
        PreparedStatement pcPs = conn.prepareStatement(pcQuery);
        pcPs.setInt(1, stationId);
        ResultSet pcRs = pcPs.executeQuery();

        while (pcRs.next()) {
            PollingStationPC pc = new PollingStationPC();
            pc.setSystemID(pcRs.getString("systemId"));
            pc.setStationID(pcRs.getInt("stationId"));
            pc.setSystemStatus("Active".equals(pcRs.getString("systemStatus")));
            pc.setConfigurationSettings(pcRs.getString("config"));

            pcList.add(pc);
        }
        return pcList;
    }

    // Method to fetch voters for a specific polling station
    private ArrayList<Voter> fetchVoters(int stationID) {
        ArrayList<Voter> votersList = new ArrayList<>();
        String voterQuery = "SELECT * FROM VOTER WHERE stationId = ?";

        try (PreparedStatement voterPs = conn.prepareStatement(voterQuery)) {
            voterPs.setInt(1, stationID);
            ResultSet voterRs = voterPs.executeQuery();

            while (voterRs.next()) {
                String cnic = voterRs.getString("cnic");
                String name = voterRs.getString("name");
                String gender = voterRs.getString("gender");
                boolean status = voterRs.getBoolean("status");

                // Assuming you have a Voter class with a constructor that takes these parameters
                Voter voter = new Voter(cnic, name, gender, status);
                System.out.println("voterrr: "+voter.getStatus());
                votersList.add(voter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return votersList;
    }




    @Override
    public ArrayList<Candidate> loadCandidateData() {
        return null;
    }

    @Override
    public ArrayList<PollingStaff> loadPollingStaffAssignments() {
        return null;
    }

//    @Override
//    public boolean connect() {
//        return false;
//    }


    @Override
    public void ShowPollingStation() {

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
    public void log(String message) {
        String sql = "INSERT INTO AUDITLOG (action, timeStamp) VALUES (?, NOW())";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, message);
            pstmt.executeUpdate();
            System.out.println("Log entry successfully added: " + message);
        } catch (SQLException e) {
            System.err.println("Failed to log message: " + message);
            e.printStackTrace();
        }
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
}
