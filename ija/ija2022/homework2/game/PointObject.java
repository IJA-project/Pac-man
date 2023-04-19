package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PointObject implements CommonMazeObject{
    private CommonField field;
    public PointObject(CommonField field){
       this.field = field;
   }


   public void takePoint(){
        this.field.remove(this);
   }
   @Override
   public boolean isPoint(){
        return true;
   }

    @Override
    public int getPoints(){throw new UnsupportedOperationException();}
    @Override
    public boolean canMove(CommonField.Direction dir) {
        return true;
    }


    @Override
    public boolean move(CommonField.Direction dir) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommonField getField() {
        return null;
    }

    @Override
    public int getLives() {
        throw new UnsupportedOperationException();
    }
}
