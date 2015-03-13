package PegSolitaire.dom.io;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dennis on 13/03/15.
 */
public class HighScores {
    private HighScoreIO highScoreIO;
    private Map<Integer, String> scoreBoard;

    public HighScores() {
        this.highScoreIO = new FileHighScoreIO();

        try {
            this.scoreBoard = highScoreIO.read(new TreeMap<>());
        } catch (Exception e) {
            this.scoreBoard = new TreeMap<>();
        }
    }

    public void addScore(int score, String name) {
        scoreBoard.put(score, name);
    }

    public void saveScores() throws Exception {
        highScoreIO.write(this.scoreBoard);
    }

    public void loadScores() throws Exception {
        scoreBoard = highScoreIO.read(scoreBoard);
    }

    public Map<Integer, String> getTop5() {
        Map<Integer, String> top5 = new TreeMap<>();
        int i = 0;

        for (Integer score : scoreBoard.keySet()) {
            top5.put(score, scoreBoard.get(score));
            i++;
            if (i > 4) {
                break;
            }
        }

        return top5;
    }

    @Override
    public String toString() {
        Map<Integer, String> top5 = this.getTop5();
        StringBuilder output = new StringBuilder();
        output.append(String.format("# %-10s %-20s\n", "Score", "Naam"));
        int i = 1;

        for (Map.Entry<Integer, String> entry : top5.entrySet()) {
            int score = entry.getKey();
            String name = entry.getValue();
            output.append(String.format("%-2d. %-10d %-20s\n", i, score, name));
            i++;
        }

        return output.toString();
    }
}
