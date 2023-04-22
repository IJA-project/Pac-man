package ija.ija2022.homework2.tool;

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

public class MazePresenter {
    private static char state = ' ';
    private CommonMaze maze;
    private static JFrame frame = new JFrame("Pacman Demo");
    private final KeyListener listener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            MazePresenter.state = e.getKeyChar();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    };
    public MazePresenter(CommonMaze maze) {
        this.maze = maze;
    }

    public char GetChar(){
        char tmp = state;
        state = ' ';
        return tmp;
    }
    public void open() {
        try {
            SwingUtilities.invokeAndWait(this::initializeInterface);
        } catch (InvocationTargetException | InterruptedException var2) {
            Logger.getLogger(MazePresenter.class.getName()).log(Level.SEVERE, (String)null, var2);
        }

    }
    public void update(CommonMaze maze){
        frame.getContentPane().removeAll();
        this.maze = maze;
        this.open();
    }

    private void initializeInterface() {

        frame.setDefaultCloseOperation(3);
        frame.setSize(350, 400);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setResizable(false);
        int rows = this.maze.numRows();
        int cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows, cols);
        JPanel content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j));
                content.add(field);
            }
        }

        frame.getContentPane().add(content, "Center");
        frame.addKeyListener(listener);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.pack();
        frame.setVisible(true);
    }

}
