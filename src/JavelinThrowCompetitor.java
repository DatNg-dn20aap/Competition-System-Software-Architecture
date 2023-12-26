import java.util.ArrayList;
public class JavelinThrowCompetitor extends Competitor {
    public JavelinThrowCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    public String getCompetitorID() {
        return super.getCompetitorID();
    }

    public Name getCompetitorName() {
        return super.getCompetitorName();
    }

    public Level getCompetitorLevel() {
        return super.getCompetitorLevel();
    }

    public int getCompetitorAge() {
        return super.getCompetitorAge();
    }

    public String getCompetitorGender() {
        return super.getCompetitorGender();
    }

    public String getCompetitorCountry() {
        return super.getCompetitorCountry();
    }

    public String getCompetitorFullName() {
        return super.getCompetitorFullName();
    }

    public String getCompetitorLevelString() {
        return super.getCompetitorLevelString();
    }

    public ArrayList<Integer> getScoreArray() {
        return super.getScoreArray();
    }


    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && this.getScoreArray().size() < 4) {
            this.getScoreArray().add(score);
        }
    }

    @Override
    public double getOverallScore() {
        return this.getScoreArray().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
    }

    @Override
    public String getShortDetails() {
        return "CN " + this.getCompetitorID() +
                ", " + this.getCompetitorName().getInitials() +
                ", Score: " + this.getOverallScore();
    }

    @Override
    public String getFullDetails() {
        return "Competitor ID: " + this.getCompetitorID() + "\n" +
                "Competitor Name: " + this.getCompetitorName().getFullName() + "\n" +
                "Competitor Country: " + this.getCompetitorCountry() + "\n" +
                "Competitor Level: " + this.getCompetitorLevel().toString() + "\n" +
                "Competitor Age: " + this.getCompetitorAge() + "\n" +
                "Competitor Overall Score: " + this.getOverallScore();
    }
}


