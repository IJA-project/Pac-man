package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;
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

    public void startReading(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        if (rows <= 0 || cols <= 0){
            this.errors = true;
        }
        this.lines = new String[rows]; 
    }

    public boolean processLine(String line) {
        if (line.isEmpty() || line.length() != this.cols || !(Pattern.matches("^[XSGPK.]+$", line)) || this.numOfLines >= this.rows ){
            this.errors = true;
            return false;
        }else{    
            this.lines[this.numOfLines] = line;
            this.numOfLines += 1;
            return true;
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

