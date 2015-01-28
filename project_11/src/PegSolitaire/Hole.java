package PegSolitaire;

/**
 * Created by dennis on 28/01/15.
 */
public class Hole {
    private Ball ball;
    boolean deadZone = false; // Vlag voor dode hoeken

    public Hole(Ball ball) {
        this.ball = ball;
    }

    public Hole(Ball ball, boolean deadZone) {
        this.ball = ball;
        this.deadZone = deadZone;
    }

    public void setBall(Ball bal) {
        this.ball = bal;
    }

    // Overhandig de ball
    public Ball giveBall() {
        Ball out = this.ball;
        this.ball = null;    // Wis pointer van bal in dit vak
        return out;
    }
}
