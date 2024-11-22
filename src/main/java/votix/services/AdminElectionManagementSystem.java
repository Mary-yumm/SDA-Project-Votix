package votix.services;

import votix.models.Area;
import votix.models.Candidate;
import votix.models.PollingStaff;

import java.util.ArrayList;

public class AdminElectionManagementSystem implements ElectionManagementSystem{

    private PersistenceHandler ph;
    private ArrayList<Area> area;
    private String electionType;

    public AdminElectionManagementSystem(PersistenceHandler ph) {

        area = new ArrayList<>();
        this.ph = ph;
    }

    public void displayAllResults() {
    }

    public boolean addCandidate(Candidate cand, String area) {
        return ph.addCandidate(cand, area);
    }

    public boolean addPollingStaff(PollingStaff p){return ph.addPollingStaffAccount(p);}

    public void managePollingStaff(String operation, int id) {
        if(operation.equals("deactivate")){
            ph.deactivatePollingStaffAccount(id);
        }
        else if(operation.equals("activate")){
            ph.activatePollingStaffAccount(id);
        }
    }
    public ArrayList<String> getPartyNames(){
        return ph.getPartyNames();
    }

    public void initiateSystem() {
        // Implementation to initiate the system
    }

//    public ElectionResult getElectionResult() {
//        return results;
//    }

    public void recountVotes() {
        // Implementation to recount votes
    }

    public void monitorPollingStation() {
        // Implementation to monitor polling station
    }

    public void monitorActiveSystems() {
        // Implementation to monitor active systems
    }

    public void inspectAuditLogs() {
        // Implementation to inspect audit logs
    }

    public void terminateSystem() {
        // Implementation to terminate the system
    }

    public void generateElectionReport() {
        // Implementation to generate election report
    }

    public void issueElectionReport() {
        // Implementation to issue an election report
    }

    public void accessElectionForms() {
        // Implementation to access election forms
    }

    public void displayElectionResults() {
        // Implementation to display election results
    }

    public void filterLogs(String criteria) {
        // Implementation to filter logs
    }

    public void viewLog(String logId) {
        // Implementation to view a specific log entry

    }

    public PersistenceHandler getPersistenceHandler(){
        return this.ph;
    }
    public void setPersistenceHandler(PersistenceHandler handler){
    this.ph =  handler;
    }


    public ArrayList<String> getAreaID(){
    return ph.getAreaID();
    }
//    public void setElectionResult(ElectionResult result) {
//        this.electionResult = result;
//    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public String getElectionType() {
        return electionType;
    }

    // not decided yet

    public ArrayList<Integer> getStations() {
        // Implementation to get stations
        return ph.getStations();
    }

    public ArrayList<Candidate> getCands() {
        // Implementation to get candidates
        System.out.println("Getting candidates:.......");
        if(ph!=null){

            return ph.fetchCandidates(1);
      }
        System.out.println("Errorr...............");
        return new ArrayList<>();
    }
    public ArrayList<Candidate> getAllCand(){
        return ph.fetchAllCandidates();
    }

    public void createLogEntry(String entry) {
        // Implementation to create a log entry
    }

    public ArrayList<PollingStaff> getPollingStaff() {
        return ph.getStaffList();
    }

    public ArrayList<Object> getStaffAssignments() {
       return ph.getStaffAssignments();
    }


    public void updatePollingStaff(Integer staffid, String username, String password, Integer stationid) {
        ph.updatePollingStaffAccount(username, password,staffid, stationid);
    }
}
