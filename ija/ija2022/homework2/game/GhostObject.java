package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;


public class GhostObject implements CommonMazeObject{
    private CommonField field;
    private char dir = ' ';
    private int wait = 0;

    private int dontMove = 0;
    public GhostObject(CommonField field){
        this.field = field;
    }

    public boolean canMove(CommonField.Direction dir){
        CommonField field = null;
        try{
            //if wait is bigger than 0, ghost can't move
            if (wait>0){
                wait--;
                return false;
            }
            field = this.field.nextField(dir);
            if(field instanceof PathField || field instanceof TargetField){
                if (field.get()!=null){
                    if (field.get().isPacman() || field.get().isKey() || field.get().isPoint()){
                        return true;
                    }else {
                        if (dontMove>5){
                            dontMove = 0;
                            return true;
                        }
                        return false;
                    }
                }
                return true;
            }else {
                return false;
            }
        }
        catch (UnsupportedOperationException e){
            return false;
        }
    }

    public boolean move(CommonField.Direction dir){
        if (this.canMove(dir)){
            if (field.nextField(dir).get()!=null){
                if (field.nextField(dir).get().isPacman()){
                    PacmanObject ptr = (PacmanObject)field.nextField(dir).get();
                    //add wait for ghost, so he can't kill pacman in one move
                    wait = 50;
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

    public CommonField getField(){
        return this.field;
    }


    public int getLives() {
        throw new UnsupportedOperationException();
    }

    public void processMoving(int x, int y, CommonMaze maze){
        //Method for moving ghost, using algorithm looking for shortest way to pacman and moving there
        CommonField field = maze.getField(x, y);
        double wayL = 99999, wayR = 99999, wayU = 99999, wayD = 99999, waylow = 99999;
        if (field.getRow()!= this.field.getRow() || field.getCol() != this.field.getCol()) {
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

    @Override
    public int getPoints(){throw new UnsupportedOperationException();}
    
}
