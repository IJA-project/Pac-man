package ija.ija2022.homework2.tool.common;
import java.util.*;

public interface CommonMaze {
    public CommonField getField(int row, int col);
    public List<CommonMazeObject> ghosts();

    public CommonMazeObject getPacman();
    public int numRows();
    public int numCols();
}
