
package ija.ija2022.homework2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;

import ija.ija2022.homework2.game.GhostObject;
import ija.ija2022.homework2.game.MazeConfigure;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PacmanGame {

    public PacmanGame(int mode, int buttonMode, String filename){
        // This code runs when the button is clicked
        MazeConfigure cfg = new MazeConfigure();

        //Here you can choose how you want to load the maze from saving file or from txt file it's like satrt a new game. loadMaze is for txt file and loadSave is for saving file.
        if (mode == 1){
            String filePath = "1.txt";
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            cfg.loadMaze(filename);
            CommonMaze maze = cfg.createMaze();
            CommonMazeObject pacman = maze.getPacman();
            MazePresenter presenter = new MazePresenter(maze, (PacmanObject)pacman);
                        //cfg.loadMaze("ija\\ija2022\\homework2\\filename.txt");
            // for loadsave move packman
            presenter.initializeInterface();
            List<Thread> list = new ArrayList<>();
            for (CommonMazeObject obj : maze.ghosts()) {
                list.add( new Thread(() -> {
                    try {
                        // moving ghost to field where pacman is
                        while(true){     
                            Thread.sleep(250);   
                            if (pacman.getLives() == 0 || ((PacmanObject)pacman).isWin()) {
                                break;
                            }              
                            ((GhostObject)obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(), maze);
                            maze.saveState();
                            presenter.updateLives();
                            presenter.updateScores();
                            if(((PacmanObject)maze.getPacman()).isWin() == true || ((PacmanObject)maze.getPacman()).isDead() == true){
                                try {
                                    Thread.sleep(75);
                                } catch (InterruptedException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                presenter.gameOver();
                            }
    
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }

            // Thread thread2 = new Thread(()->{
            //     presenter.updateLives();
            //     presenter.updateScores();
            //     if(((PacmanObject)pacman).isWin() == true ||((PacmanObject)pacman).isDead() == true){
            //         try {
            //             Thread.sleep(75);
            //         } catch (InterruptedException e1) {
            //             // TODO Auto-generated catch block
            //             e1.printStackTrace();
            //         }
            //         presenter.gameOver();
            //     }

            // });
            for (Thread obj : list){
                obj.start();
            }
            // thread2.start();
            
        }
        else if (mode == 2){
            if (buttonMode == 1){
                cfg.loadSave("1.txt");//must be deleted
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(maze, null);
            }
            else if(buttonMode == 2){

            }            
        }else{
            if(buttonMode == 1){
                cfg.loadReverseSave("1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(maze,null);
            }
            else if(buttonMode == 2){
                
            }

        }
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(PacmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
