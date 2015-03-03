package PegSolitaire.dom;

import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;
import java.util.Stack;

/**
 * Created by dennis on 28/01/15.
 */
public class Field {
    private Hole[][] matrix;
    private Stack<Peg> stack = new Stack<>();        // Stack voor verwijderde ballen
    private Stack<Coordinate> moveHistory = new Stack<Coordinate>();
    private Stack<Coordinate> jumpedHoles = new Stack<>();
    private Coordinate lastMoveDestination;
    private int moveCount;
    private int[] deadZoneMap = new int[]{0, 1, 5, 6};

    public Field(int dimensionX, int dimensionY) {
        matrix = new Hole[dimensionY][dimensionX];
        moveCount = 0;
        lastMoveDestination = new Coordinate(-1, -1);   // Foutieve coördinaten voor de eerste zet (anders nullpointer exception)
        initField();
    }

    /* Maak matrix aan */
    private void initField() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Hole(new Peg(), new Coordinate(i, j), this);
            }
        }

        /* Vlag onbruikbare velden */
        for (int i : deadZoneMap) {
            for (int j : deadZoneMap) {
                matrix[i][j].setDeadZone(true);
                matrix[i][j].clearPeg();
            }
        }

        matrix[3][3].clearPeg();        // Delete middelste bal
    }

    /* Groote van de stack reflecteerd het aantal zetten. */
    public int getStackCount() {
        return stack.size();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void doMove(int x, int y, int x1, int y1) throws IllegalCoordinateException, IllegalMoveException {
        try {
            if (inField(x, y) && inField(x1, y1)) {
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

    /* Verwijder bal en plaats op de stack */
    private void pushPeg(Coordinate c, int x1, int y1) {
        int x = c.x();
        int y = c.y();
        stack.push(matrix[x][y].givePeg(true));             // Verplaats bal van opgegeven vak naar de stack
        matrix[x][y].setJumped(true);                       // Markeer als oversprongen
        jumpedHoles.push(c);                                // Zet op de stack van oversprongen vakken
    }

    private void movePeg(int x, int y, int x1, int y1) {
        matrix[x1][y1].setPeg(matrix[x][y].givePeg());      // Overhandig bal van één vak naar het ander
        int lastX = lastMoveDestination.x();
        int lastY = lastMoveDestination.y();


        boolean otherPeg = false;
        otherPeg |= x != lastX;
        otherPeg |= y != lastY;
        /**
         * Als het niet dezelfde bal is als de voorlaatste zet
         * markeer dan alle jumped vakken als false en verhoog moveCount
         */
        if (otherPeg) {
            clearJumped();
            moveCount++;
        }

        moveHistory.push(new Coordinate(x, y));             // Plaats coördinaat zet beginvak in geschiedenis
        moveHistory.push(new Coordinate(x1, y1));           // Plaats coördinaat zet eindvak in geschiedinis
        pushPeg(getVictim(x, y, x1, y1), x1, y1);           // Verwijder bal en zet op de stack
        this.lastMoveDestination = new Coordinate(x1, y1);  // Update coördinaten van laatste eindvak
    }

    /* Markeer alle jumped vakken als false */
    private void clearJumped() {
        Coordinate c;
        for (int i = 0; i < jumpedHoles.size(); i++) {
            c = jumpedHoles.pop();
            matrix[c.x()][c.y()].setJumped(false);
        }
    }

    /* Maak de laatste zet ongedaan */
    public void undoMove() {
        if (stack.size() != 0) {
            for (int i = 0; i < jumpedHoles.size(); i++) {
                resetPeg(stack.pop());
                Coordinate a = moveHistory.pop();
                Coordinate b = moveHistory.pop();
                matrix[b.x()][b.y()].setPeg(matrix[a.x()][a.y()].givePeg());
            }
            clearJumped();
            moveCount--;
        }
    }

    private void resetPeg(Peg peg) {
        Coordinate c = peg.popHistory();        // Pop coördinaat van bal geschiedenis in tijdelijke pointer
        matrix[c.x()][c.y()].setPeg(peg);       // Zet bal terug op het veld met oude coördinaten
    }

    /* Controleer of de gegeven coördinaten in het veld en buiten de dode zone liggen */
    private boolean inField(int x, int y) {

        return x < matrix.length && y < matrix[0].length && x >= 0 && y >= 0 ?
                (matrix[y][x].isDeadZone() ? false : true) : false;
    }

    /* Controleer of de gegeven coördinaten geldig zijn voor een zet */
    private boolean isLegalMove(int x, int y, int x1, int y1) {
        boolean b = false;

        if (matrix[x1][y1].hasPeg()) {  // Controleer of (x1,y1) geen bal bevat
            return false;
        }

        if (!matrix[x][y].hasPeg()) {   // Controleer of (x,y) een bal bevat
            return false;
        }

        if (x != x1 && y != y1) {       // Controleer of de vector diagonaal is
            return false;
        }

        if (x == x1) {                  // Als vector op x-as ligt
            if (y > y1) {               // Controleer of er maar één vak tussen de coördinaten ligt
                b = (y - y1 == 2);      //
            } else {                    //
                b = (y1 - y == 2);      //
            }
        } else {                        // Als vector op y-as ligt
            if (x > x1) {               // Controleer of maar er één vak tussen de coördinaten ligt
                b = (x - x1 == 2);      //
            } else {                    //
                b = (x1 - x == 2);      //
            }
        }

        if (b) {
            Coordinate c = getVictim(x, y, x1, y1);     // Haal veld tussen (x,y) en (x1,y1)
            return matrix[c.x()][c.y()].hasPeg();       // Controleer of dit veld een bal heeft
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

    /* Controleer of dit vak selecteerbaar is (met bal) a.d.h.v. naburige vakken */
    public boolean selectable(int x, int y) {
        if (!matrix[x][y].isDeadZone() && matrix[x][y].hasPeg()) {
            if (inField(x, y - 2)) {        // Noord
                if (!matrix[x][y - 2].hasPeg() && matrix[x][y - 1].hasPeg()) {
                    return true;
                }
            }

            if (inField(x, y + 2)) {        // Zuid
                if (!matrix[x][y + 2].hasPeg() && matrix[x][y + 1].hasPeg()) {
                    return true;
                }
            }

            if (inField(x - 2, y)) {         // West
                if (!matrix[x - 2][y].hasPeg() && matrix[x - 1][y].hasPeg()) {
                    return true;
                }
            }

            if (inField(x + 2, y)) {         // Oost
                if (!matrix[x + 2][y].hasPeg() && matrix[x + 1][y].hasPeg()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Controleer of het veld nog speel opties bevat
     * Zal blijven itereren over het veld tot het een speeloptie vindt
     */
    public boolean hasSelectable() {
        for (int x = 0; x < matrix.length; x++) {
            for (int y= 0; y < matrix[x].length; y++) {
                if (selectable(x, y)) {
                    return true;
                }
            }
        }
        return false;
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
