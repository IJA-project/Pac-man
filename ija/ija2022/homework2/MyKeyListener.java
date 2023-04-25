package ija.ija2022.homework2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ija.ija2022.homework2.game.PacmanObject;

public class MyKeyListener implements KeyListener {
    private PacmanObject obj; 

    public MyKeyListener(PacmanObject pacmanObj) {
        this.obj = pacmanObj;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(" key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
        ((PacmanObject)this.obj).keyMoving(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println(" key released: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}