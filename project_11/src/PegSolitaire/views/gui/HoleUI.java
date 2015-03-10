package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.Hole;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by dennis on 5/03/15.
 */
public class HoleUI extends JPanel{
    private Game game;
    private Hole hole;
    private JLabel peg;
    private Font monospace = new Font("monospaced", Font.PLAIN, 20);

    public HoleUI(Hole h, Game g) {
        this.game = g;
        this.hole = h;
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        peg = new JLabel(hole.toString());
        peg.setFont(monospace);
        peg.setForeground(Color.black);
    }

    private void layoutComponents() {
        this.setLayout(new BorderLayout());
        peg.setHorizontalAlignment(SwingConstants.CENTER);
        peg.setVerticalAlignment(SwingConstants.CENTER);
        this.add(peg, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        adaptFontSize(peg);
    }

    private void adaptFontSize(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }
}
