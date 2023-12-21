public class Competitor {
    private String competitorID;
    private Name competitorName;
    private Level competitorLevel;
    private int competitorAge;
    private String competitorGender;
    private String competitorCountry;


    public Competitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        this.competitorID = competitorID;
        this.competitorName = competitorName;
        this.competitorLevel = competitorLevel;
        this.competitorAge = competitorAge;
        this.competitorGender = competitorGender;
        this.competitorCountry = competitorCountry;
    }

    public Competitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender) {
        this.competitorID = competitorID;
        this.competitorName = competitorName;
        this.competitorLevel = competitorLevel;
        this.competitorAge = competitorAge;
        this.competitorGender = competitorGender;
        this.competitorCountry = "";
    }

    public String getCompetitorID() {
        return this.competitorID;
    }

    public Name getCompetitorName() {
        return this.competitorName;
    }

    public Level getCompetitorLevel() {
        return this.competitorLevel;
    }

    public int getCompetitorAge() {
        return this.competitorAge;
    }

    public String getCompetitorGender() {
        return this.competitorGender;
    }

    public String getCompetitorCountry() {
        return this.competitorCountry;
    }

    public String getCompetitorFullName() {
        return this.competitorName.getFullName();
    }

    public String getCompetitorLevelString() {
        return this.competitorLevel.toString();
    }

    public void setCompetitorID(String competitorID) {
        this.competitorID = competitorID;
    }

    public void setCompetitorLevel(Level competitorLevel) {
        this.competitorLevel = competitorLevel;
    }

    public void setCompetitorName(String firstName, String middleName, String lastName) {
        this.competitorName.setFirstName(firstName);
        this.competitorName.setMiddleName(middleName);
        this.competitorName.setLastName(lastName);
    }

    public void setCompetitorAge(int competitorAge) {
        this.competitorAge = competitorAge;
    }

    public void setCompetitorGender(String competitorGender) {
        this.competitorGender = competitorGender;
    }

    public void setCompetitorCountry(String competitorCountry) {
        this.competitorCountry = competitorCountry;
    }

    //maybe add a toString method here

}
