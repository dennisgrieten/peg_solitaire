package PegSolitaire.dom.io;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dennis on 13/03/15.
 */
public class FileHighScoreIO implements HighScoreIO{
    private final Path scoreFile;

    public FileHighScoreIO() {
        FileSystem f = FileSystems.getDefault();
        scoreFile = f.getPath("highscores");
    }
      @Override
    public void write(Map<Integer, String> scores) throws Exception {
          Formatter formatter = null;

          try {
              formatter = new Formatter(new BufferedWriter(new FileWriter(scoreFile.toFile())));

              for (Map.Entry<Integer, String> entry : scores.entrySet()) {
                  formatter.format("%d;%s", entry.getKey(), entry.getValue());
                  formatter.format("\n");
              }

          } catch (FileNotFoundException e) {
              throw new Exception(String.format("Kon bestand \"%s\" niet vinden", scoreFile.getFileName().toString()), e);
          } catch (IOException e) {
              throw new Exception("Fout tijdens het wegschrijven", e);
          } finally {

              if (formatter != null) {
                  formatter.close();
              }
          }
      }


    @Override
    public Map<Integer, String> read(Map<Integer, String> scores) throws Exception {
        try {
            Scanner scanner = new Scanner(scoreFile.toFile());
            while (scanner.hasNext()) {
                try {
                    String line = scanner.nextLine();
                    String[] lineSplit = line.split(";");
                    Integer score = Integer.parseInt(lineSplit[0]);
                    String name = lineSplit[1];
                    scores.put(score, name);

                } catch (NumberFormatException nfe) {
                    System.out.println("Kon score niet uitlezen");
                }
            }
        } catch (FileNotFoundException e) {
            throw new Exception(String.format("Kon bestand \"%s\" niet vinden", scoreFile.getFileName().toString()), e);
        }

        return scores;
    }
}
