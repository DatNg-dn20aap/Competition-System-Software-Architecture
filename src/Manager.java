public class Manager {
    private static CompetitorList competitorList;
    private static StaffList staffList;

    //"IceSkating" or "JavelinThrow" for Filetype
    public Manager(String filename,String filetype) {
        competitorList = new CompetitorList(filename,filetype);
        staffList = new StaffList();
    }


    // Main method to run the application
    public static void main(String[] args) {
        //temporary placeholder
        Manager manager = new Manager("IceSkatingCompetitors.csv","IceSkating");
        competitorList.generateReport();
    }
}