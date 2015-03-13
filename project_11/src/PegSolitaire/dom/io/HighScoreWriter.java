package PegSolitaire.dom.io;

import java.util.Map;

/**
 * Created by dennis on 13/03/15.
 */
public interface HighScoreWriter {
    public void write(Map<Integer, String> scores) throws Exception;
}
