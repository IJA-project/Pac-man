package src.tool.common;

import java.util.*;

public interface CommonMaze {
    public CommonField getField(int row, int col);
    public List<CommonMazeObject> ghosts();
    public void saveState();
    public CommonMazeObject getPacman();
    public int numRows();

    public int numCols();
}
