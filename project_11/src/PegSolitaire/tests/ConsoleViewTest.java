package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.views.console.ConsoleView;

/**
 * Created by dennis on 18/02/15.
 */
public class ConsoleViewTest {
    public static void main(String[] args) {
        Game pegSolitaire = new Game();
        ConsoleView view = new ConsoleView(pegSolitaire);
        view.init();
    }
}
