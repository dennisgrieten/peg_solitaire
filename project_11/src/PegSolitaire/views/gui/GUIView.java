package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

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
        // javathis.validate();
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

    private void showFrame() {
        this.setBounds(50, 50, 800, 800);
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.doLayout();
    }
}
