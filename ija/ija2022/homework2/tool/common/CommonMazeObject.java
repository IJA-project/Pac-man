package ija.ija2022.homework2.tool.common;


public interface CommonMazeObject{
    public boolean canMove(CommonField.Direction dir);
    public boolean move(CommonField.Direction dir);
    public CommonField getField();

    public int getPoints();
    public int getLives();
    default boolean isPacman(){return false;};
    default boolean isKey(){return false;}
    default boolean pacmanKey(){return false;}
    default boolean isPoint(){return false;}

}
