package ija.ija2022.homework2.tool.common;
import java.io.*;
import ija.ija2022.homework2.game.*;
import java.util.ArrayList;
import java.util.List;

public class MazePlan implements CommonMaze {
    private int rows;
    private int cols;
    private CommonField[][] mazePlan;
    private List<CommonMazeObject> ghost_lst;

    private CommonMazeObject pacman;

    public CommonMazeObject getPacman(){
        return this.pacman;
    }
    public MazePlan(int rows, int cols, String[] lines){
        this.rows = rows;
        this.cols = cols;
        this.mazePlan = new CommonField[rows][cols];
        this.ghost_lst = new ArrayList<CommonMazeObject>();

        // create lower and upper borders
        for (int j = 0; j < cols; j++) {
            this.mazePlan[0][j] = Borders(0, j);
            this.mazePlan[rows-1][j] = Borders(rows-1, j);
        }

        for (int i = 1; i < rows-1; i++) {

            this.mazePlan[i][0] = Borders(i, 0);// create left border
            this.mazePlan[i][cols-1] = Borders(i, cols-1);// create right border

            for (int j = 1; j < cols-1; j++) {
                CommonField field = null;
                char symb = lines[i-1].charAt(j-1); // lines moved because of borders
                if ( symb == 'X'){
                    field = new WallField(i, j);
                } else if(symb == '.'){
                    field = new PathField(i, j);
                } else if(symb == 'S'){
                    field = new PathField(i, j);
                    PacmanObject pacman = new PacmanObject(field);
                    field.put(pacman);
                    this.pacman = pacman;
                }else if(symb == 'G'){
                    field = new PathField(i, j);
                    GhostObject ptr = new GhostObject(field);
                    field.put(ptr);
                    this.ghost_lst.add(ptr);
                } else if (symb == 'K'){
                    field = new PathField(i, j);
                    field.put(new KeyObject(field));
                }else if (symb == 'P'){
                    field = new PathField(i,j);
                    field.put(new PointObject(field));
                }else if (symb == 'T'){
                    field = new TargetField(i,j);
                }
                field.setMaze(this);
                this.mazePlan[i][j] = field;
            }

        }
    }

    private CommonField Borders(int row, int col){
        CommonField field = new WallField(row, col);
        field.setMaze(this);
        return field;
    }

    @Override
    public CommonField getField(int row, int col){
        try{
            return this.mazePlan[row][col];
        } catch(IndexOutOfBoundsException e){
            return null;
        } 
    }

    //Method for saving current state of the maze
    @Override
    public void saveState() {
        int count_rows = 0;
        int health = 0;
        int points = 0;
        boolean key = false;
        // Try to open file and create PrintWriter
        try {
            FileOutputStream fos = new FileOutputStream(System.currentTimeMillis() + ".txt");
            PrintWriter pw = new PrintWriter(fos);
            pw.println((this.rows-2) + " " + (this.cols-2));

            // Print maze layout to file
            for (CommonField[] i : this.mazePlan) {
                int count_cols = 0;
                if (count_rows == 0 || count_rows == this.rows - 1) {
                    count_rows++;
                    continue;
                }

                for (CommonField j : i) {
                    //Processing fields and when it is a wall, path, target or ghost
                    if (count_cols == 0 || count_cols == this.cols - 1) {
                        count_cols++;
                        continue;
                    }
                    if (j instanceof WallField) {
                        pw.print("X");
                    } else if (j instanceof PathField) {
                        if (j.get() == null) {
                            pw.print(".");
                        } else if (j.get().isKey()) {
                            pw.print("K");
                        } else if (j.get().isPoint()) {
                            pw.print("P");
                        } else if (j.get().isPacman()) {
                            health = j.get().getLives();
                            points = j.get().getPoints();
                            key = j.get().pacmanKey();
                            pw.print("S");
                        } else {
                            pw.print("G");
                        }
                    } else if (j instanceof TargetField) {
                        pw.print("T");
                    }
                    count_cols++;
                }
                pw.println();
                count_rows++;
            }

            // Print health and points to file
            pw.print(health + " " + points+ " " +key);

            // Close PrintWriter and FileOutputStream
            pw.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving maze state to file: " + e.getMessage());
        }
    }


    @Override
    public int numRows(){
        return this.rows;
    }

    @Override
    public int numCols(){
        return this.cols;
    }

    public List<CommonMazeObject> ghosts(){
        return new ArrayList<>(ghost_lst);
    }
}