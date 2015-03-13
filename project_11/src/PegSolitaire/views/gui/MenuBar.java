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
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem loadGame;
    private JMenuItem showExample;
    private JMenuItem exit;
    private JMenuItem fullscreen;
    private JMenuItem darkTheme;
    private JMenuItem undo;

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
        showExample = new JMenuItem("Toon Voorbeeld");
        exit = new JMenuItem("Exit");

        /* View */
        view = new JMenu("Beeld");
        fullscreen = new JMenuItem("Volledig Scherm");
        darkTheme = new JMenuItem("Donker Thema");

        /* Edit */
        edit = new JMenu("Bewerk");
        undo = new JMenuItem("Maak Zet Ongedaan");
    }

    private void layoutComponents() {
        /* Options */
        options.add(newGame);
        options.add(saveGame);
        options.add(loadGame);
        options.add(new JSeparator());
        options.add(exit);

        /* View */
        view.add(fullscreen);

        /* Edit */
        edit.add(undo);

        this.add(options);
        this.add(edit);
        this.add(view);
    }

    private void setAccelerators() {
        /* Options */
        saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));    // Save Game:   CTRL+S
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));     // New Game:    CTRL+N

        /* View */
        fullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));              // Fullscreen:  F11

        /* Edit */
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));        // Undo Move:   CTRL+Z
    }

    private void addListeners() {
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
                System.exit(0);
            }
        });
    }

    private void handleNewGame() {
        GUIView guiView = (GUIView) SwingUtilities.getAncestorOfClass(GUIView.class, this);
        guiView.restart();
    }
}
