package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MazeConfigure extends Object{
    private String[] lines;
    private int rows;
    private int cols;
    private int numOfLines;
    private boolean errors;

    public MazeConfigure(){
        this.rows = 0;
        this.cols = 0;
        this.numOfLines = 0;
        this.errors = false;
    }
    //Load maze from file
    public void loadMaze(String str){
        try {
            File myobj = new File(str);
            Scanner myReader = new Scanner(myobj);
            int count = 0;
            while (myReader.hasNextLine()) {
                if (count == 0) {
                    String[] param = myReader.nextLine().split(" ");
                    this.startReading(Integer.parseInt(param[0]), Integer.parseInt(param[1]));
                } else {
                    this.processLine(myReader.nextLine());
                }
                count++;
            }
            this.stopReading();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void startReading(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        if (rows <= 0 || cols <= 0){
            this.errors = true;
        }
        this.lines = new String[rows]; 
    }

    public boolean processLine(String line) {
        if (line.isEmpty() || line.length() != this.cols || !(Pattern.matches("^[XSGPKT.]+$", line)) || this.numOfLines >= this.rows ){
            this.errors = true;
            return false;
        }else{    
            this.lines[this.numOfLines] = line;
            this.numOfLines += 1;
            return true;
        }
    }

    //Load state of maze like walls, points, keys, targets and pacman from saved file, also load state of pacman
    public void loadSave(String str){
        int row = 99999;
        int col = 99999;
        try {
            File myobj = new File(str);
            Scanner myReader = new Scanner(myobj);
            int count = 0;
            while (myReader.hasNextLine()) {
                if (count == 0) {
                    String[] param = myReader.nextLine().split(" ");
                    row = Integer.parseInt(param[0]);
                    col = Integer.parseInt(param[1]);
                    this.startReading(row, col);
                } else if (count == rows+1) {
                    String[] param = myReader.nextLine().split(" ");
                    int health = Integer.parseInt(param[0]);
                    int score = Integer.parseInt(param[1]);
                    String key = param[2];
                    PacmanObject.load(health, score, key);
                } else {
                    this.processLine(myReader.nextLine());
                }
                count++;
            }
            this.stopReading();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean stopReading() {
        if (this.numOfLines == this.rows){
            return true;
        }else{
            this.errors = true;
            return false;
        } 
    }
    
    public CommonMaze createMaze() {
        if (this.errors){
            return null;
        }
        this.rows += 2;
        this.cols += 2;
        CommonMaze maze = new MazePlan(this.rows, this.cols, this.lines);
        return maze;
    }
}

