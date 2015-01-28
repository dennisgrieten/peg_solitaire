package PegSolitaire;

/**
 * Created by dennis on 28/01/15.
 */
public class Vak {
    private Ball bal;
    boolean deadZone = false; // Vlag voor dode hoeken

    public Vak(Ball bal) {
        this.bal = bal;
    }

    public Vak(Ball bal, boolean deadZone) {
        this.bal = bal;
        this.deadZone = deadZone;
    }

    public void setBall(Ball bal) {
        this.bal = bal;
    }

    // Overhandig de bal
    public Ball giveBall() {
        Ball out = this.bal;
        this.bal = null;    // Wis pointer van bal in dit vak
        return out;
    }
}