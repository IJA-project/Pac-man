package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;


public class GhostObject implements CommonMazeObject{
    private CommonField field;

    public GhostObject(CommonField field){
        this.field = field;
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
            if (field.nextField(dir).get()!=null){
                if (field.nextField(dir).get().isPacman()){
                    PacmanObject ptr = (PacmanObject)field.nextField(dir).get();
                    ptr.heat();
                }
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

    public CommonField getField(){
        return this.field;
    }


    public int getLives() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPoints(){throw new UnsupportedOperationException();}

    public boolean isPacman() {
        return false;
    }
}
