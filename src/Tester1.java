
public class Tester1 {
    private static CompetitorList iceSkatingCompetitors;
    private static CompetitorList javelinThrowCompetitors;
    public Tester1() {
        iceSkatingCompetitors = new CompetitorList("IceSkatingCompetitors.csv", "IceSkating");
        javelinThrowCompetitors = new CompetitorList("JavelinThrowCompetitors.csv", "JavelinThrow");
    }

    public Tester1(String loadtest) {
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
        Tester1 Tester = new Tester1();
        System.out.println("Loaded Data:");
        iceSkatingCompetitors.addCompetitor(new IceSkatingCompetitor("IS001", new Name("John", "Smith"), Level.Standard, 20, "Male", "USA"));
        javelinThrowCompetitors.addCompetitor(new JavelinThrowCompetitor("IS002", new Name("John", "Smith"), Level.Standard, 20, "Male", "USA"));
        for (int i = 0; i <= 5; i++) {
            iceSkatingCompetitors.getCompetitor("IS001").addScore(i);
        }

        for (int i = 1; i <= 4; i++) {
            javelinThrowCompetitors.getCompetitor("IS002").addScore(i);
        }
        Tester1.saveData();
        // Re-load data from saved files to test saving functionality
        Tester1 Tester2 = new Tester1("loadtest");
        System.out.println("Re-loaded Data:");
        Tester2.saveDataFinal();
    }
}

