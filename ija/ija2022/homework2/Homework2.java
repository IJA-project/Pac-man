
package ija.ija2022.homework2;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import ija.ija2022.homework2.game.GhostObject;
import ija.ija2022.homework2.game.MazeConfigure;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;


public class Homework2 {

    public static void main(String... args) {

        MazeConfigure cfg = new MazeConfigure();

        //Here you can choose how you want to load the maze from saving file or from txt file it's like satrt a new game. loadMaze is for txt file and loadSave is for saving file.
        cfg.loadMaze("ija\\ija2022\\homework2\\filename.txt");
        //cfg.loadSave("C:\\Users\\Lenovo\\IdeaProjects\\java_homework_2\\ija\\1682018088182.txt");
        CommonMaze maze = cfg.createMaze();
        CommonMazeObject pacman = maze.getPacman();
        MazePresenter presenter = new MazePresenter(maze);
        presenter.open();
//        sleep(100);
//        while (true){
//            if (presenter.GetChar() == 'q'){
//                break;
//            }
//
//            cfg.loadSave("C:\\Users\\Lenovo\\IdeaProjects\\java_homework_2\\ija\\1682018088182.txt");
//            maze = cfg.createMaze();

//        }

        //While loop for moving pacman and ghosts using threads and process builder.
        while (true) {
            char tmp = presenter.GetChar();
            try {
                Thread pacmanThread = new Thread(() -> {
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("java", "pacman");
                        Process process = processBuilder.start();
                        //moving pacman using kyboard or mouse
                        ((PacmanObject)pacman).keyMoving(tmp);
                        //((PacmanObject)pacman).mouseMoving(1,12, maze);

                        process.waitFor();

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });

                Thread ghostThread = new Thread(() -> {
                    try {

                        for (CommonMazeObject obj : maze.ghosts()) {
                            Thread.sleep(250);
                            Random random = new Random();
                            ProcessBuilder processBuilder = new ProcessBuilder("java", "ghost");
                            Process process = processBuilder.start();

                            //moving ghost to field where pacman is
                            ((GhostObject)obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(), maze);
                            process.waitFor();

                        }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            pacmanThread.start();
            ghostThread.start();
            pacmanThread.join();
            ghostThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (pacman.getLives() == 0) {
                break;
            }
        }


    }


    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Homework2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
