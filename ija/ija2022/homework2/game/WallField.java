package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.AbstractObservableField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.CommonField;

public class WallField extends AbstractObservableField{

    private int row;
    private int col;
    private CommonMaze maze;
    private CommonMazeObject mazeObject;

    public WallField(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public void setMaze(CommonMaze maze){
        this.maze = maze;
    }

    @Override
    public CommonField nextField(CommonField.Direction dirs){
        throw new UnsupportedOperationException("Unable to find next Object to Wall Field");
    }

    @Override
    public boolean put(CommonMazeObject object){
        throw new UnsupportedOperationException("Unable to put Object in Wall Field");
    }

    @Override
    public boolean remove(CommonMazeObject object){
        throw new UnsupportedOperationException("Unable to remove Object from Wall Field");
    }

    @Override
    public boolean isEmpty(){
        return true;
    }

    @Override
    public boolean isPacman() {
        return false;
    }

    @Override
    public boolean isKey() {
        return false;
    }
    @Override
    public boolean isPoint() {
        return false;
    }

    @Override
    public CommonMazeObject get(){
        return this.mazeObject;
    }

    @Override
    public boolean canMove(){
        return false;
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof WallField && ((WallField)obj).getCol() == this.col && ((WallField)obj).getRow() == this.row;
    }

    private int getCol(){
        return this.col;
    }

    private int getRow(){
        return this.row;
    }

    @Override
    public boolean contains(CommonMazeObject object){
        throw new UnsupportedOperationException("Unable to contain Object in Wall Field");
    }
}
