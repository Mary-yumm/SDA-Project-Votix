package votix;

import javafx.scene.image.Image;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

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
    public boolean verifyStaff(String username, String password) {
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
                return false;
            }

            // Get the stationId from the POLLINGSTAFF table
            int stationId = rs.getInt("stationId");

            // Step 2: Retrieve the MAC address of the current machine
            String currentMac = getCurrentMacAddress();
            System.out.println("Current PC MAC Address: " + currentMac);

            if (currentMac == null) {
                System.out.println("Unable to retrieve MAC address.");
                return false;
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
                    return true;
                }
            }

            System.out.println("MAC address mismatch or station unauthorized.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if verification fails
    }


    private String getCurrentMacAddress() {
        try {
            // Get all network interfaces (returns Enumeration)
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // Skip non-physical interfaces (e.g., loopback, docker)
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                // Ensure networkInterface has a MAC address
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    StringBuilder macAddress = new StringBuilder();
                    for (byte macByte : mac) {
                        macAddress.append(String.format("%02X:", macByte));
                    }

                    // Remove trailing colon
                    if (macAddress.length() > 0) {
                        macAddress.setLength(macAddress.length() - 1);
                    }

                    System.out.println("Detected MAC Address: " + macAddress);
                    return macAddress.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Unable to retrieve MAC address.");
        return null;
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
}
