package ija.ija2022.homework2.tool.common;

import ija.ija2022.homework2.game.*;
//import ija.ija2022.homework2.tool.common.CommonMazeObject;

import java.awt.event.KeyListener;
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