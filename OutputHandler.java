import java.util.Scanner;

public class OutputHandler {

    private ScoreGenerator estimator;
    private String text;

    public OutputHandler(String text) {
        this.estimator = new ScoreGenerator(text);
        this.text = text;
    }

    public void analyze() {
        simpleAnalysis();
        complexAnalysis();
    }

    private void simpleAnalysis() {
        System.out.println("\nSimple estimation based on length : " + estimator.SimpleEstimation());
        System.out.println("Simple estimation based on Words Per Sentences : " + estimator.wordsAndSentences() + "\n");

        System.out.println("The text is: " + text + "\n");
        System.out.println("Words: " + estimator.getWords());
        System.out.println("Sentences: " + estimator.getSentences());
        System.out.println("Characters: " + estimator.getCharacters());
        System.out.println("Syllables: " + estimator.getSyllables());
        System.out.println("Polysyllables: " + estimator.getPolysyllables() + "\n");
    }

    private void complexAnalysis() {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): \n");
        Scanner user = new Scanner(System.in);
        String choice = user.next();

        double ARIscore = estimator.getARIScore();
        int ageGroupBasedARI = estimator.getAgeGroup(ARIscore);

        double FKscore = estimator.getFKRScore();
        int ageGroupBasedFK = estimator.getAgeGroup(FKscore);

        double SMOGscore = estimator.getSMOGScore();
        int ageGroupBasedSMOG = estimator.getAgeGroup(SMOGscore);

        double CLscore = estimator.getCLScore();
        int ageGroupBasedCL = estimator.getAgeGroup(CLscore);

        switch (choice) {
            case "ARI" -> System.out.format("\nAutomated Readability Index: %.2f (about %d year olds). \n",
                ARIscore, ageGroupBasedARI);
            case "FK" -> System.out
                .format("\nFlesch–Kincaid readability tests: %.2f (about %d year olds). \n",
                    FKscore, ageGroupBasedFK);
            case "SMOG" -> System.out.format("\nSimple Measure of Gobbledygook: %.2f (about %d year olds). \n",
                SMOGscore, ageGroupBasedSMOG);
            case "CL" -> System.out.format("\nColeman–Liau index: %.2f (about %d year olds). \n", CLscore,
                ageGroupBasedCL);
            case "all" -> {
                System.out.format("\nAutomated Readability Index: %.2f (about %d year olds). \n",
                    ARIscore, ageGroupBasedARI);
                System.out.format("Flesch–Kincaid readability tests: %.2f (about %d year olds). \n",
                    FKscore, ageGroupBasedFK);
                System.out.format("Simple Measure of Gobbledygook: %.2f (about %d year olds). \n",
                    SMOGscore, ageGroupBasedSMOG);
                System.out.format("Coleman–Liau index: %.2f (about %d year olds). \n", CLscore,
                    ageGroupBasedCL);

                double ageGroupAvg =
                    (ageGroupBasedARI + ageGroupBasedFK + ageGroupBasedSMOG + ageGroupBasedCL)
                        / 4.0;
                System.out
                    .format("\nThis text should be understood in average by %.2f year olds. \n",
                        ageGroupAvg);
            }
        }
        user.close();
    }
}
