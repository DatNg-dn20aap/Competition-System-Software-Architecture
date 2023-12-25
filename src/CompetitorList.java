import java.util.ArrayList;
public class CompetitorList {
    //
    private ArrayList<Competitor> competitorList;

    public CompetitorList() {
        this.competitorList = new ArrayList<Competitor>();
    }

    public CompetitorList(String filename) {
        this.competitorList = new ArrayList<Competitor>();
        this.loadCompetitorList(filename);
    }







}
