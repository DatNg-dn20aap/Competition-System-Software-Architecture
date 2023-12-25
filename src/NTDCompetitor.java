import java.util.ArrayList;
public class NTDCompetitor {
    private String competitorID;
    private Name competitorName;
    private Level competitorLevel;
    private int competitorAge;
    private String competitorGender;
    private String competitorCountry;
    private ArrayList<Integer> competitorScore;

    public NTDCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        this.competitorID = competitorID;
        this.competitorName = competitorName;
        this.competitorLevel = competitorLevel;
        this.competitorAge = competitorAge;
        this.competitorGender = competitorGender;
        this.competitorCountry = competitorCountry;
        this.competitorScore = new ArrayList<Integer>();
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

    public ArrayList<Integer> getScoreArray() {
        return this.competitorScore;
    }

    public double getOverallScore() {
        return this.competitorScore.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
    }

    public boolean addScore(int score) {
        if (score >= 0 && score <= 5 && this.competitorScore.size() < 4 && !this.competitorScore.contains(score)) {
            this.competitorScore.add(score);
            return true;
        }
        return false; // Return false if the score can't be added
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

    public String getShortDetails() {
        return "CN " + this.competitorID +
                ", " + this.competitorName.getInitials() +
                ", Score: " + this.getOverallScore();
    }

    public String getFullDetails() {
        return "Competitor ID: " + this.competitorID + "\n" +
                "Competitor Name: " + this.competitorName.getFullName() + "\n" +
                "Competitor Country: " + this.competitorCountry + "\n" +
                "Competitor Level: " + this.competitorLevel.toString() + "\n" +
                "Competitor Age: " + this.competitorAge + "\n" +
                "Competitor Overall Score: " + this.getOverallScore();
    }

}
