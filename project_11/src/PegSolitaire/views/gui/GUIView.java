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

    private void initComponents() {

    }

    private void layoutComponents() {

    }

    private void initMenu() {
        this.setJMenuBar(new MenuBar(game));
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
