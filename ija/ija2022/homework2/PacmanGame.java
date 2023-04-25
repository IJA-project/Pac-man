
package ija.ija2022.homework2;

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
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.MazePresenter;

public class PacmanGame {

    public PacmanGame(boolean isGame){
        // This code runs when the button is clicked
        MazeConfigure cfg = new MazeConfigure();

        //Here you can choose how you want to load the maze from saving file or from txt file it's like satrt a new game. loadMaze is for txt file and loadSave is for saving file.
        if (isGame){
            cfg.loadMaze("ija\\ija2022\\homework2\\filename.txt");
        }
        else{
            cfg.loadSave("C:\\Users\\Lenovo\\IdeaProjects\\java_homework_2\\ija\\1682018088182.txt");//must be deleted
        }
        
        //cfg.loadReverseSave("C:\\Users\\Lenovo\\IdeaProjects\\java_homework_2\\ija\\1.txt");
        CommonMaze maze = cfg.createMaze();
        CommonMazeObject pacman = maze.getPacman();
        MazePresenter presenter = new MazePresenter(maze, (PacmanObject)pacman);

        presenter.button();

        if (isGame){
            cfg.loadMaze("ija\\ija2022\\homework2\\filename.txt");
        }
        
        List<Thread> list = new ArrayList<>();
        for (CommonMazeObject obj : maze.ghosts()) {
            list.add( new Thread(() -> {
                try {
                    // moving ghost to field where pacman is
                    while(true){     
                        Thread.sleep(250);   
                        if (pacman.getLives() == 0) {
                            break;
                        }              
                        ((GhostObject)obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(), maze);

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

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(PacmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
