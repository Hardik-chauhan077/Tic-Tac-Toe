package Tic_Tac_Toe_design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3]; // 3x3 grid for Tic-Tac-Toe
    private boolean xTurn = true; // X starts the game
    private JLabel turnLabel; // Label to display whose turn it is

    public TicTacToeGUI() {
        super("Tic-Tac-Toe Java Swing"); // Set the window title
        setSize(CommonConstant.Frame_Size); // Set the window size from CommonConstant
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Close the app when the window is closed
        setResizable(false); // Disable window resizing
        setLayout(null); // Use absolute layout to place components
        getContentPane().setBackground(CommonConstant.BackgroundColor); // Set background properly
        addTurnLabel(); // Add the turn indicator
        addGuiComponents(); // Add the GUI components (buttons)
    }

    private void addTurnLabel() {
        turnLabel = new JLabel("X's Turn", SwingConstants.CENTER); // X starts the game
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        turnLabel.setForeground(CommonConstant.BoardColor);
        turnLabel.setBounds(50, 10, 440, 30); // Position at the top
        add(turnLabel);
    }

    private void addGuiComponents() {
        int xPos = 50, yPos = 50, buttonSize = 150;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setBounds(xPos + j * (buttonSize + 10), yPos + i * (buttonSize + 10), buttonSize, buttonSize);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(CommonConstant.BoardColor);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        if (clickedButton.getText().equals("")) {
                            if (xTurn) {
                                clickedButton.setText("X");
                                clickedButton.setForeground(CommonConstant.XColor);
                                turnLabel.setText("O's Turn"); // Update turn indicator
                            } else {
                                clickedButton.setText("O");
                                clickedButton.setForeground(CommonConstant.OColor);
                                turnLabel.setText("X's Turn"); // Update turn indicator
                            }
                            xTurn = !xTurn;
                            checkWinner();
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().equals("")) {
                announceWinner(buttons[i][0].getText());
                return;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].getText().equals("")) {
                announceWinner(buttons[0][i].getText());
                return;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")) {
            announceWinner(buttons[0][0].getText());
            return;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().equals("")) {
            announceWinner(buttons[0][2].getText());
            return;
        }

        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                }
            }
        }

        if (draw) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetBoard();
        }
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(this, winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
        turnLabel.setText("X's Turn"); // Reset the turn indicator
    }
}
