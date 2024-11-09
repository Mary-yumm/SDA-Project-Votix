package org.example.sdaprojectvotix;

import java.util.ArrayList;

import java.util.ArrayList;

// PollingStation class
public class PollingStation {
    private int stationID;
    private String location;
    private ArrayList<PollingStaff> assignedPollingStaff = new ArrayList<>();
    private ArrayList<PollingStationPC> pollingPCs = new ArrayList<>();
    private int totalVotesCast;
    private boolean status;

    // Getters and Setters
    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<PollingStaff> getAssignedPollingStaff() {
        return assignedPollingStaff;
    }

    public void setAssignedPollingStaff(ArrayList<PollingStaff> assignedPollingStaff) {
        this.assignedPollingStaff = assignedPollingStaff;
    }

    public int getTotalVotesCast() {
        return totalVotesCast;
    }

    public void setTotalVotesCast(int totalVotesCast) {
        this.totalVotesCast = totalVotesCast;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Other Methods
    public ArrayList<Voter> getVoters() {
        // Return list of voters
        return new ArrayList<>();
    }

    public void setVoters(ArrayList<Voter> voters) {
        // Set list of voters
    }

    public void monitorSystemStatus() {
        // Monitor system status
    }

    public PollingStaff manageAssignedStaff() {
        // Manage assigned staff
        return new PollingStaff();
    }
}


