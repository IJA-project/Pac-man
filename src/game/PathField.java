/**
 * Project name: Pac-man
 * File name: PathField.java
 * Date: 06.05.2023
 * Last update: 04.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: PathField class represents path field in maze
 */

package game;

import tool.common.AbstractObservableField;
import tool.common.CommonField;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;

/**
 * PathField class represents path field in maze
 */
public class PathField extends AbstractObservableField {
    private final int row;
    private final int col;
    private CommonMaze maze;
    private CommonMazeObject mazeObject;

    /**
     * Constructor for PathField
     * 
     * @param row row of field where path field is
     * @param col column of field where path field is
     */
    public PathField(int row, int col) {
        this.row = row;
        this.col = col;
        mazeObject = null;
    }

    /**
     * Override CommonField method for setting maze with path field
     * 
     * @param maze maze where field is
     */
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Override CommonField method for getting fild next to path field in direction
     * 
     * @param dirs direction of next field
     * @return field next to path field in direction
     */
    @Override
    public CommonField nextField(CommonField.Direction dirs) {
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
     * Override CommonField method for putting object on the current path field
     * 
     * @param object object to put on the field
     * @return true if object was put on the field, false if field is not empty
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
     * Override CommonField method for removing object from the current path field
     * 
     * @param object object to remove from the field
     * @return true if object was removed from the field, false if field is empty or
     *         object is not on the field
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
     * Override CommonField method for checking if field is empty
     * 
     * @return true if field is empty, false if field is not empty
     */
    @Override
    public boolean isEmpty() {
        return mazeObject == null;
    }

    /**
     * Override CommonField method for checking if field contains key object
     * 
     * @return true if field contains key object, false if field does not contain
     */
    @Override
    public boolean isKey() {
        return mazeObject instanceof KeyObject;
    }

    /**
     * Override CommonField method for checking if field contains point(score)
     * object
     * 
     * @return true if field contains point(score) object, false if field does not
     *         contain
     */
    @Override
    public boolean isPoint() {
        return mazeObject instanceof PointObject;
    }

    /**
     * Override CommonField method for getting object on the current path field
     * 
     * @return CommonMazeObject object on the field
     */
    @Override
    public CommonMazeObject get() {
        return mazeObject;
    }

    /**
     * Override CommonField method for checking if field contains wall
     * 
     * @return path field always returns true
     */
    @Override
    public boolean canMove() {
        return true;
    }

    /**
     * Override CommonField method for getting number of column the current path
     * field
     * 
     * @return number column of field
     */
    @Override
    public int getCol() {
        return this.col;
    }

    /**
     * Override CommonField method for getting number of row the current path field
     * 
     * @return number row of field
     */
    @Override
    public int getRow() {
        return this.row;
    }

}
