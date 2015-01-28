package PegSolitaire;

/**
 * Created by dennis on 28/01/15.
 */
public class Vak {
    private Ball bal;
    boolean deadZone; // Vlag voor dode hoeken

    public void setBall(Ball bal) {
        this.bal = bal;
    }

    public Ball giveBall() {
        Ball out = this.bal;
        this.bal = null;
        return out;
    }
}
