package PegSolitaire.views.gui;

import PegSolitaire.controller.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dennis on 18/02/15.
 */
public class GUIView extends JFrame {
    private Game game;

    public GUIView(String title) throws HeadlessException {
        super(title);
        game = new Game();

        initMenu();
        initView();
        showFrame();
    }

    private void initView() {
        getContentPane().removeAll();
        initComponents();
        layoutComponents();
        this.validate();
    }

    private void initMenu() {
        this.setJMenuBar(new MenuBar());
    }
}
