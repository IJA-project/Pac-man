/**
 * Project name: Pac-man
 * File name: CommonField.java
 * Date: 06.05.2023
 * Last update: 01.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: Interface to the fields that can be placed on the board maze Maze. Commonmazeobjects can be inserted into boxes that allow this. The interface extends the Observable interface-allows you to notify dependent objects (Observable.Observer) about changes made to the field.
 */
package src.tool.common;
/**
 *Interface to the fields that can be placed on the board maze Maze. Commonmazeobjects can be inserted into boxes that allow this. The interface extends the Observable interface-allows you to notify dependent objects (Observable.Observer) about changes made to the field.
 */
public interface CommonField extends Observable {

    /**
     * Enumeration type that defines the direction of movement in the maze.
     */
    public static enum Direction{
        /**
         * Down direction
         */
        D,
        /**
         * Left direction
         */
        L,
        /**
         * Right direction
         */
        R,
        /**
         * Up direction
         */
        U
    } 
    /**
     * Sets the maze in which the field is located.
     * @param maze maze where field is
     */
    public void setMaze(CommonMaze maze);
    /**
     * Returns the field next to the field in the direction dirs.
     * @param dirs direction of next field
     * @return field next to field in direction
     */
    public CommonField nextField(CommonField.Direction dirs);
    /**
     * Method puts maze object into field
     * @param object maze object to put into field
     * @return true if object was put into field else false
     */
    public boolean put(CommonMazeObject object);
    /**
     * Method removes maze object from field
     * @param object maze object to remove from field
     * @return true if object was removed from field else false
     */
    public boolean remove(CommonMazeObject object);
    /**
     * Method checks if field is empty
     * @return true if field is empty else false, default true
     */
    default boolean isEmpty(){return true;};
    /**
     * Method return number of row where field is
     * @return number of row
     */
    public int getRow();
    /**
     * Method return number of column where field is
     * @return number of column
     */
    public int getCol();
    /**
     * Method checks if field contains key object
     * @return true if field contains key else false, default false
     */
    default boolean isKey(){return false;};
    /**
     * Method checks if field contains point object
     * @return true if field contains point else false, default false
     */
    default boolean isPoint(){return false;};

    /**
     * Method get maze object from field
     * @return maze object from field, default null
     */
    default CommonMazeObject get(){return null;};

    /**
     * Method checks if field moveable
     * @return true if field moveable else false, default false
     */
    default boolean canMove(){return false;};

    
}
