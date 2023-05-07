/**
 * Project name: Pac-man
 * File name: PacmanGame.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description:  PacmanGame class represents the Pacman game.
 */

package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import game.GhostObject;
import game.MazeConfigure;
import game.PacmanObject;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;
 
 /**
  * The PacmanGame class represents the Pacman game. It runs elements relative to choise of user before.
  */
public class PacmanGame {
    /**
    * Constructor for PacmanGame object.
    *
    * @param mode        The game mode: 1 for starting a new game, 2 for loading a saved game, and 3 for loading a reversed game.
    * @param buttonMode  The button mode: 1 for normal loading a saved game, and 2 for step-by-step loading a saved game.
    * @param filename    The filename of the maze configuration file.
    */
    public PacmanGame(int mode, int buttonMode, String filename){
        MazeConfigure cfg = new MazeConfigure();
        // Here you can choose how you want to load the maze from saving file or from txt file it's like satrt a new game. 
        // loadMaze is for txt file and loadSave is for saving file.
        if (mode == 1){
            String filePath = "data\\save\\1.txt";
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            cfg.loadMaze(filename);
            CommonMaze maze = cfg.createMaze();
            CommonMazeObject pacman = maze.getPacman();
            MazePresenter presenter = new MazePresenter(cfg, maze, (PacmanObject)pacman, 1);

            presenter.initializeInterface();
            List<Thread> list = new ArrayList<>();
            for (CommonMazeObject obj : maze.ghosts()) {
                list.add( new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        // Moving ghost to field where pacman is
                        while(true){     
                            Thread.sleep(300);
                            if (pacman.getLives() == 0 || ((PacmanObject)pacman).isWin()) {
                                break;
                            }
                            ((GhostObject)obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(), maze);
                            maze.saveState();
                            presenter.updateLives();
                            presenter.updateScores();
    
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
            if (buttonMode == 1){
                cfg.loadSave("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze, null, 1);
            }
            else if(buttonMode == 2){
                cfg.loadSaveStepByStep("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze, null, 2);
                
            }            
        }else{
            if(buttonMode == 1){
                cfg.loadReverseSave("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze,null, 1);
            }
            else if(buttonMode == 2){
                cfg.loadReverseSaveStepByStep("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze,null, 2);
            }

        }
    }
}
