/**
 * Project name: Pac-man
 * File name: MazePlan.java
 * Date: 06.05.2023
 * Last update: 01.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: MazePlan class represents maze plan
 */

package tool.common;

import java.io.*;
import game.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MazePlan class represents maze plan
 */
public class MazePlan implements CommonMaze {
    /**
     *Atributes representing rows size of maze
     */
    private int rows;
    /**
     *Atributes representing count of state maze configuration to save
     */
    static int count = 0;
    /**
     *Atributes representing lock for saveState method
     */
    private final Lock lock = new ReentrantLock();
    /**
     *Atributes representing cols size of maze
     */
    private int cols;
    /**
     *Atributes representing maze plan
     */
    private CommonField[][] mazePlan;
    /**
     *Atributes representing list of ghosts
     */
    private List<CommonMazeObject> ghost_lst;
    /**
     *Atributes representing object pacman
     */
    private CommonMazeObject pacman;

    /**
     * Constructor for MazePlan
     * 
     * @param rows  row of field
     * @param cols  column of field
     * @param lines lines of maze
     */
    public MazePlan(int rows, int cols, String[] lines) {
        count = 0;
        this.rows = rows;
        this.cols = cols;
        this.mazePlan = new CommonField[rows][cols];
        this.ghost_lst = new ArrayList<CommonMazeObject>();

        // create lower and upper borders
        for (int j = 0; j < cols; j++) {
            this.mazePlan[0][j] = Borders(0, j);
            this.mazePlan[rows - 1][j] = Borders(rows - 1, j);
        }
        for (int i = 1; i < rows - 1; i++) {
            this.mazePlan[i][0] = Borders(i, 0);// create left border
            this.mazePlan[i][cols - 1] = Borders(i, cols - 1);// create right border

            for (int j = 1; j < cols - 1; j++) {
                CommonField field = null;
                char symb = lines[i - 1].charAt(j - 1); // lines moved because of borders
                if (symb == 'X') {
                    field = new WallField(i, j);
                } else if (symb == '.') {
                    field = new PathField(i, j);
                } else if (symb == 'S') {
                    field = new PathField(i, j);
                    PacmanObject pacman = new PacmanObject(field);
                    field.put(pacman);
                    this.pacman = pacman;
                } else if (symb == 'G') {
                    field = new PathField(i, j);
                    GhostObject ptr = new GhostObject(field);
                    field.put(ptr);
                    this.ghost_lst.add(ptr);
                } else if (symb == 'K') {
                    field = new PathField(i, j);
                    field.put(new KeyObject(field));
                } else if (symb == 'P') {
                    field = new PathField(i, j);
                    field.put(new PointObject(field));
                } else if (symb == 'T') {
                    field = new TargetField(i, j);
                }
                field.setMaze(this);
                this.mazePlan[i][j] = field;
            }

        }
    }

    /**
     * Method returns CommonField object representing border and sets maze to it
     * 
     * @param row row of field
     * @param col column of field
     * @return CommonField object representing border
     */
    private CommonField Borders(int row, int col) {
        CommonField field = new WallField(row, col);
        field.setMaze(this);
        return field;
    }

    /**
     * Method returns pacman from maze
     * 
     * @return CommonMazeObject object representing pacman
     */
    @Override
    public CommonMazeObject getPacman() {
        return this.pacman;
    }

    /**
     * Method returns field on position row, col
     * 
     * @param row row of field
     * @param col column of field
     * @return field on position row, col, null if out of bounds
     */
    @Override
    public CommonField getField(int row, int col) {
        try {
            return this.mazePlan[row][col];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Method save configuration of maze to logging then
     */
    @Override
    public void saveState() {
        try {
            this.lock.lock();

            int count_rows = 0;
            int health = 0;
            int points = 0;
            boolean key = false;

            try {
                FileWriter fos = new FileWriter("data\\save\\1.txt", true);
                BufferedWriter pw = new BufferedWriter(fos);
                if (count == 0) {
                    pw.write((this.rows - 2) + " " + (this.cols - 2) + "\n");
                }
                pw.write(count + " state" + "\n");

                for (CommonField[] i : this.mazePlan) {
                    int count_cols = 0;
                    if (count_rows == 0 || count_rows == this.rows - 1) {
                        count_rows++;
                        continue;
                    }

                    for (CommonField j : i) {
                        if (count_cols == 0 || count_cols == this.cols - 1) {
                            count_cols++;
                            continue;
                        }
                        if (j instanceof WallField) {
                            pw.write("X");
                        } else if (j instanceof PathField) {
                            if (j.get() == null) {
                                pw.write(".");
                            } else if (j.get().isKey()) {
                                pw.write("K");
                            } else if (j.get().isPoint()) {
                                pw.write("P");
                            } else if (j.get().isPacman()) {

                                pw.write("S");
                            } else {
                                pw.write("G");
                            }
                            health = PacmanObject.lives;
                            points = PacmanObject.score;
                            key = PacmanObject.canExit;
                        } else if (j instanceof TargetField) {
                            pw.write("T");
                        }
                        count_cols++;
                    }
                    pw.write("\n");
                    count_rows++;
                }
                pw.write(health + " " + points + " " + key + "\n");

                pw.close();
                fos.close();
                count++;
            } catch (IOException e) {
                System.out.println("Error saving maze state to file: " + e.getMessage());
            }
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * Method returns number of rows which defines maze
     * 
     * @return number of rows
     */
    @Override
    public int numRows() {
        return this.rows;
    }

    /**
     * Method returns number of columns which defines maze
     * 
     * @return number of columns
     */
    @Override
    public int numCols() {
        return this.cols;
    }

    /**
     * Method returns list of all ghosts in maze
     * 
     * @return list of ghosts
     */
    @Override
    public List<CommonMazeObject> ghosts() {
        return new ArrayList<>(ghost_lst);
    }
}
