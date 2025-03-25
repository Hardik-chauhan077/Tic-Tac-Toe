package Tic_Tac_Toe_design;

import javax.swing.*;

public class app {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToeGUI gameGUI = new TicTacToeGUI();
                gameGUI.setVisible(true); // Make the GUI visible
            }
        });
    }
}
