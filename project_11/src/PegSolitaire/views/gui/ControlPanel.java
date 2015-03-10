package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

import javax.swing.*;

/**
 * Created by dennis on 10/03/15.
 */
public class ControlPanel extends JPanel{
    private Game game;

    public ControlPanel(Game g) {
        this.game = g;
    }
}
