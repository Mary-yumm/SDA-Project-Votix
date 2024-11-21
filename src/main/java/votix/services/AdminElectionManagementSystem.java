package votix.services;

import votix.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public PollingStaff managePollingStaff() {
        // Implementation for managing polling staff
        return new PollingStaff();
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

    public List<Log> viewLogs() {
        // Implementation to view a specific log entry
        return ph.ViewLogs();
    }

    public List<PollingStationPC> getPollingPCs(){
        return ph.getPollingPCs();
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

    public void searchStaffByStaffName() {
      //  return ph.getStaffByName();
    }
    public boolean addPollingStaff(PollingStaff p){return ph.addPollingStaffAccount(p);}

    public void searchStaffByAreaID() {
    }
    public ArrayList<PollingStaff> getPollingStaff() {
        return ph.getStaffList();
    }

    public ArrayList<Object> getStaffAssignments() {
       return ph.getStaffAssignments();
    }


}
