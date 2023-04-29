
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

    public PacmanGame(int mode){
        // This code runs when the button is clicked
        MazeConfigure cfg = new MazeConfigure();

        //Here you can choose how you want to load the maze from saving file or from txt file it's like satrt a new game. loadMaze is for txt file and loadSave is for saving file.
        if (mode == 1){
            String filePath = "1.txt";
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            cfg.loadMaze("Pac-man\\ija\\ija2022\\homework2\\filename.txt");
            CommonMaze maze = cfg.createMaze();
            CommonMazeObject pacman = maze.getPacman();
            MazePresenter presenter = new MazePresenter(maze, (PacmanObject)pacman);
                        //cfg.loadMaze("ija\\ija2022\\homework2\\filename.txt");
            // for loadsave move packman
            presenter.initializeInterface(3, 0);
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
    
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }
            for (Thread obj : list){
                obj.start();
            }
        }
        else if (mode == 2){
            cfg.loadSave("1.txt");//must be deleted
            CommonMaze maze = cfg.createMaze();
            MazePresenter presenter = new MazePresenter(maze, null);
            
        }else{
            cfg.loadReverseSave("1.txt");
            CommonMaze maze = cfg.createMaze();
            MazePresenter presenter = new MazePresenter(maze,null);
        }
        
        //cfg.loadReverseSave("C:\\Users\\Lenovo\\IdeaProjects\\java_homework_2\\ija\\1.txt");





    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(PacmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
