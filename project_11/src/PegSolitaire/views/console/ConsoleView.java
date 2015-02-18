package PegSolitaire.views.console;

import PegSolitaire.controller.Game;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by dennis on 18/02/15.
 */
public class ConsoleView {
    private Game game;
    private boolean quit = false;
    private Scanner kb = new Scanner(System.in);
    private HashMap<Integer, String> menuItems = new HashMap<Integer, String>();


    public ConsoleView(Game c) {
        this.game = c;
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
                case 1:
                    startGame(false);
                    break;
                case 2:
                    startGame(true);
                    break;
                default:
                    System.out.println("Goodby !");
                    choice = 0;
            }

        } while (choice != 0);
    }

    private int menuChoice() {
        boolean exit = false;
        int choice = 0;

        do {
            System.out.println("Choose your option:");

            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("%d. %s\n", i, menuItems.get(i));
            }

            try {
                choice = kb.nextInt();
                if (choice > -1 && choice <= menuItems.size()) {
                    exit = true;
                } else {
                    System.out.printf("Make a choice between %d..%d\n\n", 0, menuItems.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid choice!\n");
                kb.nextLine();
            }

        } while (!exit);

        kb.nextLine();
        return choice;
    }

    private void startGame(boolean resume) {
        if (!resume) {
            game = new Game();
            menuItems.put(2, "Resume game");
            System.out.println("##############################");
        }

        boolean exit = false;
        boolean valid = false;
        int x = 0, y = 0, x1 = 0, y1 = 0;

        do {
            System.out.println(game.printField());
            System.out.println("Plot a move");

            try {
                System.out.print("x: ");
                x = kb.nextInt();
                System.out.print("y: ");
                y = kb.nextInt();
                System.out.println("move to");
                System.out.print("x: ");
                x1 = kb.nextInt();
                System.out.print("y: ");
                y1 = kb.nextInt();
                System.out.println();
                valid = true;
            } catch (InputMismatchException e) {
                break;
            }

            if (valid) {
                try {
                    game.doMove(x, y, x1, y1);

                    if (game.isEndgame()) {
                        System.out.println("You've unlocked the secret to the universe!\nWell done!");
                        exit = true;
                    }
                } catch (IllegalMoveException | IllegalCoordinateException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            }

        } while (!exit);
    }
}
