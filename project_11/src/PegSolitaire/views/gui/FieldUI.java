package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.Field;
import PegSolitaire.dom.Hole;

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
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(new EmptyBorder(20,20,20,20));

        for (int i = 0; i < field.getDimensionX(); i++) {
            for (int j = 0; j < field.getDimensionY(); j++) {
                HoleUI hole = new HoleUI(matrix[i][j], game);
                this.add(hole);
                holelist.add(hole);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}