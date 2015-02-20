package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

/**
 * Created by dennis on 29/01/15.
 */
public class TestExceptions {
    public static void main(String[] args) {
        Game spel = new Game();

        System.out.println("### Exceptions Test ###");
        System.out.println("\n" + spel.printField());

        /* (5,3) -> (3,3) */
        try {
            spel.doMove(5, 3, 3, 3);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + spel.printField());

        /* (4,1) -> (4,3)*/
        try {
            spel.doMove(4, 1, 4, 3);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + spel.printField());

        /* (4,0) -> (4,2), spring over leeg veld*/
        System.out.print("Spring over leeg veld:\n" +
                        "(4,0) -> (4,2)\n\n"
        );
        try {
            spel.doMove(4, 0, 4, 2);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + spel.printField() + "\n");

        /* (5,2) -> (7,2), spring buiten matrix */
        System.out.print("Spring buiten matrix:\n" +
                        "(5,2) -> (7,2)\n\n"
        );
        try {
            spel.doMove(5, 2, 7, 2);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + spel.printField() + "\n");

        /* (3,0) -> (5,0), spring op dode zone */
        System.out.print("Spring op dode zone:\n" +
                        "(3,0) -> (5,0)\n\n"
        );

        try {
            spel.doMove(3, 0, 5, 0);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n" + spel.printField() + "\n");

        /* (5,0) -> (3,0), spring van dode zone */
        System.out.print("Spring van dode zone op vol vak:\n" +
                        "(5,0) -> (3,0)\n\n"
        );

        try {
            spel.doMove(3, 0, 5, 0);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n" + spel.printField() + "\n");

        /* (5,0) -> (3,0), spring van dode zone op leeg vak */
        System.out.print("Spring van dode zone op leeg vak:\n" +
                        "(5,1) -> (5,3)\n\n"
        );

        try {
            spel.doMove(3, 0, 5, 0);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n" + spel.printField() + "\n");

        /* (2,4) -> (4,2), spring diagonaal */
        System.out.print("Spring diagonaal:\n" +
                        "(2,4) -> (4,2)\n\n"
        );
        try {
            spel.doMove(2, 4, 4, 2);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + spel.printField() + "\n");

        /* (2,3) -> (5,3), spring over meerdere ballen */
        System.out.print("Spring over meerdere ballen:\n" +
                        "(2,3) -> (5,3)\n\n"
        );

        try {
            spel.doMove(2, 3, 5, 3);
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + spel.printField() + "\n");
    }
}

