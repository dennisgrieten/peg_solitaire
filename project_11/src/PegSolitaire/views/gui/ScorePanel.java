package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dennis on 10/03/15.
 */
public class ScorePanel extends JPanel {
    private final Game game;
    private JLabel movesLabel;
    private JLabel pegsLabel;
    private Font monospace = new Font("monospaced", Font.ITALIC, 30);

    public ScorePanel(Game g) {
        this.game = g;
        initComponents();
    }

    private void initComponents() {
        movesLabel = new JLabel("Zetten: " + game.getMoveCount());
        pegsLabel = new JLabel("Pegs: " + game.getCurrentPegCount());

        movesLabel.setFont(monospace);
        pegsLabel.setFont(monospace);

        this.add(movesLabel);
        this.add(pegsLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        movesLabel.setText("Zetten: " + game.getMoveCount());
        pegsLabel.setText("Pegs: " + game.getCurrentPegCount());
    }
}
