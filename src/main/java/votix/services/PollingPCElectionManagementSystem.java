package votix.services;

import votix.models.Area;
import votix.models.Candidate;
import votix.models.PollingStation;
import votix.models.Voter;

import java.util.ArrayList;

public class PollingPCElectionManagementSystem implements ElectionManagementSystem {
    private PersistenceHandler ph;
    private Area area;
    private int stationId;

    public PollingPCElectionManagementSystem() {
        area = new Area();
    }

    public boolean authorizePollingStaff(String username, String password,String mac_address) {
        // Implementation to authorize polling staff
        int is_verified = ph.verifyStaff(username,password,mac_address);

        if(is_verified == 0) {
            return false;
        }
        else{
            stationId = is_verified;
            int areaID = ph.fetchArea(is_verified);
            area.setAreaID(areaID);
            return true;
        }

    }

    public void initializeArrays(){
        area.setCands(ph.fetchCandidates(area.getAreaID()));
        area.setStations(ph.fetchStations(area.getAreaID()));

    }

    public boolean isVoterRegistered(String name,String cnic) {
        // Implementation to check if a voter is registered
        return ph.isVoterRegistered(name,cnic,getAreaId());
    }

    public int getAreaId(){
        return area.getAreaID();
    }

    public int getStationId(){
        return stationId;
    }

    public void setStationId(int id){
        stationId = id;
    }

    public void castVote(int candidateId) {
        // Implementation to cast a vote
        ph.updateVoteCount(candidateId,getAreaId());

    }

    public void updateVoterStatus(String cnic) {
        ph.changeVoterStatus(cnic);
        area.updateVoterStatus(cnic,stationId);
    }

    public boolean getVoterStatus(String cnic,int stationid){
        return area.getVoterStatus(cnic,stationid);
    }

    public boolean validateVote() {
        // Implementation to validate a vote
        return false;
    }

    @Override
    public void setPersistenceHandler(PersistenceHandler handler) {
        this.ph = handler;
    }

    @Override
    public ArrayList<Integer> getStations() {
        return null;
    }

    public PollingStation getStation(int id) {
        for(PollingStation ps:area.getStations()){
            if(ps.getStationID() == id){
                return ps;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Candidate> getCands() {
        return area.getCands();

    }

    @Override
    public void createLogEntry(String entry) {
        ph.createLog(entry);
    }

    public Voter getVoterByCnic(String cnic){
        return ph.getVoterByCnic(cnic);
    }

}
