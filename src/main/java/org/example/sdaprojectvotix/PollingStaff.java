package org.example.sdaprojectvotix;

// PollingStaff class
public class PollingStaff {
    private int staffID;
    private String name;
    private int assignedStation;
    private String role;
    private String username;
    private String password;

    // Methods
    public void login() {
        // Login logic
    }

    public void editAccount(PollingStaff account) {
        // Edit account details
    }

    public void deactivateAccount(PollingStaff account) {
        // Deactivate account
    }

    public void manageAccount() {
        // Manage account details
    }

    public void viewAssignments() {
        // View assignments
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAssignedStation() {
        return assignedStation;
    }

    public void setAssignedStation(int assignedStation) {
        this.assignedStation = assignedStation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

