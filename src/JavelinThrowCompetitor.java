public class JavelinThrowCompetitor extends NTDCompetitor {
    public JavelinThrowCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && super.getScoreArray().size() < 4 && !super.getScoreArray().contains(score)) {
            super.getScoreArray().add(score);
        }
    }

    @Override
    public double getOverallScore() {
        return super.getScoreArray().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
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


