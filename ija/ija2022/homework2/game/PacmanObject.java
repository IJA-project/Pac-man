package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;


public class PacmanObject implements CommonMazeObject{
    private CommonField field;
    static private int lives = 3;


    static private int tmp_row = 0;
    static private int tmp_col = 0;
    static private boolean Win = false;
    static private boolean canExit = false;

    static private char dir = ' ';

    static private int score = 0;


    public PacmanObject(CommonField field){
        this.field = field;
    }
    //Load state of pacman like lives, score and if he has key its loading when we start game from saved file
    static public void load(int health, int scores, String key){
        lives = health;
        score = scores;
        canExit = key.equals("true");
    }
    public void heat() {
        lives = lives - 1;
    }
    public void keyMoving(char key){
        //Move pacman to direction which we get from keyboard
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


    public void mouseMoving(int x, int y, CommonMaze maze) {
        //Move pacman to coordinates which we get from mouse click, the same moving algorithm as in ghost
            CommonField field = maze.getField(x, y);
            if (tmp_row != x || tmp_col!=y){
                dir = ' ';
            }
            tmp_row = x;
            tmp_col = y;

            if (!(field instanceof WallField)) {
                double wayL = 99999, wayR = 99999, wayU = 99999, wayD = 99999, waylow = 99999;

                if (field.getRow() != this.field.getRow() || field.getCol() != this.field.getCol()) {
                    if (this.canMove(CommonField.Direction.L) && dir != 'R') {
                        wayL = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.L).getRow(), 2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.L).getCol(), 2));
                    }
                    if (this.canMove(CommonField.Direction.R) && dir != 'L') {
                        wayR = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.R).getRow(), 2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.R).getCol(), 2));
                    }
                    if (this.canMove(CommonField.Direction.U) && dir != 'D') {
                        wayU = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.U).getRow(), 2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.U).getCol(), 2));
                    }
                    if (this.canMove(CommonField.Direction.D) && dir != 'U') {
                        wayD = Math.sqrt(Math.pow(field.getRow() - this.field.nextField(CommonField.Direction.D).getRow(), 2) + Math.pow(field.getCol() - this.field.nextField(CommonField.Direction.D).getCol(), 2));
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
    @Override
    public boolean canMove(CommonField.Direction dir){
        CommonField field ;
        try{
            field = this.field.nextField(dir);
        }
        catch (UnsupportedOperationException e){
            return false;
        }
        return field instanceof PathField || field instanceof TargetField;
    }

    public boolean isWin(){
        return Win;
    }

    public boolean isDead(){
        return lives == 0;
    }
    @Override
    public boolean move(CommonField.Direction dir){
        if (this.canMove(dir)){
            if (this.field.nextField(dir).isKey()){
                canExit = true;
                field.nextField(dir).remove(this.field.nextField(dir).get());
            } else if (this.field.nextField(dir).isPoint()) {
                score +=10;
                field.nextField(dir).remove(this.field.nextField(dir).get());
            } else if (!this.field.nextField(dir).isEmpty()) {
                lives-=1;
            } else if (this.field.nextField(dir) instanceof TargetField && canExit) {
                this.field.remove(this);
                this.field = null;
                this.Win = true;
            }

            CommonField field;
            field = this.field.nextField(dir);
            this.field.remove(this);
            field.put(this);
            this.field = field;
            return true;
        }
        return false;
    }
    @Override
    public int getPoints(){return score;}

    @Override
    public CommonField getField(){
        return this.field;
    }

    @Override
    public int getLives(){
        return lives;
    }

    @Override
    public boolean isPacman(){
        return true;
    }

    @Override
    public boolean pacmanKey(){
        return canExit;
    }
}
