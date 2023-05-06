/**
 * Project name: Pac-man
 * File name: CommonMazeObject.java
 * Date: 06.05.2023
 * Last update: 01.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: CommonMazeObject interface represents common object in maze
 */

package src.tool.common;

/**
 * CommonMazeObject interface represents common object in maze
 */
public interface CommonMazeObject{
    /**
     * Method return true if object can move in this direction dir
     * @param dir direction where object want to move
     * @return true if object can move in this direction dir, false if object can't move in this direction dir
     * @exception UnsupportedOperationException default. because not all objects can move
     */
    default boolean canMove(CommonField.Direction dir){throw new UnsupportedOperationException();};
    /**
     * Method return true if object move in this direction dir
     * @param dir direction where object move
     * @return true if object move in this direction dir, false if object can't move in this direction dir
     * @exception UnsupportedOperationException default, because not all objects can move
     */
    default boolean move(CommonField.Direction dir){throw new UnsupportedOperationException();};
    /**
     * Method get current field where object is
     * @return CommonField where object is
     */
    public CommonField getField();

    /**
     * Method return number of points for this object
     * @return number of points for this object
     * @exception UnsupportedOperationException default, because not all objects have points
     */
    default int getPoints(){throw new UnsupportedOperationException();};
    /**
     * Method return number of lives for this object
     * @return number of lives for this object
     * @exception UnsupportedOperationException default, because not all objects have lives
     */
    default int getLives(){throw new UnsupportedOperationException();};
    /**
     * Method return true if object is pacman
     * @return true if object is pacman, false if object is not pacman. Default return false
     */
    default boolean isPacman(){return false;};
    /**
     * Method return true if object is key
     * @return true if object is key, false if object is not key. Default return false
     */
    default boolean isKey(){return false;}
    /**
     * Method return true if object is point(score)
     * @return true if object is point(score), false if object is not point(score). Default return false
     */
    default boolean isPoint(){return false;}
}
