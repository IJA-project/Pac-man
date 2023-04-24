//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ija.ija2022.homework2;

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
    public JFrame frame;
    private static char state;
    private final CommonMaze maze;
    private MenuPresenter menuPresenter;
    private KeyListener listener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
           // System.out.println(1);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            MazePresenter.state = e.getKeyChar();
        //    System.out.println(e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println(2);
        }

    };
    public MazePresenter(CommonMaze maze) {
        this.maze = maze;
        // this.frame = frame;
        // SwingUtilities.invokeLater(this::initializeInterface);
    }

    public char GetChar(){
        char tmp = this.state;
        this.state = ' ';
        return tmp;
    }

    // public void open() {

        
    //     try {
    //         System.out.println("kkkkkk");

            
    //         SwingUtilities.invokeAndWait(this::initializeInterface);
            
    //     } catch (InvocationTargetException | InterruptedException var2) {
    //         Logger.getLogger(MazePresenter.class.getName()).log(Level.SEVERE, (String)null, var2);
    //     }
    // }

    public void button(){
        this.initializeInterface();
    }

    private void initializeInterface() {
        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(350, 400);
        frame2.setPreferredSize(new Dimension(800, 800));
        frame2.setResizable(false);
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
        frame2.add(content, "Center");
        frame2.addKeyListener(listener);
        // frame.setFocusable(true);
        // frame.requestFocusInWindow();
        // frame.pack();
        frame2.setVisible(true);
        frame2.revalidate();
        frame2.repaint();
        // frame.getTopLevelAncestor().revalidate();
    }

}
