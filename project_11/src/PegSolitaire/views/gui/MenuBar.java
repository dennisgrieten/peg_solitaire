package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by dennis on 2/03/15.
 */
public class MenuBar extends JMenuBar {
    private final Game game;
    private JMenu options;
    private JMenu view;
    private JMenu edit;
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem loadGame;
    private JMenuItem exit;
    private JMenuItem fullscreen;
    private JMenuItem darkTheme;
    private JMenuItem undo;

    public MenuBar(Game g) {
        super();
        this.game = g;
        initComponents();
        setAccelerators();
        addListeners();
    }

    private void initComponents() {
        /* Options */
        JMenu options = new JMenu("Opties");
        newGame = new JMenuItem("Nieuw Spel");
        saveGame = new JMenuItem("Spel Opslaan");
        saveGame.setEnabled(false);                 // Enable wanneer nieuw spel gestart is
        loadGame = new JMenuItem("Spel Laden");
        exit = new JMenuItem("Exit");

        /* View */
        JMenu view = new JMenu("Beeld");
        fullscreen = new JMenuItem("Volledig Scherm");
        darkTheme = new JMenuItem("Donker Thema");

        /* Edit */
        JMenu edit = new JMenu("Bewerk");
        undo = new JMenuItem("Maak Zet Ongedaan");
    }

    private void setAccelerators() {
        /* Options */
        saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));    // Save Game: CTRL+S

        /* View */
        fullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));              // Fullscreen: F11
        darkTheme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));   // Dark Theme: CTRL+D

        /* Edit */
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK));        // Undo Move: CTRL+Z
    }

    private void addListeners() {

    }
}
