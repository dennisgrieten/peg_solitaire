package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.Field;
import PegSolitaire.dom.Hole;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dennis on 5/03/15.
 */
public class FieldUI extends JPanel {
    private final Game game;
    private Field field;
    private Hole[][] matrix;

    public FieldUI(Game g) {
        this.game = g;
        this.field = game.getField();
        this.matrix = field.getMatrix();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(game.getDimensionX(), game.getDimensionY()));

        for (int i = 0; i < field.getDimensionX(); i++) {
            for (int j = 0; j < field.getDimensionY(); j++) {
                this.add(new HoleUI(matrix[i][j], game));
            }
        }
    }
}