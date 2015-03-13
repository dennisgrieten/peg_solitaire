package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by dennis on 2/03/15.
 */
public class MenuBar extends JMenuBar {
    private final Game game;
    GUIView guiView = (GUIView) SwingUtilities.getAncestorOfClass(GUIView.class, this);
    private JMenu options;
    private JMenu view;
    private JMenu edit;
    private JMenu help;
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem loadGame;
    private JMenuItem exit;
    private JMenuItem showHighScores;
    private JMenuItem undo;
    private JMenuItem showHelp;

    public MenuBar(Game g) {
        super();
        this.game = g;
        initComponents();
        layoutComponents();
        setAccelerators();
        addListeners();
    }

    private void initComponents() {
        /* Options */
        options = new JMenu("Opties");
        newGame = new JMenuItem("Nieuw Spel");
        saveGame = new JMenuItem("Spel Opslaan");
        saveGame.setEnabled(false);                 // Enable wanneer nieuw spel gestart is
        loadGame = new JMenuItem("Spel Laden");
        exit = new JMenuItem("Exit");

        /* View */
        view = new JMenu("Beeld");
        showHighScores = new JMenuItem("Toon HighScores");

        /* Edit */
        edit = new JMenu("Bewerk");
        undo = new JMenuItem("Maak Zet Ongedaan");

        /* Help */
        help = new JMenu("Help");
        showHelp = new JMenuItem("Toon Hulp");
    }

    private void layoutComponents() {
        /* Options */
        options.add(newGame);
        options.add(saveGame);
        options.add(loadGame);
        options.add(new JSeparator());
        options.add(exit);

        /* View */
        view.add(showHighScores);

        /* Edit */
        edit.add(undo);

        /* Help */
        help.add(showHelp);

        this.add(options);
        this.add(edit);
        this.add(view);
        this.add(help);
    }

    private void setAccelerators() {
        /* Options */
        saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));    // Save Game:   CTRL+S
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));     // New Game:    CTRL+N

        /* Edit */
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));        // Undo Move:   CTRL+Z
    }

    private void addListeners() {
        showHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleShowHelp();
            }
        });
        showHighScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleShowHighScores();
            }
        });
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNewGame();
            }
        });
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.undoMove();
                getTopLevelAncestor().repaint();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExit();
            }
        });
    }

    private void handleShowHelp() {
        JOptionPane.showMessageDialog(guiView, game.toString(), "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleShowHighScores() {
        JOptionPane.showMessageDialog(null, game.getTop5(), "Top 5 HighScores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleExit() {
        try {
            game.saveScores();
        } catch (Exception e){
            guiView.showErrorMessage(e.getMessage());
        }

        System.exit(0);
    }

    private void handleNewGame() {
        GUIView guiView = (GUIView) SwingUtilities.getAncestorOfClass(GUIView.class, this);
        guiView.restart();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        saveGame.setEnabled(game.getMoveCount() > 0 ? true : false);
    }
}
