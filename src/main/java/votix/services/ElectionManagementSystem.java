package votix.services;

import votix.models.Candidate;
import votix.models.PollingStation;

import java.util.ArrayList;

public interface ElectionManagementSystem {

    public abstract void setPersistenceHandler(PersistenceHandler handler);

    //public void setElectionResult(ElectionResult result);



    // not decided yet

    public ArrayList<PollingStation> getStations();

    public ArrayList<Candidate> getCands();

    public void createLogEntry(String entry);

}
