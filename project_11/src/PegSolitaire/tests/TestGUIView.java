package PegSolitaire.tests;

import PegSolitaire.views.gui.GUIView;

import javax.swing.*;

/**
 * Created by dennis on 10/03/15.
 */
public class TestGUIView {
    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        new GUIView("Peg Solitaire");
    }
}
