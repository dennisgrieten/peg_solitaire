package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;
import PegSolitaire.dom.Hole;

import javax.swing.*;

/**
 * Created by dennis on 5/03/15.
 */
public class HoleUI extends JPanel{
    private Game game;
    private Hole hole;

    public HoleUI(Hole h, Game g) {
        this.game = g;
        this.hole = h;
    }
}
