/**
 * Project name: Pac-man
 * File name: MyKeyListener.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description:  MyKeyListener class implements the KeyListener interface to handle press on the buttons 'w', 's', 'd', 'a'.
 */

package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.game.PacmanObject;
import src.tool.MazePresenter;
import src.tool.common.CommonMaze;

/**
 * The MyKeyListener class implements the KeyListener interface to handle press on the buttons 'w', 's', 'd', 'a'.
 */
public class MyKeyListener implements KeyListener {
    private PacmanObject obj; 
    public CommonMaze maze;
    public MazePresenter presenter;

    /**
     * Constructor for MyKeyListener object.
     * 
     * @param pacmanObj The PacmanObject representing the player's character.
     * @param maze      The CommonMaze object representing the game maze.
     * @param presenter The MazePresenter object for updating the game view.
     */
    public MyKeyListener(PacmanObject pacmanObj, CommonMaze maze, MazePresenter presenter) {
        this.obj = pacmanObj;
        this.maze = maze;
        this.presenter = presenter;
    }

    /**
     * Invoked when a key has been pressed.
     *
     * @param e The KeyEvent object that contains the char of the pressed key, and other details.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (((PacmanObject)this.obj).isWin() == false && ((PacmanObject)this.obj).isDead() == false){
            ((PacmanObject)this.obj).keyMoving(e.getKeyChar());
            maze.saveState();
            presenter.updateLives();
            presenter.updateScores();
        }
    }

    /**
     * Invoked when a key has been released.
     *
     * @param e The KeyEvent object that contains the details of the key release event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    /**
     * Invoked when a key has been typed (pressed and released).
     *
     * @param e The KeyEvent object that contains the details of the key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}