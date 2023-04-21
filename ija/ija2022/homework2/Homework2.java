/*
 * IJA 2022/23: Úloha 2
 * Spuštění presentéru (vizualizace) implementace modelu bludiště.
 */
package ija.ija2022.homework2;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//--- Importy z implementovaneho reseni ukolu
import ija.ija2022.homework2.game.KeyObject;
import ija.ija2022.homework2.game.MazeConfigure;
//--- 

//--- Importy z baliku dodaneho nastroje
import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
//--- 

/**
 * Třída spustí vizualizaci implementace modelu bludiště. 
 * Prezentér je implementován třídou {@link MazePresenter}, dále využívá prostředky definované 
 * v balíku ija.ija2022.homework2.common, který je součástí dodaného nástroje.
 * @author Radek Kočí
 */
public class Homework2 {
    
    public static void main(String... args) {
        MazeConfigure cfg = new MazeConfigure();



        try {
            File myobj = new File("Pac-man\\ija\\ija2022\\homework2\\filename.txt");
            Scanner myReader = new Scanner(myobj);
            int count = 0;
            while (myReader.hasNextLine()) {
                if (count ==0){
                      String[] param = myReader.nextLine().split(" ");

                    cfg.startReading(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
                }else{
                    cfg.processLine(myReader.nextLine());
                }
                count++;
            }
            cfg.stopReading();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        CommonMaze maze = cfg.createMaze();
        MazePresenter presenter = new MazePresenter(maze);
        presenter.open();

        sleep(2000);

        CommonMazeObject obj = maze.ghosts().get(0);
        CommonMazeObject pacman = maze.getPacman();


        while (true){

            Random random = new Random();
            int randomWintNextIntWithinARange = random.nextInt(4 - 0) + 0;
//            sleep(150);
            char tmp = presenter.GetChar();
            if (randomWintNextIntWithinARange == 0){
                sleep(400);
                obj.move(CommonField.Direction.U);
            } else if (randomWintNextIntWithinARange == 1) {
                sleep(400);
                obj.move(CommonField.Direction.D);
            } else if (randomWintNextIntWithinARange == 2) {
                sleep(400);
                obj.move(CommonField.Direction.R);
            } else if (randomWintNextIntWithinARange ==3) {
                sleep(400);
                obj.move(CommonField.Direction.L);
            }

            if (Character.toLowerCase(tmp)=='w'){
                pacman.move(CommonField.Direction.U);
            } else if (Character.toLowerCase(tmp)=='s') {
                pacman.move(CommonField.Direction.D);
            } else if (Character.toLowerCase(tmp)=='a') {
                pacman.move(CommonField.Direction.L);
            } else if (Character.toLowerCase(tmp)=='d') {
                pacman.move(CommonField.Direction.R);
            }

            if (pacman.getLives()==0){
                break;
            }
        }


        obj.move(CommonField.Direction.L);
        pacman.move(CommonField.Direction.L);

        sleep(2000);
        obj.move(CommonField.Direction.L);

        pacman.move(CommonField.Direction.U);
        sleep(1000);
        pacman.move(CommonField.Direction.U);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.R);
        sleep(1000);
        obj.move(CommonField.Direction.L);
        sleep(1000);
        obj.move(CommonField.Direction.U);
    }

    /**
     * Uspani vlakna na zadany pocet ms.
     * @param ms Pocet ms pro uspani vlakna.
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Homework2.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
