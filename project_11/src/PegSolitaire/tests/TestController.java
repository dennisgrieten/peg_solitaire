package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

/**
 * Created by dennis on 29/01/15.
 */
public class TestController {
    public static void main(String[] args) {
        Game spel = new Game();


        System.out.println("### Controller Test ###");
        System.out.println(spel.printField());
        /* (5,3) -> (3,3) */
        System.out.print("Verplaats bal:\n" +
                        "(5,3) -> (3,3)\n\n"
        );

        try {
            spel.doMove(5, 3, 3, 3);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(spel.printField());

        /* (4,1) -> (4,3)*/
        System.out.print("Verplaats bal:\n" +
                        "(4,1) -> (4,3)\n\n"
        );
        try {
            spel.doMove(4, 1, 4, 3);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(spel.printField());
        try {
            spel.doMove(4, 1, 4, 3);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
        System.out.println(e.getMessage());
        }

        System.out.println(spel.printField());

        System.out.println("Maak zet ongedaan:");
        spel.undoMove();
        System.out.println(spel.printField());

        System.out.println("Maak zet nog eens ongedaan:");
        spel.undoMove();
        System.out.println(spel.printField());
    }
}
