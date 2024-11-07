package org.example.sdaprojectvotix;

import java.sql.*;

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

           // handler = new SQL_Handler("jdbc:mysql://localhost:3306/LMS", "root", "16033004");

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

    /*
    public mysql(String dbUrl, String user, String password) throws ClassNotFoundException {
        // replace with your password
        try {
            // Optional: Load the SQL Server JDBC driver
            // Class.forName("org.mariadb.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(dbUrl, user, password);
            if (conn != null) {
                System.out.println("Connection established successfully.");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */


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

//    @Override
//    public boolean connect() {
//        return false;
//    }
}
