public class IceSkatingCompetitor extends NTDCompetitor {
    public IceSkatingCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    public String getShortDetails() {
        return super.getShortDetails() + "\nCategory: Ice Skating";
    }

    @Override
    public String getFullDetails() {
        return super.getFullDetails() + "\nCategory: Ice Skating";
    }
}


