//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ija.ija2022.homework2.tool;

import ija.ija2022.homework2.MenuPresenter;
import ija.ija2022.homework2.MyKeyListener;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;
import ija.ija2022.homework2.tool.view.FieldView;

import java.awt.BorderLayout;
import java.awt.Component;
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

public class MazePresenter extends JComponent {
    public JFrame frame2 = new JFrame();
    private static char state;
    private CommonMaze maze;
    private PacmanObject pacmanObj;
    private MenuPresenter menuPresenter;
    private JPanel mainPanel;

    public MazePresenter(CommonMaze maze, PacmanObject pacmanObj) {
        this.frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame2.setSize(350, 400);
        this.frame2.setPreferredSize(new Dimension(800, 800));
        this.frame2.setResizable(false);
        this.maze = maze;
        this.pacmanObj = pacmanObj;
        this.mainPanel = new JPanel();
    }

    public void updateMaze(CommonMaze maze){

        this.maze = maze;
    }

    public void update(CommonMaze maze) {
        if (maze != null ){
        this.maze = maze;
        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = getFieldView(i, j);
                if (field != null) {
                    field.update(this.maze.getField(i, j));
                }
            }
        }
        this.initializeInterface();
        this.frame2.getContentPane().revalidate();
        this.frame2.getContentPane().repaint();
        }
    }
    
    private FieldView getFieldView(int row, int col) {
        Component[] components = this.mainPanel.getComponents();
        int index = row * this.maze.numCols() + col;
        if (index >= 0 && index < this.mainPanel.getWidth()*this.mainPanel.getHeight()) {
            return (FieldView) components[index];
        } else {
            return null; // or throw an exception or return a default value
        }
        
    }

    public void initializeInterface() {
        if(this.maze != null){
            int rows = this.maze.numRows();
            int cols = this.maze.numCols();
            GridLayout layout = new GridLayout(rows, cols);
            layout.setHgap(0);
            layout.setVgap(0);
            JPanel content = new JPanel(layout);
            content.setPreferredSize(new Dimension(700, 700));
            
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < cols; ++j) {
                    FieldView field = new FieldView(this.maze.getField(i, j));
                    content.add(field);
                }
            }
            
            // Remove the old main panel and add the new content panel to it
            JPanel mainPanel = (JPanel) this.frame2.getContentPane();
            mainPanel.removeAll();
            mainPanel.add(content);
            
            if (pacmanObj != null){
                MyKeyListener keyListener = new MyKeyListener(pacmanObj);
                this.frame2.addKeyListener(keyListener);
            }
            this.frame2.setFocusable(true);
            this.frame2.requestFocusInWindow();
            this.frame2.pack();
            this.frame2.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}
