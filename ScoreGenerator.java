public class ScoreGenerator {

    String text;
    double characters = 0;
    double words = 0;
    double sentences = 0;
    double syllables = 0;
    double polysyllables = 0;

    public ScoreGenerator(String text) {
        this.text = text;
        setCharactersInText();
        setWordsInText();
        setSentencesInText();
        setSyllablesAndPolysyllables();
    }

    public String SimpleEstimation() {
        return text.length() > 100 ? "HARD" : "EASY";
    }

    public String wordsAndSentences() {
        String difficulty;
        
        double wordsPerSentences = words / sentences;

        if (wordsPerSentences > 10) {
            difficulty = "HARD";
        } else {
            difficulty = "EASY";
        }

        return difficulty;
    }

    private void setCharactersInText() {
        characters = text.replace(" ","").length();
    }

    private void setWordsInText() {
        words = text
                .replace("! ", " ")
                .replace("? ", " ")
                .replace(". ", " ")
                .replace("  ", " ").split(" ").length;
    }

    private void setSentencesInText() {
        sentences = text.split("[.]|[!]|[?]").length;
    }

    private void setSyllablesAndPolysyllables() {
        String[] sentences = text.split("[!?.]\\s");
        @SuppressWarnings("SpellCheckingInspection") String vowels = "aeiouy";
        for (String sentence : sentences) {
            for (String word : sentence.split("\\s")) {
                String temp = word.toLowerCase();
                int syllablesInWord = word.endsWith("e") ? -1 : 0;
                for (int i = 1; i <= temp.length(); i++) {
                    boolean contains = vowels.contains(String.valueOf(temp.charAt(i - 1)));
                    if (i == temp.length() && contains) {
                        syllablesInWord++;
                    }
                    if (contains && i != temp.length()) {
                        if (!vowels.contains(String.valueOf(temp.charAt(i)))) {
                            syllablesInWord++;
                        }
                    }
                }
                if (syllablesInWord <= 0) {syllablesInWord = 1;}
                syllables += syllablesInWord;
                if (syllablesInWord > 2) {
                    polysyllables++;
                }
            }
        }
    }

    public int getCharacters() {
        return (int) characters;
    }

    public int getWords() {
        return (int) words;
    }

    public int getSentences() {
        return (int) sentences;
    }

    public int getSyllables() {
        return (int) syllables;
    }

    public int getPolysyllables() {
        return (int) polysyllables;
    }

    public double getARIScore() {
        return 4.71 * (characters / words) + 0.5 * ( words / sentences) - 21.43;
    }

    public double getFKRScore() {
        return 0.39 * (words / sentences) + 11.8 * (syllables / words) - 15.59;
    }

    public double getSMOGScore() {
        return 1.043 * Math.sqrt(polysyllables * ( 30 / sentences)) + 3.1291;
    }

    public double getCLScore() {
        return 0.0588 * characters / words * 100 - 0.296 * sentences / words * 100 - 15.8;
    }

    public int getAgeGroup(double score) {
        String[] ages = new String[]{"6", "7", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "24", "24"};
        var ceilScore = (int) Math.ceil(score);
        ceilScore++;
        if (ceilScore < 0) ceilScore = 0;
        if (ceilScore >= 14) ceilScore = 13;
        return Integer.parseInt(ages[ceilScore]);
    }
}
