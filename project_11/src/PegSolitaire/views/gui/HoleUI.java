package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.field.Hole;
import PegSolitaire.dom.exceptions.IllegalCoordinateException;
import PegSolitaire.dom.exceptions.IllegalMoveException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by dennis on 5/03/15.
 */
public class HoleUI extends JPanel {
    private static HoleUI selectedPeg = null;
    private final Game game;
    private Hole hole;
    private JLabel holeLabel;
    private Color startColor = new Color(2, 141, 136);
    private Color hoverColor = new Color(63, 171, 167);
    private Color selectColor = new Color(232,105, 3);
    private Color jumpedColor = new Color(184, 0, 5);
    private Color jumpedHoverColor = new Color(143, 0, 4);
    private Font monospace = new Font("monospaced", Font.PLAIN, 100);
    private boolean selected = false;
    private boolean isMouseOver = false;

    public HoleUI(Hole h, Game g) {
        this.game = g;
        this.hole = h;
        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        holeLabel = new JLabel(hole.toString());
        holeLabel.setFont(monospace);
        holeLabel.setForeground(startColor);
        this.setBackground(new Color(0,0,0,0));
    }

    private void layoutComponents() {
        this.setLayout(new BorderLayout());
        holeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        holeLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(holeLabel, BorderLayout.CENTER);
    }

    private void adaptFontSize(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();
        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

        double widthRatio = (double)componentWidth / (double)stringWidth;           // Find out how much the font can grow in width.
        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();
        int fontSizeToUse = Math.min(newFontSize, componentHeight);                 // Pick a new font size so it will not be larger than the height of label.

        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));    // Set the label's font size to the newly determined size.
    }

    protected Hole getHole() {
        return this.hole;
    }

    protected void setSelected(boolean s) {
        this.selected = s;
    }

    private void addListeners() {
        /* ComponentListener */
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                adaptFontSize(holeLabel);
            }
        });

        /* MouseListener */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                handleMouseClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                isMouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                isMouseOver = false;
                repaint();
            }
        });
    }

    private void handleMouseClick() {
        GUIView guiView = (GUIView) SwingUtilities.getAncestorOfClass(GUIView.class, this);

        if (selected) {
            selectedPeg = null;
            this.setSelected(false);
        } else if (selectedPeg != null) {
            int x = selectedPeg.getHole().x();
            int y = selectedPeg.getHole().y();
            if (game.isLegalMove(x, y, hole.x(), hole.y())) {
                try {
                    game.doMove(x, y, hole.x(), hole.y());

                    if (game.isEndgame()) {
                        guiView.repaint();
                        guiView.showEndGameDialog();
                    } else if (game.isGameOver()) {
                        guiView.repaint();
                        guiView.showGameOverDialog();
                    }

                } catch (IllegalCoordinateException | IllegalMoveException e) {
                    guiView.showErrorMessage(e.getMessage());
                } finally {
                    if (hole.isSelectable()) {
                        selectedPeg.setSelected(false);
                        selectedPeg = this;
                        this.setSelected(true);
                    } else {
                        selectedPeg.setSelected(false);
                    }
                }
            } else if (hole.isSelectable()) {
                selectedPeg.setSelected(false);
                selected = true;
                selectedPeg = this;
            }
        } else if (hole.isSelectable()) {
            selectedPeg = this;
            this.setSelected(true);
        }
        guiView.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        holeLabel.setText(hole.toString());

        if (selected) {
            holeLabel.setForeground(selectColor);
        } else if (isMouseOver) {
            if (hole.isJumped()) {
                holeLabel.setForeground(jumpedHoverColor);
            } else {
                holeLabel.setForeground(hoverColor);
            }
        } else if (hole.isJumped()) {
            holeLabel.setForeground(jumpedColor);
        } else {
            holeLabel.setForeground(startColor);
        }
    }
}
