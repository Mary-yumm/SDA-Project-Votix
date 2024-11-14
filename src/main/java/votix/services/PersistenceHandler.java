package votix.services;


import votix.models.Candidate;
import votix.models.PollingStaff;
import votix.models.PollingStation;

import java.util.ArrayList;

public abstract class PersistenceHandler {
    protected String connectionString;
    protected String username;
    protected String password;
    protected String databaseName;
    protected boolean isConnected = false;
    protected int connectionTimeout;
    protected int maxRetries;

    // Abstract methods to be implemented by subclasses
    public abstract void ShowPollingStation();

    public abstract boolean connect();
    public abstract boolean disconnect();
    public abstract void executeQuery(String query);
    public abstract int executeUpdate(String query);
    public abstract void beginTransaction();
    public abstract void commit();
    public abstract void rollback();
    public abstract void biometricVerification();
    public abstract void retrieveVotes();
    public abstract void updateCount();
    public abstract void saveReport();
    public abstract ArrayList<String> reportData();
    public abstract ArrayList<String> getElectionForm();
    public abstract ArrayList<String> electionReportData();
    public abstract void log();
    public abstract void updatePollingStaffAccount(PollingStaff staff);
    public abstract void addPollingStaffAccount(PollingStaff staff);
    public abstract void deactivatePollingStaffAccount(PollingStaff staff);
    public abstract void addCandidate(Candidate candidate);
    public abstract ArrayList<Candidate> loadCandidateData();
    public abstract ArrayList<PollingStaff> loadPollingStaffAssignments();
    public abstract int verifyStaff(String login, String password,String mac_address);
    public abstract ArrayList<Candidate> fetchCandidates(int areaid);
    public abstract boolean isVoterRegistered(String name,String cnic,int areaid);
    public abstract int fetchArea(int stationid);
    public abstract void updateVoteCount(int candid,int areaid);
    public abstract ArrayList<PollingStation> fetchStations(int areaID);
    public abstract void changeVoterStatus(String cnic);
}


