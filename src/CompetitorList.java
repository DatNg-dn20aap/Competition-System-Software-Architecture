import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class CompetitorList {
    //
    private ArrayList<Competitor> competitorList;

    public CompetitorList() {
        this.competitorList = new ArrayList<Competitor>();
    }

    public CompetitorList(String filename, String competitorType) {
        this.competitorList = new ArrayList<Competitor>();
        this.loadCompetitorsFromFile(filename, competitorType);
    }

    // Method to load Ice Skating Competitors
    public void loadIceSkatingCompetitorsFromFile(String filename) {
        loadCompetitorsFromFile(filename, "IceSkating");
    }

    // Method to load Javelin Throw Competitors
    public void loadJavelinThrowCompetitorsFromFile(String filename) {
        loadCompetitorsFromFile(filename, "JavelinThrow");
    }

    // Generic method to read competitors from a file
    private void loadCompetitorsFromFile(String filename, String type) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Competitor competitor = parseCompetitor(line, type);
                if (competitor != null) {
                    competitorList.add(competitor);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    // Method to parse a line of CSV and create a Competitor object
    private Competitor parseCompetitor(String line, String type) {
        String[] data = line.split(",");
        if (data.length >= 10) {
            String competitorID = data[0];

            // Split the name and create a Name object
            String[] nameParts = data[1].split(" ");
            Name competitorName;
            if (nameParts.length == 3) {
                competitorName = new Name(nameParts[0], nameParts[1], nameParts[2]);
            } else if (nameParts.length == 2) {
                competitorName = new Name(nameParts[0], "", nameParts[1]);
            } else {
                // Handle other cases or throw an exception
                throw new IllegalArgumentException("Invalid name format");
            }

            Level competitorLevel = Level.valueOf(data[2]);
            int competitorAge = Integer.parseInt(data[3]);
            String competitorGender = data[4];
            String competitorCountry = data[5];

            ArrayList<Integer> scores = new ArrayList<>();
            // Start from index 6 to parse scores
            for (int i = 6; i < data.length; i++) {
                if (!data[i].isEmpty()) {  // Ensure non-empty score data
                    scores.add(Integer.parseInt(data[i]));
                }
            }

            Competitor competitor;
            if ("IceSkating".equals(type)) {
                competitor = new IceSkatingCompetitor(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
            } else if ("JavelinThrow".equals(type)) {
                competitor = new JavelinThrowCompetitor(competitorID, competitorName, competitorLevel, competitorAge, competitorGender, competitorCountry);
            } else {
                return null; // Or throw an exception for unknown type
            }

            // Add scores to the competitor
            for (int score : scores) {
                competitor.addScore(score);
            }

            return competitor;
        }
        return null;
    }


    // Method to save competitors to a file
    public void saveCompetitorsToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Competitor competitor : competitorList) {
                writer.write(formatCompetitorForCsv(competitor) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + filename);
            e.printStackTrace();
        }
    }

    // Helper method to format a Competitor object into a CSV line
    private String formatCompetitorForCsv(Competitor competitor) {
        StringBuilder csvLine = new StringBuilder();
        csvLine.append(competitor.getCompetitorID()).append(",");
        csvLine.append(competitor.getCompetitorName().getFullName()).append(",");
        csvLine.append(competitor.getCompetitorLevel().toString()).append(",");
        csvLine.append(competitor.getCompetitorAge()).append(",");
        csvLine.append(competitor.getCompetitorGender()).append(",");
        csvLine.append(competitor.getCompetitorCountry());

        // Append scores
        ArrayList<Integer> scores = competitor.getScoreArray();
        for (int score : scores) {
            csvLine.append(",").append(score);
        }

        return csvLine.toString();
    }


    //public methods
    public ArrayList<Competitor> getCompetitorList() {
        return this.competitorList;
    }

    public void addCompetitor(Competitor competitor) {
        this.competitorList.add(competitor);
    }

    public void removeCompetitor(Competitor competitor) {
        this.competitorList.remove(competitor);
    }

    public Competitor getCompetitor(String competitorID) {
        for (Competitor competitor : competitorList) {
            if (competitor.getCompetitorID().equals(competitorID)) {
                return competitor;
            }
        }
        return null;
    }

    // Method to get full details of all competitors
    public String getAllCompetitorDetails() {
        StringBuilder details = new StringBuilder();
        for (Competitor competitor : competitorList) {
            details.append(competitor.getFullDetails()).append("\n");
            details.append("------------------------------- \n");
        }
        return details.toString();
    }

    // Method to find the competitor with the highest score
    public Competitor getTopCompetitor() {
        Competitor topCompetitor = null;
        for (Competitor competitor : competitorList) {
            if (topCompetitor == null || competitor.getOverallScore() > topCompetitor.getOverallScore()) {
                topCompetitor = competitor;
            }
        }
        return topCompetitor;
    }

    // Example method for a summary statistic - average score
    public double getAverageScore() {
        double totalScore = 0;
        for (Competitor competitor : competitorList) {
            totalScore += competitor.getOverallScore();
        }
        return competitorList.isEmpty() ? 0 : totalScore / competitorList.size();
    }

    // Method to generate a frequency report of scores
    public Map<Integer, Integer> getScoreFrequency() {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Competitor competitor : competitorList) {
            for (int score : competitor.getScoreArray()) {
                frequency.put(score, frequency.getOrDefault(score, 0) + 1);
            }
        }
        return frequency;
    }

    public void generateReport() {
        String report = "Competitor Details:\n" + this.getAllCompetitorDetails();
        report += "\nTop Competitor:\n" + (this.getTopCompetitor() != null ? this.getTopCompetitor().getFullDetails() : "No competitors found.");
        report += "\nAverage Score: " + this.getAverageScore();
        report += "\nScore Frequency Report: " + this.getScoreFrequency().toString();
        // Add other summary statistics to the report as needed

        // Output the report to a file or System.out
        System.out.println(report); // Temporary for debbugging
    }
}
