package PegSolitaire.controller;

import PegSolitaire.dom.exceptions.IllegalCoordinateException;
import PegSolitaire.dom.exceptions.IllegalMoveException;
import PegSolitaire.dom.field.Field;
import PegSolitaire.dom.io.FieldSerializer;
import PegSolitaire.dom.io.HighScoreIO;
import PegSolitaire.dom.io.HighScores;

import java.lang.String;

/**
 * Created by dennis on 28/01/15.
 */
public class Game {
    private Field field;
    private FieldSerializer fieldSerializer;
    private HighScores highScores;
    private boolean endGame;
    private boolean gameOver;

    public Game() {
        this.highScores = new HighScores();
        this.fieldSerializer = new FieldSerializer();
        initGame();
    }

    public void initGame() {
        this.field = new Field(7, 7);
        this.gameOver = false;
        this.endGame = false;
    }

    public int getCurrentPegCount() {
        return field.getCurrentPegCount();
    }

    public int getMoveCount() {
        return field.getMoveCount();
    }

    public int getDimensionX() {
        return field.getDimensionX();
    }

    public int getDimensionY() {
        return field.getDimensionY();
    }

    /**
     * Controleer of het spel gedaan is
     * Maximum aantal ballen - 1 zitten in de stack
     */
    private void checkEndGame() {
        if (field.getStackSize() == (field.getPegCount() - 1)) {
            endGame = true;
        }
    }

    private void checkGameOver() {
        gameOver = !field.hasSelectable();
    }

    /* Doe een zet */
    public void doMove(int x, int y, int x1, int y1) throws IllegalCoordinateException, IllegalMoveException {
        field.doMove(x, y, x1, y1);
        checkGameOver();
        checkEndGame();
    }

    /* Maak een zet ongedaan */
    public void undoMove() {
        field.undoMove();
    }

    /* Controleer of het een legale zet is */
    public boolean isLegalMove(int x, int y, int x1, int y1) {
        return field.isLegalMove(x, y, x1, y1);
    }

    public boolean isEndgame() {
        return endGame;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void saveGame() {
        fieldSerializer.serializeField(this.field);
    }

    public void loadGame() {
        this.field = fieldSerializer.deserializeField();
    }

    public Field getField() {
        return this.field;
    }

    public String printField() {
        return field.toString();
    }


    public void saveScores() throws Exception {
        highScores.saveScores();
    }

    public void loadScores() throws Exception {
        highScores.loadScores();
    }

    public void addScore(int score, String name) {
        highScores.addScore(score, name);
    }

    public String getTop5() {
        return highScores.toString();
    }

    @Override
    public String toString() {
        return "### Peg Solitaire ###\n\n" +
                "A player selects a peg and then moves it across the board by legally jumping any other peg that it chooses.\n" +
                "Whenever a peg is jumped, it is removed from the board and is out of play.\n\n" +
                "Pegs can only jump over pegs that are horizontally or vertically adjacent to the jumping peg, \n" +
                "and can only jump over one peg at a time.\n\n" +
                "Diagonal jumps are not allowed, and there must always be an empty target hole on the other side of the peg \n" +
                "being jumped in order for the move to be legal (they can't jump over one peg and land on another).\n";
    }
}
