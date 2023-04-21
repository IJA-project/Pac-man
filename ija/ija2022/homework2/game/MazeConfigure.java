package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static ija.ija2022.homework2.Homework2.sleep;

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
            Scanner myReader = new Scanner(new File(str));
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
    public void clean(){
        this.lines = null;
        this.rows = 0;
        this.cols = 0;
        this.numOfLines = 0;
        this.errors = false;
    }
    public boolean processLine(String line) {
        if (line.isEmpty() || line.length() != this.cols || !(Pattern.matches("^[XSGPKT.]+$", line)) || this.numOfLines >= this.rows ){
            this.errors = true;
            System.out.println(line.length()+" "+ this.cols );
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
        MazePresenter presenter = null;
        CommonMaze maze;
        Pattern pattern = Pattern.compile("^[0-9]{0,2} state$");
        Pattern pattern2 = Pattern.compile("^[0-9]{0,2} [0-9]{0,2} (true|false)$");
        try {
            boolean First_state = false;
            Scanner myReader = new Scanner(new File(str));
            int counts = 0;
            int cc = 0;
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                if (counts == 0) {
                    String[] parms = data.split(" ");
                    row = Integer.parseInt(parms[0]);
                    col = Integer.parseInt(parms[1]);
                    this.startReading(row, col);
                } else {
                    if (pattern.matcher(data).matches()) {
                        if (data.equals("0 state")) {
                            First_state = true;
                            continue;
                        } else if(First_state){
                            this.stopReading();
                            First_state = false;
                            cc=0;
                            maze = this.createMaze();
                            presenter = new MazePresenter(maze);
                            presenter.open();
                        }
                        sleep(1000);
                        this.clean();
                        this.startReading(row, col);
                    }else{
                        if(First_state){
                            if (counts == row + 1){
                                String[] param = data.split(" ");
                                int health = Integer.parseInt(param[0]);
                                int score = Integer.parseInt(param[1]);
                                String key = param[2];
                                PacmanObject.load(health, score, key);
                            }else {
                                this.processLine(data);
                            }

                        }else {

                            if (!pattern2.matcher(data).matches()) {
                                this.processLine(data);
                                cc++;
                                if (cc == row) {
                                    cc = 0;
                                    this.stopReading();
                                    maze = this.createMaze();
                                    presenter.update(maze);
                                    sleep(100);

                                }
                            }

                        }
                    }

                }
                counts++;
            }
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
        System.out.println("Create maze");
        rows+=2;
        cols+=2;
        System.out.println("Rows: "+rows+" Cols: "+cols + " Lines: "+lines.length);
        CommonMaze maze = new MazePlan(this.rows, this.cols, this.lines);
        return maze;
    }

}

