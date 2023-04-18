package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PacmanObject implements CommonMazeObject{
    private CommonField field;
    private int lives;

    private boolean canExit;

    private int score;


    public PacmanObject(CommonField field){
        this.field = field;
        this.lives = 3;
        this.score = 0;
        this.canExit = false;
    }

    public boolean canMove(CommonField.Direction dir){
        CommonField field = null;
        try{
            field = this.field.nextField(dir);
        }
        catch (UnsupportedOperationException e){
            return false;
        }
        return field instanceof PathField;
    }

    public boolean move(CommonField.Direction dir){
        if (this.canMove(dir)){
            if (this.field.nextField(dir).isKey()){
                this.canExit = true;
                field.nextField(dir).remove(this.field.nextField(dir).get());
            } else if (this.field.nextField(dir).isPoint()) {
                this.score +=1;
                field.nextField(dir).remove(this.field.nextField(dir).get());
            }
            CommonField field = null;
            field = this.field.nextField(dir);
            this.field.remove(this);
            field.put(this);
            this.field = field;
            return true;
        }
        return false;
    }

    @Override
    public int getPoints(){return this.score;}
    public CommonField getField(){
        return this.field;
    }

    public int getLives(){
        return lives;
    }

    public void heat() {
            this.lives = this.lives - 1;
    }
    public boolean isPacman(){
        return true;
    }
}
