public class IceSkatingCompetitor extends Competitor {
    public IceSkatingCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    @Override
    public String toString() {
        return super.toString() + "\nCategory: Ice Skating";
    }
}


