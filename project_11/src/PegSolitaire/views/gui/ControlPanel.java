package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by dennis on 10/03/15.
 */
public class ControlPanel extends JPanel {
    private final Game game;
    private JLabel undoLabel;
    private Font undoFont = loadFont().deriveFont(80f);
    private Color defaultArrowColor = new Color(156, 103, 0);
    private Color hoverArrowColor = new Color(252, 167, 0);
    private Color pressedArrowColor = new Color(255, 193, 71);
    private boolean isMouseOver = false;
    private boolean isMousePressed = false;

    public ControlPanel(Game g) {
        this.game = g;
        initComponents();
        addListeners();
    }

    private void initComponents() {
        undoLabel = new JLabel(new Character((char) 0xe802).toString());
        undoLabel.setFont(undoFont);
        this.add(undoLabel);
        this.setLayout(new FlowLayout());
    }

    private void addListeners() {
        undoLabel.addMouseListener(new MouseAdapter() {
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

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                isMousePressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                isMousePressed = false;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                game.undoMove();
                getTopLevelAncestor().repaint();
            }
        });
    }

    private Font loadFont() {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/PegSolitaire/files/fontello.ttf"));
        } catch (IOException | FontFormatException e) {
            GUIView guiView = (GUIView) SwingUtilities.getAncestorOfClass(GUIView.class, this);
            guiView.showErrorMessage(e.getMessage());
        }

        return font;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isMouseOver) {
            undoLabel.setForeground(hoverArrowColor);
            if (isMousePressed) {
                undoLabel.setForeground(pressedArrowColor);
            }
        } else {
            undoLabel.setForeground(defaultArrowColor);
        }
    }
}