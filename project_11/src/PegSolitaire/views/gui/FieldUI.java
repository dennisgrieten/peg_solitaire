package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.field.Field;
import PegSolitaire.dom.field.Hole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

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
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(new EmptyBorder(10,10,10,10));

        for (int i = 0; i < field.getDimensionX(); i++) {
            for (int j = 0; j < field.getDimensionY(); j++) {
                HoleUI hole = new HoleUI(matrix[i][j], game);
                gbc.gridx = i;
                gbc.gridy = j;
                this.add(hole, gbc);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}