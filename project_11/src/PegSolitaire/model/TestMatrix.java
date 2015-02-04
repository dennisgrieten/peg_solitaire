package PegSolitaire.model;

import java.util.Scanner;

/**
 * Created by dennis on 29/01/15.
 */
public class TestMatrix {
    public static void main(String[] args) {
        Matrix field = new Matrix();
        Scanner input = new Scanner(System.in);
        int x, x1;
        int y, y1;

        while (true) {
            System.out.println(field.toString());
            System.out.print("Verplaats een bal\nx: ");
            x = input.nextInt();
            System.out.print("y: ");
            y = input.nextInt();
            System.out.print("Naar\nx: ");
            x1 = input.nextInt();
            System.out.print("y: ");
            y1 = input.nextInt();
            field.moveBall(x, y, x1, y1);
            System.out.println("\n");
        }

    }
}
