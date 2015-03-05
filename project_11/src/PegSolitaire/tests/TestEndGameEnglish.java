package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

/**
 * Created by dennis on 18/02/15.
 */
public class TestEndGameEnglish {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Game game = new Game();
        int[] c = {
                1, 3, 3, 3,
                2, 5, 2, 3,
                0, 4, 2, 4,
                3, 4, 1, 4,
                5, 4, 3, 4,
                4, 6, 4, 4,
                4, 3, 4, 5,
                2, 6, 4, 6,
                4, 6, 4, 4,
                2, 2, 2, 4,
                2, 0, 2, 2,
                4, 1, 4, 3,
                4, 3, 4, 5,
                4, 5, 2, 5,
                2, 5, 2, 3,
                2, 3, 2, 1,
                0, 2, 0, 4,
                0, 4, 2, 4,
                2, 4, 4, 4,
                6, 2, 4, 2,
                3, 2, 5, 2,
                6, 4, 6, 2,
                6, 2, 4, 2,
                4, 0, 2, 0,
                2, 0, 2, 2,
                1, 2, 3, 2,
                3, 2, 5, 2,
                5, 2, 5, 4,
                5, 4, 3, 4,
                3, 4, 3, 2,
                3, 1, 3, 3,
        };

        int x = 0, y = 0, x1 = 0, y1 = 0;

        System.out.println("### Endgame Test ###");
        System.out.println(game.printField());
        System.out.print("Plot moves\n\n");

        for (int i = 0; i < c.length;) {
            try {
                game.doMove(c[i++],c[i++],c[i++],c[i++]);
                System.out.print(game.printField());
                System.out.printf("Moves: %d\n\n", game.getMoveCount());

                if (game.isEndgame()) {
                    System.out.println("                        #,\n" +
                            "                        ###\n" +
                            "                       ## ##\n" +
                            "                      ##  ##\n" +
                            "                       ####\n" +
                            "                         :\n" +
                            "                        #####\n" +
                            "                       ######\n" +
                            "                       ##  ##\n" +
                            "                       ##  ##\n" +
                            "                       ##  ##\n" +
                            "                       ##  ##########\n" +
                            "                       ##  #############\n" +
                            "                  #######  ###############\n" +
                            "              #############################\n" +
                            "        .###################################\n" +
                            "       #####################################;\n" +
                            "       ##                                 ##.\n" +
                            "       ##                                 ##\n" +
                            "       #####################################\n" +
                            "       ##                                 ##\n" +
                            "       ##                                 ##\n" +
                            "       ##                                 ###\n" +
                            "    #####                                 #####\n" +
                            "   ### ##################################### ###\n" +
                            "  ###  ##                                 ##  ###\n" +
                            "  ##   ## ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, ##   ##\n" +
                            "   ##  #####################################  ##\n" +
                            "    ##                                       ##\n" +
                            "     ####                                 ####\n" +
                            "       ######                         ######\n" +
                            "          ###############################");
                }
            } catch (IllegalMoveException | IllegalCoordinateException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("Runtime: %d ms", endTime - startTime);
    }
}
