
public class TesterLoadingSaving {
    private static CompetitorList iceSkatingCompetitors;
    private static CompetitorList javelinThrowCompetitors;
    public TesterLoadingSaving() {
        iceSkatingCompetitors = new CompetitorList("IceSkatingCompetitors.csv", "IceSkating");
        javelinThrowCompetitors = new CompetitorList("JavelinThrowCompetitors.csv", "JavelinThrow");
    }

    public TesterLoadingSaving(String loadtest) {
        if (loadtest.equals("loadtest")) {
            iceSkatingCompetitors = new CompetitorList("Test1.csv", "IceSkating");
            javelinThrowCompetitors = new CompetitorList("Test2.csv", "JavelinThrow");
        }
    }

    public static void saveData() {
        iceSkatingCompetitors.saveCompetitorsToFile("Test1.csv");
        javelinThrowCompetitors.saveCompetitorsToFile("Test2.csv");
    }

    public void saveDataFinal() {
        iceSkatingCompetitors.saveCompetitorsToFile("Test1final.csv");
        javelinThrowCompetitors.saveCompetitorsToFile("Test2final.csv");
    }

    // Main method to run the application
    public static void main(String[] args) {
        TesterLoadingSaving Tester = new TesterLoadingSaving();
        System.out.println("Loaded Data:");
        iceSkatingCompetitors.addCompetitor(new IceSkatingCompetitor("IS001", new Name("John", "Smith"), Level.Standard, 20, "Male", "USA"));
        javelinThrowCompetitors.addCompetitor(new JavelinThrowCompetitor("IS002", new Name("John", "Smith", "Dev"), Level.Standard, 20, "Male", "USA"));
        for (int i = 0; i <= 5; i++) {
            iceSkatingCompetitors.getCompetitorByID("IS001").addScore(i);
        }

        for (int i = 1; i <= 4; i++) {
            javelinThrowCompetitors.getCompetitorByID("IS002").addScore(i);
        }
        TesterLoadingSaving.saveData();
        // Re-load data from saved files to test saving functionality
        TesterLoadingSaving Tester1 = new TesterLoadingSaving("loadtest");
        System.out.println("Re-loaded Data:");
        Tester1.saveDataFinal();
    }
}

