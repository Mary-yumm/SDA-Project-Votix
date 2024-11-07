package org.example.sdaprojectvotix;

import java.util.ArrayList;

public class PollingStation {
    private int stationID;
    private String location;
    private ArrayList<PollingStaff> assignedPollingStaff;
    private ArrayList<PollingStationPC> pollingPCs;
    private int totalVotesCast;
    private boolean status;

    public int getStationID() { return stationID; }
    public void setStationID(int stationID) { this.stationID = stationID; }

    // Additional methods and getters/setters...
}

