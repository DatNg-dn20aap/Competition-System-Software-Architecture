public class IceSkatingCompetitor extends NTDCompetitor {
    public IceSkatingCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    @Override
    public void addScore(int score) {
        if (score >= 0 && score <= 5 && super.getScoreArray().size() < 4 && !super.getScoreArray().contains(score)) {
            super.getScoreArray().add(score);
        }
    }

    public String getShortDetails() {
        return super.getShortDetails() + "\nCategory: Ice Skating";
    }

    @Override
    public String getFullDetails() {
        return super.getFullDetails() + "\nCategory: Ice Skating";
    }
}


