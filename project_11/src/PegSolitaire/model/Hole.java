package PegSolitaire.model;

/**
 * Created by dennis on 28/01/15.
 */
public class Hole {
    private Ball ball;
    private Coordinate coordinate;
    private boolean deadZone;   // Vlag voor dode hoeken

    public Hole(Ball ball, Coordinate c) {
        this.ball = ball;
        this.coordinate = c;
        this.deadZone = false;
    }

    public Hole(Ball ball, boolean deadZone) {
        this.ball = ball;
        this.deadZone = deadZone;
    }

    public void setBall(Ball bal) {
        this.ball = bal;
    }

    public void setDeadZone(boolean deadZone){
        this.deadZone = deadZone;
    }

    public void clearBall() {
        this.ball = null;   // Wis pointer van bal in dit vak
    }

    // Overhandig de ball
    public Ball giveBall() {
        pushCoordinate();       // Zet Coordinaat van dit vak in geschiedenis van ball
        Ball out = this.ball;   // Tijdelijke extra pointer naar ball
        clearBall();            // Verwijder pointer naar de bal in dit vak
        return out;
    }

    // Coordinaat van dit vak plaatsen in bal geschiedenis
    public void pushCoordinate() {
        if (ball != null ) {
            ball.pushCoordinate(this.coordinate);
        }
    }

    @Override
    public String toString() {
        return (ball == null?"o":deadZone == true?" ":"·");
    }
}
