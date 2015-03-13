package PegSolitaire.dom.io;

import java.util.Map;

/**
 * Created by dennis on 13/03/15.
 */
public interface HighScoreReader {
    public Map<Integer, String> read(Map<Integer, String> scores) throws Exception;
}
