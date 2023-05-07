/**
 * Project name: Pac-man
 * File name: CommonMaze.java
 * Date: 06.05.2023
 * Last update: 01.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: Interface representing a game (maze) that works with CommonMazeObject objects and CommonField fields. The fields are placed in a grid, the dimensions of which are fixed. The grid representing the maze is initialized when an instance of the class is instantiated. The creation of the game (instance) is controlled through an object of the MazeConfigure class.
 */
package tool.common;

import java.util.*;

/**
 * Interface representing a game (maze) that works with CommonMazeObject objects
 * and CommonField fields. The fields are placed in a grid, the dimensions of
 * which are fixed. The grid representing the maze is initialized when an
 * instance of the class is instantiated. The creation of the game (instance) is
 * controlled through an object of the MazeConfigure class.
 */
public interface CommonMaze {
    /**
     * Method returns field on position row, col
     * 
     * @param row row of field
     * @param col column of field
     * @return field on position row, col
     */
    public CommonField getField(int row, int col);

    /**
     * Method returns list of all ghosts in maze
     * 
     * @return list of ghosts
     */
    public List<CommonMazeObject> ghosts();

    /**
     * Method save configuration of maze to logging then
     */
    public void saveState();

    /**
     * Method returns pacman from maze
     * 
     * @return CommonMazeObject object representing pacman
     */
    public CommonMazeObject getPacman();

    /**
     * Method returns number of rows which defines maze
     * 
     * @return number of rows
     */
    public int numRows();

    /**
     * Method returns number of columns which defines maze
     * 
     * @return number of columns
     */
    public int numCols();
}
