package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import game.GhostObject;
import game.MazeConfigure;
import game.PacmanObject;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;

public class PacmanGame {

    public PacmanGame(int mode, int buttonMode, String filename) {
        // This code runs when the button is clicked
        MazeConfigure cfg = new MazeConfigure();

        // Here you can choose how you want to load the maze from saving file or from
        // txt file it's like satrt a new game. loadMaze is for txt file and loadSave is
        // for saving file.
        if (mode == 1) {
            String filePath = "data\\save\\1.txt";
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            cfg.loadMaze(filename);
            CommonMaze maze = cfg.createMaze();
            CommonMazeObject pacman = maze.getPacman();
            MazePresenter presenter = new MazePresenter(cfg, maze, (PacmanObject) pacman, 1);

            presenter.initializeInterface();
            List<Thread> list = new ArrayList<>();
            for (CommonMazeObject obj : maze.ghosts()) {
                list.add(new Thread(() -> {
                    try {
                        // moving ghost to field where pacman is
                        while (true) {
                            Thread.sleep(300);
                            if (pacman.getLives() == 0 || ((PacmanObject) pacman).isWin()) {
                                break;
                            }
                            // System.out.println(obj.canMove(CommonField.Direction.L)+ " " +
                            // obj.canMove(CommonField.Direction.R) + " " +
                            // obj.canMove(CommonField.Direction.U) + " " +
                            // obj.canMove(CommonField.Direction.D));
                            ((GhostObject) obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(),
                                    maze);

                            maze.saveState();
                            presenter.updateLives();
                            presenter.updateScores();

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }

            // Thread thread2 = new Thread(()->{
            // presenter.updateLives();
            // presenter.updateScores();
            // if(((PacmanObject)pacman).isWin() == true ||((PacmanObject)pacman).isDead()
            // == true){
            // try {
            // Thread.sleep(75);
            // } catch (InterruptedException e1) {
            // // TODO Auto-generated catch block
            // e1.printStackTrace();
            // }
            // presenter.gameOver();
            // }

            // });
            for (Thread obj : list) {
                obj.start();
            }
            // thread2.start();

        } else if (mode == 2) {
            if (buttonMode == 1) {
                cfg.loadSave("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze, null, 1);
            } else if (buttonMode == 2) {
                cfg.loadSaveStepByStep("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze, null, 2);

            }
        } else {
            if (buttonMode == 1) {
                // cfg.loadReverseSaveOneByOne("data\\save\\1.txt");
                cfg.loadReverseSave("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze, null, 1);
            } else if (buttonMode == 2) {
                cfg.loadReverseSaveStepByStep("data\\save\\1.txt");
                CommonMaze maze = cfg.createMaze();
                MazePresenter presenter = new MazePresenter(cfg, maze, null, 2);
            }

        }
    }
}
