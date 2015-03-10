package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.Hole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by dennis on 5/03/15.
 */
public class HoleUI extends JPanel{
    private Game game;
    private Hole hole;
    private JLabel holeLabel;
    private Color startColor = new Color(2, 141, 136);
    private Color hoverColor = new Color(63, 171, 167);
    private Color selectColor = new Color(232,105, 3);
    private Color jumpedColor = new Color(184, 0, 5);
    private Font monospace = new Font("monospaced", Font.PLAIN, 80);
    private boolean selected = false;
    private boolean isMouseOver = false;

    public HoleUI(Hole h, Game g) {
        this.game = g;
        this.hole = h;
        initComponents();
        layoutComponents();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!selected && hole.isSelectable()) {
                    holeLabel.setForeground(selectColor);
                    selected = true;
                } else {
                    holeLabel.setForeground(hoverColor);
                    selected = false;
                }
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                if (!selected) {
                    isMouseOver = true;
                    holeLabel.setForeground(hoverColor);
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (!selected) {
                    isMouseOver = false;
                    holeLabel.setForeground(startColor);
                    repaint();
                }
            }
        });
    }

    private void initComponents() {
        holeLabel = new JLabel(hole.toString());
        holeLabel.setFont(monospace);
        holeLabel.setForeground(startColor);
    }

    private void layoutComponents() {
        this.setLayout(new BorderLayout());
        holeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        holeLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(holeLabel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isMouseOver)
        super.paintComponent(g);
        // adaptFontSize(holeLabel);
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
