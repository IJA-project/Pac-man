//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ija.ija2022.homework2.tool;

import ija.ija2022.homework2.MenuPresenter;
import ija.ija2022.homework2.MyKeyListener;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.view.FieldView;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.*;

public class MazePresenter {
    public JFrame frame2 = new JFrame();
    private static char state;
    public CommonMaze maze;
    public PacmanObject pacmanObj;
    private MenuPresenter menuPresenter;
 
    public MazePresenter(CommonMaze maze, PacmanObject pacmanObj) {
        this.frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame2.setSize(350, 400);
        this.frame2.setPreferredSize(new Dimension(800, 800));
        this.frame2.setResizable(false);
        this.maze = maze;
        this.pacmanObj = pacmanObj;
    }

    public void button(){
        this.initializeInterface();
    }

    public void update(CommonMaze maze){
        frame2.getContentPane().removeAll();
        this.maze = maze;
        this.initializeInterface();
    }

    private void initializeInterface() {
        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows, cols);
        layout.setHgap(0);
        layout.setVgap(0);
        JPanel content = new JPanel(layout);
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j));
                content.add(field);
            }
        }
        this.frame2.add(content, "Center");
        if (pacmanObj != null){
            MyKeyListener keyListener = new MyKeyListener(pacmanObj);
            this.frame2.addKeyListener(keyListener);
        }
        this.frame2.setFocusable(true);
        this.frame2.requestFocusInWindow();
        this.frame2.pack();
        this.frame2.setVisible(true);
        this.frame2.revalidate();
        this.frame2.repaint();
    }

}
