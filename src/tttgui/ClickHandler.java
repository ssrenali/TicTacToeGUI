package tttgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickHandler implements ActionListener {
    // keyword implements is for using an interface
    // which is an abstract class (has empty methods)
    TTTGUI game;

    public ClickHandler(TTTGUI game) {
        this.game = game;


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Button b = (Button)e.getSource();
        game.move(b);
    }
}
