package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class KeyObject implements CommonMazeObject {
    private CommonField fld;



    public KeyObject(CommonField field){
        this.fld = field;
    }

    public void takeKey(){
        this.fld.remove(this);
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
        return fld;
    }

    @Override
    public int getLives() {
        throw new UnsupportedOperationException();
    }
}
