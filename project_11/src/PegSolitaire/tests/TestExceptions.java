package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.exceptions.IllegalCoordinateException;
import PegSolitaire.dom.exceptions.IllegalMoveException;

/**
 * Created by dennis on 29/01/15.
 */
public class TestExceptions {
    public static void main(String[] args) {
        Game game = new Game();
        int[] moves = {
                1, 3, 3, 3,     //   0 1 2 3 4 5 6
                2, 5, 2, 3,     // 0     ◉ ◉ ◉
                0, 4, 2, 4,     // 1     ◉ ◉ ◉
                3, 4, 1, 4,     // 2 ◉ ◉ ◉ ◉ ◉ ◉ ◉
                5, 4, 3, 4,     // 3 ◉ ○ ◉ ◉ ○ ◉ ◉
                4, 6, 4, 4,     // 4 ○ ◉ ○ ◉ ◉ ○ ◉
                4, 3, 4, 5,     // 5     ○ ◉ ○
                2, 6, 4, 6,     // 6     ○ ○ ○
                4, 6, 4, 4,     // Setup
                4, 4, 4, 4,     // 0 sprong
                5, 4, 5, 4,     // 0 sprong leeg vak
                4, 4, 4, 6,     // Spring over leeg vak
                3, 3, 3, 5,     // Spring op vol vak
                4, 4, 4, 5,     // Spring op naburig leeg vak
                4, 4, 3, 4,     // Spring op naburig vol vak
                4, 4, 6, 4,     // Spring over leeg vak in vol vak
                3, 2, 3, 6,     // Spring over meerdere vakken in leeg vak
                4, 4, 2, 6,     // Spring diagonaal
                4, 5, 2, 5,     // Leeg vak, over vol vak, in leeg vak
                4, 6, 2, 6,     // Leeg vak, over leeg vak, in leeg vak
                5, 3, 7, 3,     // Spring buiten matrix
                5, 3, 5, 1,     // Spring in dode zone
        };

        String[] tests = {
                "0 sprong",
                "0 sprong leeg vak",
                "Spring over leeg vak",
                "Spring op vol vak",
                "Spring op naburig leeg vak",
                "Spring op naburig vol vak",
                "Spring over leeg vak in vol vak",
                "Spring over meerdere vakken in leeg vak",
                "Spring diagonaal",
                "Leeg vak, over vol vak, in leeg vak",
                "Leeg vak, over leeg vak, in leeg vak",
                "Spring buiten matrix",
                "Spring in dode zone",
        };

        System.out.println("### Exceptions Test ###");
        int j = 0;

        for (int i = 0; i < 36;) {
            try {
                game.doMove(moves[i++],moves[i++],moves[i++],moves[i++]);
            } catch (IllegalCoordinateException | IllegalMoveException e) {
                System.out.printf("%-40s: %s", tests[j], e.getMessage());
                j++;
                System.out.print("\n");
            }
        }

        String begin = game.printField();

        for (int i = 36; i < moves.length;) {
            try {
                game.doMove(moves[i++],moves[i++],moves[i++],moves[i++]);
            } catch (IllegalCoordinateException | IllegalMoveException e) {
                System.out.printf("%-40s: %s", tests[j], e.getMessage());
                j++;
                System.out.print("\n");
            }
        }

        System.out.println("\n" + game.printField());
        System.out.println(begin);
    }
}

