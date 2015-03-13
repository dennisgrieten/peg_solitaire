package PegSolitaire.dom.io;

import java.util.Map;

/**
 * Created by dennis on 13/03/15.
 */
public class HighScores {
    private HighScoreIO highScoreIO;
    private Map<Integer, String> scoreBoard;

    public HighScores() {
        this.highScoreIO = new FileHighScoreIO();

    }
}
