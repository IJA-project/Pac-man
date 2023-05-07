/**
 * Project name: Pac-man
 * File name: WallField.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: WallField class represents wall object in maze
 */

package game;

import tool.common.AbstractObservableField;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;
import tool.common.CommonField;

/**
 * WallField class represents wall object in maze
 */
public class WallField extends AbstractObservableField {

    private int row;
    private int col;
    private CommonMaze maze;

    /**
     * Constructor for WallField
     * 
     * @param row row where wall is
     * @param col column where wall is
     */
    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Override CommonField method for set maze where wall is
     * 
     * @param maze maze where wall is
     */
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    /**
     * Override CommonField method for get next field in this direction, but throw
     * exception because wall is not movable
     * 
     * @param dirs direction where next field is
     * @exception UnsupportedOperationException always
     */
    @Override
    public CommonField nextField(CommonField.Direction dirs) {
        throw new UnsupportedOperationException("Unable to find next Object to Wall Field");
    }

    /**
     * Override CommonField method for put object in this field, but throw exception
     * because wall can't contain any object
     * 
     * @param object object to put in this field
     * @exception UnsupportedOperationException always
     */
    @Override
    public boolean put(CommonMazeObject object) {
        throw new UnsupportedOperationException("Unable to put Object in Wall Field");
    }

    /**
     * Override CommonField method for remove object from this field, but throw
     * exception because wall can't contain any object
     * 
     * @param object object to remove from this field
     * @exception UnsupportedOperationException always
     */
    @Override
    public boolean remove(CommonMazeObject object) {
        throw new UnsupportedOperationException("Unable to remove Object from Wall Field");
    }

    /**
     * Override CommonField method for get number of column where this field is
     * 
     * @return number of column where this field is
     */
    @Override
    public int getCol() {
        return this.col;
    }

    /**
     * Override CommonField method for get number of row where this field is
     * 
     * @return number of row where this field is
     */
    @Override
    public int getRow() {
        return this.row;
    }
}
