package ija.ija2022.homework2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;

public class MyKeyListener implements KeyListener {
    private PacmanObject obj; 
    public CommonMaze maze;
    public MazePresenter presenter;



    public MyKeyListener(PacmanObject pacmanObj, CommonMaze maze, MazePresenter presenter) {
        this.obj = pacmanObj;
        this.maze = maze;
        this.presenter = presenter;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("8");
        if (((PacmanObject)this.obj).isWin() == false && ((PacmanObject)this.obj).isDead() == false){
            // System.out.println(" key pressed: " + ((PacmanObject)this.obj).getLives());


            ((PacmanObject)this.obj).keyMoving(e.getKeyChar());
            maze.saveState();
            presenter.updateLives();
            presenter.updateScores();
        }
        // if(((PacmanObject)maze.getPacman()).isWin() == true || ((PacmanObject)maze.getPacman()).isDead() == true){
        //     try {
        //         Thread.sleep(75);
        //     } catch (InterruptedException e1) {
        //         // TODO Auto-generated catch block
        //         e1.printStackTrace();
        //     }
        //     presenter.gameOver();
        // }
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