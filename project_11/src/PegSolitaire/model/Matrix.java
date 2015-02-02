package PegSolitaire.model;

/**
 * Created by dennis on 28/01/15.
 */
public class Matrix {
    Hole[][] matrix = new Hole[7][7];
    byte[] deadZoneMap = new byte[]{0, 1, 5, 6};

    public Matrix() {
        initField();
    }

    private void initField() {
        for (byte i = 0; i < matrix.length; i++) {
            for (byte j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Hole(new Ball());
            }
        }

        for (byte i : deadZoneMap) {
            for (byte j : deadZoneMap) {
                matrix[i][j].setDeadZone(true);
            }
        }

        matrix[3][3].clearBall();   // Delete middelste bal
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output.append(matrix[i][j].toString()).append(" ");
            }
            output.append("\n");
        }

        return output.toString();
    }
}
