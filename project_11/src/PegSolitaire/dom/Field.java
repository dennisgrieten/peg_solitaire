package PegSolitaire.dom;

import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Field {
    private Hole[][] matrix;
    private Stack<Peg> stack = new Stack<Peg>();        // Stack voor verwijderde ballen
    private Stack<Coordinate> moveHistory = new Stack<Coordinate>();
    private byte[] deadZoneMap = new byte[]{0, 1, 5, 6};

    public Field(int dimensionX, int dimensionY) {
        matrix = new Hole[dimensionY][dimensionX];
        initField();
    }

    /* Maak matrix aan */
    private void initField() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Hole(new Peg(), new Coordinate(i,j), this);
            }
        }

        /* Vlag onbruikbare velden */
        for (byte i : deadZoneMap) {
            for (byte j : deadZoneMap) {
                matrix[i][j].setDeadZone(true);
            }
        }

        matrix[3][3].clearPeg();        // Delete middelste bal
    }

    /* Groote van de stack reflecteerd het aantal zetten. */
    public int getMoveCount() {
        return stack.size();
    }

    public void doMove(int x, int y, int x1, int y1) throws IllegalCoordinateException, IllegalMoveException {
        try {
            if (inField(x, y) || inField(x1, y1)) {
                if (isLegalMove(x, y, x1, y1)) {
                    movePeg(x, y, x1, y1);

                } else {
                    throw new IllegalMoveException("Illegal move");
                }
            } else {
                throw new IllegalCoordinateException("Invalid coordinates (dead zone)");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalCoordinateException("Invalid coordinates (outside field)");
        }
    }

    /* Maak de laatste zet ongedaan */
    public void undoMove() {
        if (stack.size() != 0) {
            resetPeg(stack.pop());
            Coordinate a =  moveHistory.pop();
            Coordinate b =  moveHistory.pop();
            matrix[b.x()][b.y()].setPeg(matrix[a.x()][a.y()].givePeg());
        }
    }

    /* Verwijder bal en plaats op de stack */
    private void pushPeg(Coordinate c) {
        stack.push(matrix[c.x()][c.y()].givePeg(true));       // Verplaats bal van opgegeven vak naar de stack
    }

    private void movePeg(int x, int y, int x1, int y1) {
        matrix[x1][y1].setPeg(matrix[x][y].givePeg());        // Overhandig bal van één vak naar het ander
        pushPeg(getVictim(x, y, x1, y1));                     // Verwijder bal
        moveHistory.push(new Coordinate(x, y));               // Plaats coördinaat zet beginvak in geschiedenis
        moveHistory.push(new Coordinate(x1, y1));             // Plaats coördinaat zet eindvak in geschiedinis
    }

    private void resetPeg(Peg peg) {
        Coordinate c = peg.popHistory();        // Pop coördinaat van bal geschiedenis in tijdelijke pointer
        matrix[c.x()][c.y()].setPeg(peg);       // Zet bal terug op het veld met oude coördinaten
    }

    /* Controleer of de gegeven coördinaten in het veld en buiten de dode zone liggen */
    private boolean inField(int x, int y) {
        return x <= matrix.length && y <= matrix[x].length && x >= 0 && y >= 0 ?
                (matrix[y][x].isDeadZone() ? false : true) : false;
    }

    /* Controleer of de gegeven coördinaten geldig zijn voor een zet */
    private boolean isLegalMove(int x, int y, int x1, int y1) {
        boolean b = false;

        if (matrix[x1][y1].hasPeg()) {      // Controleer of (x1,y1) geen bal bevat
            return false;
        }

        if (x != x1 && y != y1) {           // Controleer of de vector diagonaal is
            return false;
        }

        if (x == x1) {                      // Als vector op x-as ligt
            if (y > y1) {                   // Controleer of maar één vak tussen de coördinaten ligt
                b |= (y - y1 == 2);         //
            } else {                        //
                b |= (y1 - y == 2);         //
            }
        } else if (y == y1) {               // Als vector op y-as ligt
            if (x > x1) {                   // Controleer of maar één vak tussen de coördinaten ligt
                b |= (x - x1 == 2);         //
            } else {                        //
                b |= (x1 - x == 2);         //
            }
        }

        if (b) {
            Coordinate c = getVictim(x, y, x1, y1);             // Haal veld tussen (x,y) en (x1,y1)
            return matrix[c.x()][c.y()].hasPeg();               // Controleer of dit veld een bal heeft
        }

        return b;
    }

    /* Bereken in welke richting de zet gedaan werd om de juiste bal weg te nemen */
    private Coordinate getVictim(int x, int y, int x1, int y1) {
        if (x == x1) {
            if (y < y1) {
                return new Coordinate(x, y + 1);
            } else {
                return new Coordinate(x, y - 1);
            }
        } else {
            if (x < x1) {
                return new Coordinate(x + 1, y);
            } else {
                return new Coordinate(x - 1, y);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("  0 1 2 3 4 5 6\n");
        for (int i = 0; i < matrix.length; i++) {
            output.append(i + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                output.append(matrix[j][i].toString()).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }
}
