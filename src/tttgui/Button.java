package tttgui;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private boolean claimed;
    private Player owner;

    public Button() {
        this.setPreferredSize(new Dimension(100,100));
        this.setBackground(Color.WHITE);
        this.setFont(new Font(Font.SANS_SERIF, Font.BOLD,48));
        claimed = false;
        // default owner value is null

    }

    // getter returns private value indirectly
    public boolean isClaimed() {
        return claimed;
    }
    public Player getOwner() {
        return owner;
    }

    public void claim(Player current) {
        if (current == Player.X) {
            this.setText("X");
            owner = Player.X;
        } else {
            this.setText("O");
            owner = Player.O;
        }
        claimed = true;
    }
}

