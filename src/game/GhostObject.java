/**
 * Project name: Pac-man
 * File name: GhostObject.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: GhostObject class represents ghost object in maze
 */

package game;

import tool.common.CommonField;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;

/**
 * GhostObject class represents ghost object in maze
 */
public class GhostObject implements CommonMazeObject {
    /**
     * Attribute representing field where ghost is
     */
    private CommonField field;
    /**
     * Attribute representing temporary row of previous field where ghost was
     */
    private int tmp_row = 0;
    /**
     * Attribute representing temporary column of previous field where ghost was
     */
    private int tmp_col = 0;
    /**
     * Attribute representing direction where ghost was moving
     */
    private char dir = ' ';
    /**
     * Attribute representing number of steps where ghost should wait when other ghost is on the same field and ghost can't move
     */
    private int wait = 0;
    /**
     * Attribute representing number of steps where ghost should wait after ghost hit the pacman
     */
    private int dontMove = 0;

    /**
     * Constructor for GhostObject
     * 
     * @param field field where ghost is
     */
    public GhostObject(CommonField field) {
        this.field = field;
    }

    /**
     * Check if ghost can move in this direction
     * 
     * @param dir direction where ghost can be moving
     * @return true if ghost can move in this direction, false otherwise
     */
    public boolean canMove(CommonField.Direction dir) {
        CommonField field = null;
        try {
            if (wait > 0) {
                wait--;
                return false;
            }
            field = this.field.nextField(dir);
            if (field instanceof PathField || field instanceof TargetField) {
                if (field.get() != null) {
                    if (field.get() instanceof PacmanObject || field.get() instanceof PointObject || field.get() instanceof KeyObject) {
                        return true;
                    } else {
                        if (dontMove > 5) {
                            dontMove = 0;
                            return true;
                        }
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    /**
     * Change field where ghost is, if it's possible, notify observers and return
     * true, otherwise return false
     * 
     * @param dir direction where ghost moving
     * @return true if ghost moved, false otherwise
     */
    public boolean move(CommonField.Direction dir) {
        if (this.canMove(dir)) {
            if (field.nextField(dir).get() != null) {
                if (field.nextField(dir).get().isPacman()) {
                    PacmanObject ptr = (PacmanObject) field.nextField(dir).get();
                    wait = 40;
                    ptr.heat();
                    dontMove = 0;
                    field.notifyObservers();
                    return true;
                }
            }

            CommonField field = null;
            field = this.field.nextField(dir);
            this.field.remove(this);
            field.put(this);
            this.field = field;
            dontMove = 0;
            return true;
        }
        dontMove++;
        return false;
    }

    /**
     * Get field where ghost is
     * 
     * @return field where ghost is
     */
    public CommonField getField() {
        return this.field;
    }

    /**
     * method for moving ghost, using algorithm looking for shortest way to pacman
     * and moving there
     * 
     * @param x    row where ghost is
     * @param y    column where ghost is
     * @param maze maze where pacman is
     */
    public void processMoving(int x, int y, CommonMaze maze) {

        CommonField field = maze.getField(x, y);
        if (this.tmp_row != x || this.tmp_col != y) {
            dir = ' ';
        }
        this.tmp_row = x;
        this.tmp_col = y;

        double wayL = 99999, wayR = 99999, wayU = 99999, wayD = 99999, waylow = 99999;
        if (field.getRow() != this.field.getRow() || field.getCol() != this.field.getCol()) {
            if (this.canMove(CommonField.Direction.L) && dir != 'R') {
                wayL = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.L).getRow(), 2)
                        + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.L).getCol(), 2));
            }
            if (this.canMove(CommonField.Direction.R) && dir != 'L') {
                wayR = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.R).getRow(), 2)
                        + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.R).getCol(), 2));
            }
            if (this.canMove(CommonField.Direction.U) && dir != 'D') {
                wayU = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.U).getRow(), 2)
                        + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.U).getCol(), 2));
            }
            if (this.canMove(CommonField.Direction.D) && dir != 'U') {
                wayD = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.D).getRow(), 2)
                        + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.D).getCol(), 2));
            }
            if (wayL <= waylow) {
                waylow = wayL;
                dir = 'L';
            }
            if (wayR <= waylow) {
                waylow = wayR;
                dir = 'R';
            }
            if (wayU <= waylow) {
                waylow = wayU;
                dir = 'U';
            }
            if (wayD <= waylow) {
                dir = 'D';
            }
            if (dir == 'L') {
                this.move(CommonField.Direction.L);
            } else if (dir == 'R') {
                this.move(CommonField.Direction.R);
            } else if (dir == 'U') {
                this.move(CommonField.Direction.U);
            } else if (dir == 'D') {
                this.move(CommonField.Direction.D);
            }

        } else {
            dir = ' ';
        }
    }
}
