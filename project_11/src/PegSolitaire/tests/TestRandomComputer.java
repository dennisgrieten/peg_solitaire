package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.exceptions.IllegalCoordinateException;
import PegSolitaire.dom.exceptions.IllegalMoveException;

import java.util.Random;

/**
 * Created by dennis on 20/02/15.
 */
public class TestRandomComputer {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Game game = new Game();
        Random random = new Random();
        int bound = 7;
        int turn = 0;

        int x = 0, y = 0, x1 = 0, y1 = 0;
        boolean exit = false;

        System.out.println("### Game Over Test ###");
        System.out.println(game.printField());
        System.out.print("Plot moves\n\n");
        do {
            x = random.nextInt(bound);
            y = random.nextInt(bound);
            x1 = random.nextInt(bound);
            y1 = random.nextInt(bound);
            turn++;
            try {
                game.doMove(x, y, x1, y1);
                System.out.printf("Turn: %d\nMoves: %d\n", turn, game.getMoveCount());
                System.out.println(game.printField());

                if (game.isGameOver()) {
                    System.out.println("Game over!");
                    exit = true;
                }

                if (game.isEndgame()) {
                    System.out.println("Game Won!");
                    exit = true;
                }

            } catch (IllegalMoveException | IllegalCoordinateException e) {
            }
        } while (!exit);
        long endTime = System.currentTimeMillis();
        System.out.printf("Runtime: %d ms", endTime - startTime);
    }
}
