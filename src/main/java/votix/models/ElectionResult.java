package votix.models;

import java.util.ArrayList;

public class ElectionResult {

    private ArrayList<CandidateVoteRecord> candidateVoteRecords;
    // Method to set a single vote for a candidate
    public void setResult(String candidateId) {
        for (CandidateVoteRecord record : candidateVoteRecords) {
            if (record.candidateId.equals(candidateId)) {
                record.voteCount++; // Increment vote count for the candidate
                System.out.println("Vote recorded for candidate: " + candidateId);
                return; // Exit after finding the candidate
            }
        }
        System.out.println("Candidate not found in area");
    }
}
