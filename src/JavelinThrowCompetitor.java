public class JavelinThrowCompetitor extends NTDCompetitor {
    public JavelinThrowCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }


    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && super.getScoreArray().size() < 6 && !super.getScoreArray().contains(score)) {
            super.getScoreArray().add(score);
        }
    }


    public String getShortDetails() {
        return super.getShortDetails() + "\nCategory: Javelin Throw";
    }


    public String getFullDetails() {
        return super.getFullDetails() + "\nCategory: Javelin Throw";
    }
}


