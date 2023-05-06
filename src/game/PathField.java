package src.game;

import src.tool.common.AbstractObservableField;
import src.tool.common.CommonField;
import src.tool.common.CommonMaze;
import src.tool.common.CommonMazeObject;

public class PathField extends AbstractObservableField {
    private final int row;
    private final int col;
    private CommonMaze maze;

    private CommonMazeObject mazeObject;

    public PathField(int row, int col){
        this.row = row;
        this.col = col;
        mazeObject = null;
        //notifyObservers();

    }


    @Override
    public void setMaze(CommonMaze maze){
        this.maze = maze;

    }

    @Override
    public CommonField nextField(CommonField.Direction dirs){
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
        //notifyObservers();
        if (mazeObject == null){
            mazeObject = object;
            notifyObservers();
            return true;
        }else{
            //notifyObservers();
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
            //notifyObservers();
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
        return mazeObject instanceof KeyObject;
    }

    @Override
    public boolean isPoint() {
        return mazeObject instanceof PointObject;
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
        return obj instanceof PathField && ((PathField)obj).getCol() == this.col && ((PathField)obj).getRow() == this.row;

    }


    @Override
    public int getCol(){
        return this.col;
    }

    @Override
    public int getRow(){
        return this.row;
    }

    @Override
    public boolean contains(CommonMazeObject object){
        return object == mazeObject;
    }
}
