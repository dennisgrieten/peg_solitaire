package PegSolitaire.model;

import PegSolitaire.controller.Controller;

import java.util.Scanner;

/**
 * Created by dennis on 29/01/15.
 */
public class TestMatrix {
    public static void main(String[] args) {
        Controller spel = new Controller();
        Scanner input = new Scanner(System.in);
        int x, x1;
        int y, y1;

        while (true) {
            System.out.println(spel.toString());
            System.out.print("Verplaats een bal\nx: ");
            x = input.nextInt();
            System.out.print("y: ");
            y = input.nextInt();
            System.out.print("Naar\nx: ");
            x1 = input.nextInt();
            System.out.print("y: ");
            y1 = input.nextInt();
            spel.doMove(x, y, x1, y1);
            System.out.println("\n");
        }

    }
}
