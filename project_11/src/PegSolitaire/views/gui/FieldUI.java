package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.Field;
import PegSolitaire.dom.Hole;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dennis on 5/03/15.
 */
public class FieldUI extends JPanel {
    private final Game game;
    private Field field;
    private Hole[][] matrix;
    private ArrayList<HoleUI> holelist;

    public FieldUI(Game g) {
        this.game = g;
        this.field = game.getField();
        this.matrix = field.getMatrix();
        holelist = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(game.getDimensionX(), game.getDimensionY()));

        for (int i = 0; i < field.getDimensionX(); i++) {
            for (int j = 0; j < field.getDimensionY(); j++) {
                HoleUI hole = new HoleUI(matrix[i][j], game);
                this.add(hole);
                holelist.add(hole);
            }
        }
    }

    public void showHowItIsDone() throws IllegalMoveException, IllegalCoordinateException {
        GUIView guiView = (GUIView) SwingUtilities.getAncestorOfClass(GUIView.class, this);

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

        for (int i = 0; i < c.length;) {
            game.doMove(c[i++],c[i++],c[i++],c[i++]);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}