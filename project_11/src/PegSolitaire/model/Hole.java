package PegSolitaire.model;

/**
 * Created by dennis on 28/01/15.
 */
public class Hole {
    private Field parent;
    private Ball ball;
    private Coordinate coordinate;
    private boolean deadZone;   // Vlag voor dode Vakken

    public Hole(Ball ball, Coordinate c, Field f) {
        this.ball = ball;
        this.coordinate = c;
        this.deadZone = false;
        parent = f;
    }

    public Hole(Ball ball, boolean deadZone) {
        this.ball = ball;
        this.deadZone = deadZone;
    }

    public boolean hasBall() {
        if (ball == null) {
            return false;
        }

        return true;
    }

    public boolean isDeadZone() {
        return this.deadZone;
    }

    public void setDeadZone(boolean deadZone){
        this.deadZone = deadZone;
    }

    public void setBall(Ball bal) {
        this.ball = bal;
    }

    public void clearBall() {
        this.ball = null;       // Wis pointer van bal in dit vak
    }

    /* Overhandig de ball */
    public Ball giveBall() {
        Ball out = this.ball;   // Tijdelijke extra pointer naar ball
        clearBall();            // Verwijder pointer naar de bal in dit vak
        return out;
    }

    public Ball giveBall(boolean toStack) {
        pushCoordinate();       // Zet Coordinaat van dit vak in geschiedenis van ball
        return this.giveBall();
    }

    /* Coordinaat van dit vak plaatsen in de bal geschiedenis */
    public void pushCoordinate() {
        if (ball != null ) {
            ball.pushHistory(this.coordinate);
        }
    }

    @Override
    public String toString() {
        return (ball == null?"o":deadZone == true?" ":"·");
    }
}
