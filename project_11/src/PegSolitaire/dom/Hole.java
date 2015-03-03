package PegSolitaire.dom;

/**
 * Created by dennis on 28/01/15.
 */
public class Hole {
    private Field parent;
    private Peg peg;
    private Coordinate coordinate;
    private boolean deadZone;   // Vlag voor dode Vakken
    private boolean jumped;     // Vlag voor

    public Hole(Peg p, Coordinate c, Field f) {
        this.peg = p;
        this.coordinate = c;
        this.deadZone = false;
        parent = f;
    }

    public Hole(Peg p, boolean d) {
        setPeg(p);
        setDeadZone(d);
    }

    public boolean hasPeg() {
        if (peg == null) {
            return false;
        }

        return true;
    }

    public boolean isDeadZone() {
        return this.deadZone;
    }

    public void setDeadZone(boolean d){
        this.deadZone = d;
    }

    public void setJumped(boolean j) {
        this.jumped = j;
    }

    public void setPeg(Peg p) {
        this.peg = p;
        this.jumped = false;
    }

    public void clearPeg() {
        this.peg = null;        // Wis pointer van bal in dit vak
    }

    /* Overhandig de ball */
    public Peg givePeg() {
        Peg out = this.peg;     // Tijdelijke extra pointer naar ball
        clearPeg();             // Verwijder pointer naar de bal in dit vak
        return out;
    }

    public Peg givePeg(boolean toStack) {
        pushCoordinate();       // Zet Coordinaat van dit vak in geschiedenis van ball
        return this.givePeg();
    }

    /* Coordinaat van dit vak plaatsen in de bal geschiedenis */
    public void pushCoordinate() {
        if (peg != null ) {
            peg.pushHistory(this.coordinate);
        }
    }

    @Override
    public String toString() {
        return (deadZone?" ": hasPeg() ?"◉":jumped?"◎":"○");      //●
    }
}
