public class JavelinThrowCompetitor extends NTDCompetitor {
    public JavelinThrowCompetitor(String competitorID, Name competitorName, Level competitorLevel, int competitorAge, String competitorGender, String competitorCountry) {
        super(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
    }

    public String getShortDetails() {
        return super.getShortDetails() + "\nCategory: Javelin Throw";
    }


    public String getFullDetails() {
        return super.getFullDetails() + "\nCategory: Javelin Throw";
    }
}


