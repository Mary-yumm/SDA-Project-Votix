    package votix;


    import javafx.scene.image.Image;

    import java.util.Date;

    public class Candidate {
        private int Cid;
        private String name;
        private String PartyName;
        private boolean eligibilityStatus;
        private Date registrationDate;
        private Image PartySymbol;
        private String NAPA;

        public boolean updateCandidateDetails() { return true; }
        private void removeCandidate() {}
        public boolean isEligibility() { return true; }

        public int getCid() { return Cid; }
        public void setCid(int cid) { this.Cid = cid; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPartyName() { return PartyName; }
        public void setPartyName(String partyName) { this.PartyName = partyName; }

        public boolean getEligibility() { return eligibilityStatus; }
        public void setEligibility(boolean eligibility) { this.eligibilityStatus = eligibility; }

        public Date getRegistrationDate() { return registrationDate; }
        public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

        public Image getPartySymbol() { return PartySymbol; }
        public void setPartySymbol(Image partySymbol) { this.PartySymbol = partySymbol; }

        public String getNAPA() { return NAPA; }
        public void setNAPA(String napa) { this.NAPA = napa; }
    }
