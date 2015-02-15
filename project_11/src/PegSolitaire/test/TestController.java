package PegSolitaire.test;

import PegSolitaire.controller.Controller;

/**
 * Created by dennis on 29/01/15.
 */
public class TestController {
    public static void main(String[] args) {
        Controller spel = new Controller();

        /* (5,3) -> (3,3) */
        System.out.println(spel.toString());
        System.out.print("Verplaats bal\n" +
                        "x: 5\ny: 3\n->\n" +
                        "x: 3\ny: 3\n\n"
        );
        spel.doMove(5, 3, 3, 3);
        System.out.println(spel.toString());

        /* (4,1) -> (4,3)*/
        System.out.print("Verplaats bal\n" +
                        "x: 4\ny: 1\n->\n" +
                        "x: 4\ny: 3\n\n"
        );
        spel.doMove(4, 1, 4, 3);
        System.out.println(spel.toString());

        /* (4,0) -> (4,2), Illegaal, spring over leeg veld */
        System.out.print("Verplaats bal\n" +
                        "x: 4\ny: 1\n->\n" +
                        "x: 4\ny: 3\n\n"
        );
        spel.doMove(4, 1, 4, 3);
        System.out.println(spel.toString());

        System.out.println("Maak zet ongedaan");
        spel.undoMove();
        System.out.println(spel.toString());

        System.out.println("Maak zet nog eens ongedaan");
        spel.undoMove();
        System.out.println(spel.toString());
    }
}
