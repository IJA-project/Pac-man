package ija.ija2022.homework2.tool.common;

public interface CommonField extends Observable {
    public static enum Direction{
        D,
        L,
        R,
        U
    } 
    public boolean contains(CommonMazeObject object);
    public void setMaze(CommonMaze maze);
    public CommonField nextField(CommonField.Direction dirs);
    public boolean put(CommonMazeObject object);
    public boolean remove(CommonMazeObject object);
    public boolean isEmpty();
    public int getRow();
    public int getCol();
    public boolean isPacman();

    public boolean isKey();

    public boolean isPoint();
    public CommonMazeObject get();
    public boolean canMove();
    
}
