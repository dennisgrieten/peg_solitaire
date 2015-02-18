package PegSolitaire.tests;

import PegSolitaire.controller.Game;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by dennis on 18/02/15.
 */
public class TestEndGame {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner kb = new Scanner(System.in);
        int x = 0, y = 0, x1 = 0, y1 = 0;
        boolean exit = false;

        do {
            System.out.println("### Endgame Test ###");
            System.out.println(game.printField());
            System.out.print("Plot a moves\n\n");

            try {
                game.doMove(1, 3, 3, 3);
                game.doMove(2, 5, 2, 3);
                game.doMove(0, 4, 2, 4);
                game.doMove(3, 4, 1, 4);
                game.doMove(5, 4, 3, 4);
                game.doMove(4, 6, 4, 4);
                game.doMove(4, 3, 4, 5);
                game.doMove(2, 6, 4, 6);
                game.doMove(4, 6, 4, 4);
                game.doMove(2, 2, 2, 4);
                game.doMove(2, 0, 2, 2);
                game.doMove(4, 1, 4, 3);
                game.doMove(4, 3, 4, 5);
                game.doMove(4, 5, 2, 5);
                game.doMove(2, 5, 2, 3);
                game.doMove(2, 3, 2, 1);
                game.doMove(0, 2, 0, 4);
                game.doMove(0, 4, 2, 4);
                game.doMove(2, 4, 4, 4);
                game.doMove(6, 2, 4, 2);
                game.doMove(3, 2, 5, 2);
                game.doMove(6, 4, 6, 2);
                game.doMove(6, 2, 4, 2);
                game.doMove(4, 0, 2, 0);
                game.doMove(2, 0, 2, 2);
                game.doMove(1, 2, 3, 2);
                game.doMove(3, 2, 5, 2);
                game.doMove(5, 2, 5, 4);
                game.doMove(5, 4, 3, 4);
                game.doMove(3, 4, 3, 2);
                game.doMove(3, 1, 3, 3);

                System.out.println(game.printField());

                if (game.isEndgame()) {
                    System.out.println("You've unlocked the secret to the universe!\nWell done!");
                    exit = true;
                }

            } catch (IllegalMoveException | IllegalCoordinateException e) {
                System.out.println(e.getMessage() + "\n");
            }

        } while (!exit);
    }
}
