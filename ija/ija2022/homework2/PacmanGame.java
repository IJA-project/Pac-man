
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
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.MazePresenter;

public class PacmanGame {

    public PacmanGame(){
        // This code runs when the button is clicked
        MazeConfigure cfg = new MazeConfigure();

        //Here you can choose how you want to load the maze from saving file or from txt file it's like satrt a new game. loadMaze is for txt file and loadSave is for saving file.
        cfg.loadMaze("ija\\ija2022\\homework2\\filename.txt");
        //cfg.loadSave("C:\\Users\\Lenovo\\IdeaProjects\\java_homework_2\\ija\\1682018088182.txt");
        CommonMaze maze = cfg.createMaze();
        CommonMazeObject pacman = maze.getPacman();
        MazePresenter presenter = new MazePresenter(maze);

        presenter.button();

        //While loop for moving pacman and ghosts using threads and process builder.
            char tmp = presenter.GetChar();
            while(true){
                ((PacmanObject)pacman).keyMoving(tmp);
                maze.saveState();
                 for (CommonMazeObject obj : maze.ghosts()) {
                    ((GhostObject)obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(), maze);
                    maze.saveState();
                    if (pacman.getLives() == 0) {
                        break;
                    }
                 }
            }
            // // try {
            //     Thread pacmanThread = new Thread(() -> {
            //         // try {
            //             //moving pacman using kyboard or mouse
            //             while(true){
            //                 // System.out.println("1");
            //                 // ProcessBuilder processBuilder = new ProcessBuilder("java", "pacman");
            //                 // Process process = processBuilder.start();
            //                 ((PacmanObject)pacman).keyMoving(tmp);
            //                 // process.waitFor();

            //             }
            //             //((PacmanObject)pacman).mouseMoving(1,12, maze);
            //         // } catch (IOException | InterruptedException e) {
            //         //     e.printStackTrace();
            //         // }
            //     });
            //     List<Thread> list = new ArrayList<>();
            //     for (CommonMazeObject obj : maze.ghosts()) {
            //         list.add( new Thread(() -> {
            //             try {
            //             //     //moving ghost to field where pacman is
            //                 while(true){     
            //                     Thread.sleep(250);
            //             //         Random random = new Random();
            //             //         ProcessBuilder processBuilder = new ProcessBuilder("java", "ghost");
            //                     // Process process = processBuilder.start();                       
            //                     ((GhostObject)obj).processMoving(pacman.getField().getRow(), pacman.getField().getCol(), maze);
            //                         // System.out.println(((GhostObject)obj).getField());
            //             //         process.waitFor();

            //                 }
            //             } catch (InterruptedException e) {
            //                 e.printStackTrace();
            //             }
            //         }));
                    
            //         // ghostThread.start();
            //     }
            //     for (Thread obj : list){
            //         obj.start();
            //     }

            // pacmanThread.start();
            
            // System.out.println("2");
            // // pacmanThread.join();
            
            
            // // } catch (InterruptedException e) {
            // //     e.printStackTrace();
            // // }

            // // if (pacman.getLives() == 0) {
            // //     break;
            // // }



    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(PacmanGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
