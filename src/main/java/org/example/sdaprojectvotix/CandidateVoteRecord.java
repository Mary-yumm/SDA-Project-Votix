package org.example.sdaprojectvotix;

// CandidateVoteRecord class to hold the details of candidate votes
class CandidateVoteRecord {
    String candidateId;
    int voteCount;
    String area;

    public CandidateVoteRecord(String candidateId, int voteCount, String area) {
        this.candidateId = candidateId;
        this.voteCount = voteCount;
        this.area = area;
    }
}
