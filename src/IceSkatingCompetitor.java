import java.util.Collections;
public class IceSkatingCompetitor extends Competitor {
    public IceSkatingCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && super.getScoreArray().size() < 6 && !super.getScoreArray().contains(score)) {
            super.getScoreArray().add(score);
        }
    }

    @Override
    public double getOverallScore() {
        int maxScore = Collections.max(super.getScoreArray());
        int minScore = Collections.min(super.getScoreArray());

        double sum = 0;
        int count = 0;
        for (int score : super.getScoreArray()) {
            if (score != maxScore && score != minScore) {
                sum += score;
                count++;
            } else {
                // Ensure only one instance of max and min are removed
                if (score == maxScore) maxScore = Integer.MIN_VALUE;
                if (score == minScore) minScore = Integer.MIN_VALUE;
            }
        }
        return count > 0 ? sum / count : Double.NaN;
    }

    @Override
    public String getShortDetails() {
        return "CN " + super.getCompetitorID() +
                ", " + super.getCompetitorName().getInitials() +
                ", Score: " + this.getOverallScore();
    }

    @Override
    public String getFullDetails() {
        return "Competitor ID: " + super.getCompetitorID() + "\n" +
                "Competitor Name: " + super.getCompetitorName().getFullName() + "\n" +
                "Competitor Country: " + super.getCompetitorCountry() + "\n" +
                "Competitor Level: " + super.getCompetitorLevel().toString() + "\n" +
                "Competitor Age: " + super.getCompetitorAge() + "\n" +
                "Competitor Overall Score: " + this.getOverallScore();
    }
}


