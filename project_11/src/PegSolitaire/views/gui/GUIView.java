package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.exceptions.IllegalCoordinateException;
import PegSolitaire.exceptions.IllegalMoveException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dennis on 18/02/15.
 */
public class GUIView extends JFrame {
    private Game game;
    private FieldUI fieldUI;
    private JPanel gamePanel;
    private JPanel controlPanel;
    private JPanel scorePanel;

    public GUIView(String title) throws HeadlessException {
        super(title);
        game = new Game();

        initView();
        showFrame();
    }

    private void initView() {
        getContentPane().removeAll();
        initMenu();
        initComponents();
        layoutComponents();
        this.validate();
    }

    private void initComponents() {
        gamePanel = new JPanel(new BorderLayout());
        fieldUI = new FieldUI(game);
        controlPanel = new ControlPanel(game);
        scorePanel = new ScorePanel(game);
    }

    private void layoutComponents() {
        gamePanel.add(new FieldUI(game), BorderLayout.CENTER);

        getContentPane().add(gamePanel, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
        getContentPane().add(scorePanel, BorderLayout.NORTH);
    }

    private void initMenu() {
        this.setJMenuBar(new MenuBar(game));
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Fout", JOptionPane.ERROR_MESSAGE);
    }

    public void showHowItIsDone() {
        this.game = new Game();
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
            try {
                game.doMove(c[i++],c[i++],c[i++],c[i++]);
                System.out.println(game.printField());

                if (game.isEndgame()) {

                }
            } catch (IllegalMoveException | IllegalCoordinateException e) {
                showErrorMessage(e.getMessage());
            }
            repaint();
        }
    }

    private void showFrame() {
        this.setBounds(50, 50, 800, 800);
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.doLayout();
    }
}
