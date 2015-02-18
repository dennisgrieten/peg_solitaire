package PegSolitaire.tests;

import PegSolitaire.controller.Controller;

/**
 * Created by dennis on 29/01/15.
 */
public class TestExceptions {
    public static void main(String[] args) {
        Controller spel = new Controller();

        System.out.println("\n" + spel.toString());
        /* (5,3) -> (3,3) */
        spel.doMove(5, 3, 3, 3);
        System.out.println("\n" + spel.toString());

        /* (4,1) -> (4,3)*/
        spel.doMove(4, 1, 4, 3);
        System.out.println("\n" + spel.toString());

        /* (4,0) -> (4,2), spring over leeg veld*/
        System.out.print("Spring over leeg veld:\n" +
                        "(4,0) -> (4,2)\n\n"
        );
        spel.doMove(4, 0, 4, 2);
        System.out.println("\n" + spel.toString() + "\n");

        /* (5,2) -> (7,2), spring buiten matrix */
        System.out.print("Spring buiten matrix:\n" +
                        "(5,2) -> (7,2)\n\n"
        );
        spel.doMove(5, 2, 7, 2);
        System.out.println("\n" + spel.toString() + "\n");

        /* (3,0) -> (5,0), spring op dode zone */
        System.out.print("Spring op dode zone:\n" +
                        "(3,0) -> (5,0)\n\n"
        );
        spel.doMove(3, 0, 5, 0);
        System.out.println("\n" + spel.toString() + "\n");

        /* (2,4) -> (4,2), spring diagonaal */
        System.out.print("Spring diagonaal:\n" +
                        "(2,4) -> (4,2)\n\n"
        );
        spel.doMove(2, 4, 4, 2);
        System.out.println("\n" + spel.toString() + "\n");

        /* (2,3) -> (5,3), spring over meerdere ballen */
        System.out.print("Spring over meerdere ballen:\n" +
                        "(2,3) -> (5,3)\n\n"
        );
        spel.doMove(2, 3, 5, 3);
        System.out.println("\n" + spel.toString() + "\n");
    }
}

