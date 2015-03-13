package PegSolitaire.views.console;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.exceptions.IllegalCoordinateException;
import PegSolitaire.dom.exceptions.IllegalMoveException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by dennis on 18/02/15.
 */
public class ConsoleView {
    private Game game;
    private Scanner keyboard = new Scanner(System.in);
    private HashMap<Integer, String> menuItems = new HashMap<Integer, String>();

    public ConsoleView(Game g) {
        this.game = g;
        initMenuItems();
    }

    public void init() {
        System.out.println(game);
        showMenu();
    }

    private void initMenuItems() {
        menuItems.put(1, "Start new game ");
        menuItems.put(0, "Exit");
    }

    public void showMenu() {
        int choice;

        do {
            choice = menuChoice();

            switch (choice) {
                case 1:                 // New game
                    startGame(true);
                    break;
                case 2:                 // Resume game
                    startGame(true);
                    break;
                case 3:                 // Undo move
                    game.undoMove();
                    startGame(true);
                    break;
                case 4:                 // Show highscores
                    System.out.println(game.getTop5());
                    break;
                default:                // Exit
                    System.out.println("Good bye!");
                    choice = 0;
            }

        } while (choice != 0);
    }

    private int menuChoice() {
        boolean exit = false;
        int choice = 0;

        do {
            System.out.println("Choose your option:");

            if (game.getMoveCount() == 0) {
                menuItems.remove(2);
                menuItems.remove(3);
            } else {
                menuItems.put(2, "Resume game");
                menuItems.put(3, "Undo move");
                menuItems.put(4, "Show highscores");
            }

            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("%d. %s\n", i, menuItems.get(i));
            }

            try {
                choice = keyboard.nextInt();
                if (choice > -1 && choice <= menuItems.size()) {
                    exit = true;
                } else {
                    System.out.printf("Make a choice between %d..%d\n\n", 0, menuItems.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid choice!\n");
                keyboard.nextLine();
            }

        } while (!exit);

        keyboard.nextLine();
        return choice;
    }

    private void startGame(boolean start) {
        if (game == null) {
            game = new Game();
        }

        boolean exit = false;
        boolean valid = false;
        int x = 0, y = 0, x1 = 0, y1 = 0;
        System.out.println(game.printField());

        do {
            System.out.print("Plot a move\n");

            try {
                System.out.print("x: ");
                x = keyboard.nextInt();
                System.out.print("y: ");
                y = keyboard.nextInt();
                System.out.print("move to\n");
                System.out.print("x: ");
                x1 = keyboard.nextInt();
                System.out.print("y: ");
                y1 = keyboard.nextInt();
                System.out.print("\n");
            } catch (InputMismatchException e) {
                keyboard.nextLine();
                break;
            }

            try {
                game.doMove(x, y, x1, y1);

                if (game.isGameOver()) {
                    System.out.println("Game over!");
                    menuItems.remove(2);
                    exit = true;
                }

                if (game.isEndgame()) {
                    System.out.println("You've unlocked the secret to the universe!\nWell done!");
                    exit = true;
                }
            } catch (IllegalMoveException | IllegalCoordinateException e) {
                System.out.println(e.getMessage() + "\n");
            }

            System.out.println(game.printField());
            System.out.printf("Moves: %d\n", game.getMoveCount());
        } while (!exit);
    }
}
