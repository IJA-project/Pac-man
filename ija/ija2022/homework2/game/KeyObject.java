package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class KeyObject implements CommonMazeObject {
    private CommonField field;



    public KeyObject(CommonField field){
        this.field = field;
    }

    public void takeKey(){
        this.field.remove(this);
    }

    @Override
    public int getPoints(){throw new UnsupportedOperationException();}

    public boolean isKey(){
        return true;
    }

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
        return field;
    }

    @Override
    public int getLives() {
        throw new UnsupportedOperationException();
    }
}
