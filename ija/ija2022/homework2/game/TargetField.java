package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.AbstractObservableField;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class TargetField extends AbstractObservableField {
    private final int row;
    private final int col;
    private CommonMaze maze;

    private CommonMazeObject mazeObject;

    public TargetField(int row, int col){
        this.row = row;
        this.col = col;
        mazeObject = null;

    }


    @Override
    public void setMaze(CommonMaze maze){
        this.maze = maze;
    }

    @Override
    public CommonField nextField(Direction dirs){
        CommonField field = switch (dirs) {
            case D -> this.maze.getField(this.row + 1, this.col);
            case L -> this.maze.getField(this.row, this.col - 1);
            case R -> this.maze.getField(this.row, this.col + 1);
            case U -> this.maze.getField(this.row - 1, this.col);
        };
        if(field == null){
            throw new UnsupportedOperationException("Field outside the Maze");
        }
        return field;
    }

    @Override
    public boolean put(CommonMazeObject object){
        if (mazeObject == null){
            mazeObject = object;
            notifyObservers();
            return true;
        }else{
            notifyObservers();
            return false;
        }
    }

    @Override
    public boolean remove(CommonMazeObject object){
        if (mazeObject == object && mazeObject != null){
            mazeObject = null;
            notifyObservers();
            return true;
        }else {
            notifyObservers();
            return false;
        }
    }

    @Override
    public boolean isEmpty(){
        return mazeObject == null;
    }

    @Override
    public boolean isPacman(){
        return mazeObject instanceof PacmanObject;
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
        return mazeObject;
    }

    @Override
    public boolean canMove(){
        return true;
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof TargetField && ((TargetField)obj).getCol() == this.col && ((TargetField)obj).getRow() == this.row;

    }

    private int getCol(){
        return this.col;
    }

    private int getRow(){
        return this.row;
    }

    public boolean contains(CommonMazeObject object){
        return object == mazeObject;
    }
}
