    package votix.models;


    import javafx.scene.image.Image;

    import java.time.LocalDate;
    import java.util.Date;

    public class Candidate {
        private int Cid;
        private String name;
        private String PartyName;
        private boolean eligibilityStatus;
        private java.sql.Date registrationDate;
        private Image PartySymbol;
        private String NAPA;

        public boolean updateCandidateDetails() { return true; }
        private void removeCandidate() {}
        public boolean isEligibility() { return true; }

        public Candidate(){}
        public Candidate(int id, String name, String party, boolean status, String napa){
            this.Cid=id;
            this.name=name;
            this.PartyName=party;
            this.eligibilityStatus=status;
            this.NAPA=napa;
            this.registrationDate = java.sql.Date.valueOf(LocalDate.now());
            this.PartySymbol=  new Image("D:/semester 5/SDA/finalllll/src/main/resources/assets/pti.png");
        }
        public int getCid() { return Cid; }
        public void setCid(int cid) { this.Cid = cid; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPartyName() { return PartyName; }
        public void setPartyName(String partyName) { this.PartyName = partyName; }

        public boolean getEligibility() { return eligibilityStatus; }
        public void setEligibility(boolean eligibility) { this.eligibilityStatus = eligibility; }

        public java.sql.Date getRegistrationDate() { return registrationDate; }
        public void setRegistrationDate(java.sql.Date registrationDate) { this.registrationDate = registrationDate; }

        public Image getPartySymbol() { return PartySymbol; }
        public void setPartySymbol(Image partySymbol) { this.PartySymbol = partySymbol; }

        public String getNAPA() { return NAPA; }
        public void setNAPA(String napa) { this.NAPA = napa; }

        public String getNapa() {return this.NAPA;}
        public String getPartySymbolPath() {return this.PartySymbol.getUrl();}
    }
