/**
 * Project name: Pac-man
 * File name: PacmanObject.java
 * Date: 06.05.2023
 * Last update: 04.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: PacmanObject class represents pacman object in maze
 */

package game;

import tool.common.CommonField;
import tool.common.CommonMaze;
import tool.common.CommonMazeObject;

/**
 * PacmanObject class represents pacman object in maze
 */
public class PacmanObject implements CommonMazeObject {
    private CommonField field;
    static public int lives = 3;
    static private int tmp_row = 0;
    static private int tmp_col = 0;
    static private boolean Win = false;
    static public boolean canExit = false;
    static private char dir = ' ';
    static public int score = 0;

    /**
     * Constructor for PacmanObject
     * 
     * @param field field where pacman is
     */
    public PacmanObject(CommonField field) {
        lives = 3;
        tmp_row = 0;
        tmp_col = 0;
        Win = false;
        canExit = false;
        dir = ' ';
        score = 0;
        this.field = field;
    }

    /**
     * Load pacman parameters from file (health, scores, key)
     * 
     * @param health health of pacman
     * @param scores scores of pacman
     * @param key    if pacman has key
     */
    static public void load(int health, int scores, String key) {
        lives = health;
        score = scores;
        canExit = key.equals("true");
    }

    /**
     * Heat pacman from ghost
     */
    public void heat() {
        lives = lives - 1;
    }

    /**
     * Moving of pacman using keyboard
     * 
     * @param key key which was pressed by user on keyboard, allowed keys are W, A,
     *            S, D, and their lower case
     */
    public void keyMoving(char key) {
        if (Character.toLowerCase(key) == 'w') {
            this.move(CommonField.Direction.U);
        } else if (Character.toLowerCase(key) == 's') {
            this.move(CommonField.Direction.D);
        } else if (Character.toLowerCase(key) == 'a') {
            this.move(CommonField.Direction.L);
        } else if (Character.toLowerCase(key) == 'd') {
            this.move(CommonField.Direction.R);
        }
    }

    /**
     * Moving of pacman using mouse
     * 
     * @param x    x coordinate of field where user clicked
     * @param y    y coordinate of field where user clicked
     * @param maze maze where pacman is
     */
    public void mouseMoving(int x, int y, CommonMaze maze) {
        CommonField field = maze.getField(x, y);
        if (tmp_row != x || tmp_col != y) {
            dir = ' ';
        }
        tmp_row = x;
        tmp_col = y;

        if (!(field instanceof WallField)) {
            double wayL = 99999, wayR = 99999, wayU = 99999, wayD = 99999, waylow = 99999;

            if (field.getRow() != this.field.getRow() || field.getCol() != this.field.getCol()) {
                if (this.canMove(CommonField.Direction.L) && dir != 'R') {
                    wayL = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.L).getRow(),
                            2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.L).getCol(), 2));
                }
                if (this.canMove(CommonField.Direction.R) && dir != 'L') {
                    wayR = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.R).getRow(),
                            2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.R).getCol(), 2));
                }
                if (this.canMove(CommonField.Direction.U) && dir != 'D') {
                    wayU = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.U).getRow(),
                            2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.U).getCol(), 2));
                }
                if (this.canMove(CommonField.Direction.D) && dir != 'U') {
                    wayD = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.D).getRow(),
                            2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.D).getCol(), 2));
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

    /**
     * Implementation CommonObject method for getting info whether Pacman object can
     * move to field in direction dir
     * 
     * @param dir direction where pacman wants to move
     * @return true if pacman can move to field in direction dir, false otherwise
     */
    @Override
    public boolean canMove(CommonField.Direction dir) {
        CommonField field;
        try {
            field = this.field.nextField(dir);
        } catch (UnsupportedOperationException e) {
            return false;
        }
        return field instanceof PathField || field instanceof TargetField;
    }

    /**
     * Method get info whether pacman is winner
     * 
     * @return true if pacman is winner, false otherwise
     */
    public boolean isWin() {
        return Win;
    }

    /**
     * Method get info whether pacman is dead
     * 
     * @return true if pacman is dead, false otherwise
     */
    public boolean isDead() {
        return lives <= 0;
    }

    /**
     * Override method for moving pacman to field in direction dir
     * 
     * @param dir direction where pacman wants to move
     * @return truse if pacman move successfully, false otherwise
     */
    @Override
    public boolean move(CommonField.Direction dir) {
        if (this.canMove(dir)) {
            if (this.field.nextField(dir).isKey()) {
                canExit = true;
                this.field.nextField(dir).remove(this.field.nextField(dir).get());
            } else if (this.field.nextField(dir).isPoint()) {
                score += 10;
                this.field.nextField(dir).remove(this.field.nextField(dir).get());
            } else if (!this.field.nextField(dir).isEmpty()) {
                lives -= 1;
            } else if (this.field.nextField(dir) instanceof TargetField && canExit) {
                score += 1000;
                this.Win = true;
                field.notifyObservers();
            }
            if (this.lives <= 0) {
                field.notifyObservers();
            }
            if (this.Win == false && this.lives > 0) {
                CommonField field;
                field = this.field.nextField(dir);
                this.field.remove(this);
                field.put(this);
                this.field = field;
            }
            return true;
        }
        return false;
    }

    /**
     * Get info about pacman score
     * 
     * @return pacman score
     */
    @Override
    public int getPoints() {
        return score;
    }

    /**
     * Override CommonObject method for getting field where pacman is
     * 
     * @return pacman field
     */
    @Override
    public CommonField getField() {
        return this.field;
    }

    /**
     * Override CommonObject method for getting info about pacman current lives
     * 
     * @return number of pacman lives
     */
    @Override
    public int getLives() {
        return lives;
    }

    /**
     * Override CommonObject method for getting info if object is pacman
     * 
     * @return true
     */
    @Override
    public boolean isPacman() {
        return true;
    }

}
