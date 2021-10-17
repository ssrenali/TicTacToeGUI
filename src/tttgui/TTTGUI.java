package tttgui;

import javax.swing.*;
import java.awt.*;

public class TTTGUI extends JFrame {
    // extends keyword means TTTGUI inherits from JFrame
    // so TTTGUI object "is a" JFrame
    Button[][] buttons;
    static int ROWS = 3;
    static int COLS = 3;
    Player currentPlayer;
    JLabel message;
    boolean gameOver;


    public TTTGUI() {
        //sends title to JFrame constructor
        super("TicTacToe");
        //EXIT ON CLOSE = static therefore use class to call
        //Sets the X button to exit the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel messagePanel = new JPanel();
        message = new JLabel();
        message.setText("Player X's turn");
        messagePanel.add(message);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(ROWS,COLS));
        buttons = new Button[ROWS][COLS];
        for (int i =0; i<ROWS; i++) {
            for (int j = 0; j< COLS; j++) {
                Button b = new Button();
                buttons[i][j] = b;
                gridPanel.add(b);
                b.addActionListener(new ClickHandler(this));
            }
        }
        this.add(messagePanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.SOUTH);
        this.pack(); // Fits size to contents

        currentPlayer = Player.X;
        gameOver = false;
    }

    public void move(Button b) {
        if (!b.isClaimed() && !gameOver) {
            b.claim(currentPlayer);
            Player winner = winner(); // winner method doesn't have to run as many times
            if (winner != null) {
                message.setText("Congratulations " + winner + "! You won!");
                gameOver = true;
            } else {
                // if player is X, then change to O, otherwise change to X
                currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
                String player = (currentPlayer == Player.X) ? "X" : "O";
                message.setText("Player " + player + "'s turn");
            }
        }
    }

    public Player winner() {
        Player winner = null;
        //Rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].isClaimed() && buttons[i][1].isClaimed() && buttons[i][2].isClaimed()) {
                boolean cond1 = buttons[i][0].getOwner() == buttons[i][1].getOwner();
                boolean cond2 = buttons[i][0].getOwner() == buttons[i][2].getOwner();
                if (cond1 && cond2) {
                    winner = buttons[i][0].getOwner();
                }
            }
        }
        // cols
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].isClaimed() && buttons[1][j].isClaimed() && buttons[2][j].isClaimed()) {
                boolean cond1 = buttons[0][j].getOwner() == buttons[1][j].getOwner();
                boolean cond2 = buttons[0][j].getOwner() == buttons[2][j].getOwner();
                if (cond1 && cond2) {
                    winner = buttons[0][j].getOwner();
                }
            }
        }
        //diagonal
        if (buttons[0][0].isClaimed() && buttons[1][1].isClaimed() && buttons[2][2].isClaimed()) {
            boolean cond1 = buttons[0][0].getOwner() == buttons[1][1].getOwner();
            boolean cond2 = buttons[1][1].getOwner() == buttons[2][2].getOwner();
            if (cond1 && cond2) {
                winner = buttons[0][0].getOwner();
            }
        }
        //other diagonal
        if (buttons[0][2].isClaimed() && buttons[1][1].isClaimed() && buttons[2][0].isClaimed()) {
            boolean cond1 = buttons[0][2].getOwner() == buttons[1][1].getOwner();
            boolean cond2 = buttons[1][1].getOwner() == buttons[2][0].getOwner();
            if (cond1 && cond2) {
                winner = buttons[0][2].getOwner();
            }
        }
        return winner;
    }

    public static void main(String[] args) {
        TTTGUI game = new TTTGUI();
    }
}
