package votix;


import java.util.ArrayList;

abstract class PersistenceHandler {
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
    public abstract boolean verifyStaff(String login, String password, int areaID, int stationID);
    public abstract ArrayList<Candidate> fetchCandidates();
}


