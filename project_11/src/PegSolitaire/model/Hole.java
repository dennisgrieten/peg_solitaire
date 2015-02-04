package PegSolitaire.model;

/**
 * Created by dennis on 28/01/15.
 */
public class Hole {
    private Ball ball;
    private Coordinate c;
    private boolean deadZone;   // Vlag voor dode hoeken

    public Hole(Ball ball, Coordinate c) {
        this.ball = ball;
        this.c = c;
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
        Ball out = this.ball;
        clearBall();
        return out;
    }

    @Override
    public String toString() {
        return (ball == null?"o":deadZone == true?" ":"·");
    }
}
