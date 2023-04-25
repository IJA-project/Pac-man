package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            return false;
        }else{
            this.lines[this.numOfLines] = line;
            this.numOfLines += 1;
            return true;
        }
    }


    //Load state of maze like walls, points, keys, targets and pacman from saved file, also load state of pacman
    public void loadSave(String str) {
        MazePresenter presenter = null;
        CommonMaze maze;

        try (Scanner myReader = new Scanner(new File(str))) {
            int count_line = 0;
            boolean firstState = false;
            String data = myReader.nextLine();
            String[] parm = data.split(" ");
            int row= Integer.parseInt(parm[0]);
            int col = Integer.parseInt(parm[1]);
            this.startReading(row, col);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();

                if (Pattern.compile("^[0-9]{1,9} state$").matcher(data).matches()){
                    if (data.equals("0 state")){
                        firstState = true;
                        continue;
                    }else if (firstState){
                        this.stopReading();
                        firstState = false;
                        count_line = 0;
                        maze = this.createMaze();
                        presenter = new MazePresenter(maze);
                        presenter.open();
                    }
                    this.clean();
                    this.startReading(row, col);
                }else{
                    if (!Pattern.compile("^[0-9]{1,9} [0-9]{1,9} (true|false)$").matcher(data).matches()) {
                        this.processLine(data);
                        count_line++;
                        if (count_line == row && !firstState) {
                            count_line = 0;
                            this.stopReading();
                            maze = this.createMaze();
                            presenter.update(maze);
                        }
                    }else {
                        String[] param = data.split(" ");
                        int health = Integer.parseInt(param[0]);
                        int score = Integer.parseInt(param[1]);
                        String key = param[2];
                        PacmanObject.load(health, score, key);
                    }
                }
                sleep(25);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadReverseSave(String str){
        Path path = Paths.get(str);
        MazePresenter presenter = null;
        CommonMaze maze;

        long lines;
        String line_cur = "";
        try {
            String[] param = Files.readAllLines(path).get(0).split(" ");
            int row = Integer.parseInt(param[0]);
            int col = Integer.parseInt(param[1]);

            lines = Files.lines(path).count();
            int max = (int)lines-(row+2);
            lines = lines-(row+2);

            while (!line_cur.equals("0 state")){
                int count = 0;
                this.startReading(row, col);

                while ( count != row){
                    try {
                        line_cur = Files.readAllLines(path).get((int)lines+count+1);
                        this.processLine(line_cur);
                        count++;
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Index out of bounds");
                        System.exit(0);
                    }
                }

                if (lines == max){
                    this.stopReading();
                    this.stopReading();
                    maze = this.createMaze();
                    presenter = new MazePresenter(maze);
                    presenter.open();

                }else {
                    maze = this.createMaze();
                    presenter.update(maze);
                }
                this.clean();
                line_cur = Files.readAllLines(path).get((int)lines);
                lines = lines-(row+2);
                sleep(250);
            }

        }catch (IOException e){
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

        rows+=2;
        cols+=2;
        CommonMaze maze = new MazePlan(this.rows, this.cols, this.lines);
        return maze;
    }

}

