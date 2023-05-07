/**
 * Project name: Pac-man
 * File name: TargetField.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: TargetField class represents target object in maze
 */

package game;

import tool.common.AbstractObservableField;
import tool.common.CommonField;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;

/**
 * TargetField class represents target object in maze
 */
public class TargetField extends AbstractObservableField {
    /**
     * Attribute representing row where target field is
     */
    private final int row;
    /**
     * Attribute representing column where target field is
     */
    private final int col;
    /**
     * Attribute representing maze where target field is
     */
    private CommonMaze maze;
    /**
     * Attribute representing object on the target field
     */
    private CommonMazeObject mazeObject;

    /**
     * Constructor for TargetField
     * 
     * @param row row where target is
     * @param col column where target is
     */
    public TargetField(int row, int col) {
        this.row = row;
        this.col = col;
        mazeObject = null;
    }

    /**
     * Override CommonField method for set maze where target is
     */
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Override CommonField method for get next field in this direction
     * 
     * @param dirs direction where next field is
     * @return next field in this direction
     */
    @Override
    public CommonField nextField(Direction dirs) {
        CommonField field = switch (dirs) {
            case D -> this.maze.getField(this.row + 1, this.col);
            case L -> this.maze.getField(this.row, this.col - 1);
            case R -> this.maze.getField(this.row, this.col + 1);
            case U -> this.maze.getField(this.row - 1, this.col);
        };
        if (field == null) {
            throw new UnsupportedOperationException("Field outside the Maze");
        }
        return field;
    }

    /**
     * Override CommonField method for put object on this field
     * 
     * @return true if object was put on this field, false otherwise
     */
    @Override
    public boolean put(CommonMazeObject object) {
        if (mazeObject == null) {
            mazeObject = object;
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Override CommonField method for remove object from this field
     * 
     * @return true if object was removed from this field, false otherwise
     */
    @Override
    public boolean remove(CommonMazeObject object) {
        if (mazeObject == object && mazeObject != null) {
            mazeObject = null;
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Override CommonField method for check if this field is empty
     * 
     * @return true if this field is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return mazeObject == null;
    }

    /**
     * Override CommonField method for get object on this field
     * 
     * @return object on this field
     */
    @Override
    public CommonMazeObject get() {
        return mazeObject;
    }

    /**
     * Override CommonField method for check if object can move on this field
     * 
     * @return always true
     */
    @Override
    public boolean canMove() {
        return true;
    }

    /**
     * Override CommonField method for get number of column this field
     * 
     * @return number column of this field
     */
    @Override
    public int getCol() {
        return this.col;
    }

    /**
     * Override CommonField method for get number of row this field
     * 
     * @return number row of this field
     */
    @Override
    public int getRow() {
        return this.row;
    }

}
