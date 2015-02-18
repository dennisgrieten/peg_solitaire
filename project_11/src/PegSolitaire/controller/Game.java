package PegSolitaire.controller;

import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;
import PegSolitaire.dom.Field;
import java.lang.String;

/**
 * Created by dennis on 28/01/15.
 */
public class Game {
    private Field field;
    private boolean endGame;

    public Game() {
        this.field = new Field(7, 7);
        this.endGame = false;
    }

    /* Doe een zet */
    public void doMove(int x, int y, int x1, int y1) throws IllegalCoordinateException, IllegalMoveException {
        field.doMove(x, y, x1, y1);
        checkEndGame();
    }

    /**
     * Controleer of het spel gedaan is
     * Maximum aantal ballen - 1 zitten in de stack
     * of er kan geen zet meer gedaan worden (nog te implementeren!)
    */
    private void checkEndGame() {
        if (field.getMoveCount() == 31) {
            endGame = true;
            return;
        }
    }

    /* Maak een zet ongedaan */
    public void undoMove() {
        field.undoMove();
    }

    public boolean isEndgame() {
        return endGame;
    }

    public String printField() {
        return field.toString();
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
