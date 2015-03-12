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

        /* Font Smoothing */
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

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
        scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));     // Flowlayout (int align, int hgap, int vgap)
        gamePanel.add(scorePanel, BorderLayout.NORTH);
        gamePanel.add(new FieldUI(game), BorderLayout.CENTER);
        gamePanel.add(controlPanel, BorderLayout.SOUTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
    }

    private void initMenu() {
        this.setJMenuBar(new MenuBar(game));
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Fout", JOptionPane.ERROR_MESSAGE);
    }

    public void showEndGameDialog() {
        JOptionPane.showMessageDialog(this, "Proficiat, je hebt het spel voltooid!", "Uitgespeeld!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showGameOverDialog() {
        JOptionPane.showMessageDialog(this, "Er zijn geen zetten meer mogelijk", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showHowItIsDone() {
        try {
            fieldUI.showHowItIsDone();
        } catch (IllegalCoordinateException | IllegalMoveException e) {
            showErrorMessage(e.getMessage());
        }
        showEndGameDialog();
    }

    private void showFrame() {
        this.setBounds(50, 50, 900, 900);
        this.setPreferredSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.doLayout();
    }
}
