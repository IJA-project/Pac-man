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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JPanel oldMainPanel;
    public JPanel mazePanel;
    public JPanel heartPanel;
    public JPanel scorePanel;
    public JPanel mainPanel;
    public JPanel mainAttributesPanel;

    public MazePresenter(CommonMaze maze, PacmanObject pacmanObj) {
        this.frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame2.setSize(350, 400);
        this.frame2.setPreferredSize(new Dimension(700, 700));
        this.frame2.setResizable(false);
        this.maze = maze;
        this.pacmanObj = pacmanObj;
        this.oldMainPanel = new JPanel();
        this.mazePanel = new JPanel();
        this.heartPanel = new JPanel();
        this.scorePanel = new JPanel();
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainAttributesPanel =  new JPanel(new BorderLayout());
    }

    public void updateMaze(CommonMaze maze){

        this.maze = maze;
    }

    // public void update(CommonMaze maze) {
    //     if (maze != null ){
    //     this.maze = maze;
    //     int rows = this.maze.numRows();
    //     int cols = this.maze.numCols();
    //     for(int i = 0; i < rows; ++i) {
    //         for(int j = 0; j < cols; ++j) {
    //             FieldView field = getFieldView(i, j);
    //             if (field != null) {
    //                 field.update(this.maze.getField(i, j));
    //             }
    //         }
    //     }
    //     this.initializeInterface();
    //     this.frame2.getContentPane().revalidate();
    //     this.frame2.getContentPane().repaint();
    //     }
    // }
    
    // private FieldView getFieldView(int row, int col) {
    //     Component[] components = this.oldMainPanel.getComponents();
    //     int index = row * this.maze.numCols() + col;
    //     if (index >= 0 && index < this.oldMainPanel.getWidth()*this.oldMainPanel.getHeight()) {
    //         return (FieldView) components[index];
    //     } else {
    //         return null; // or throw an exception or return a default value
    //     }
        
    // }

    public void gameOver(){
        mainAttributesPanel.removeAll();
        mainPanel.removeAll();
        JLabel gameover = new JLabel("Gameover");
        mainPanel.add(gameover, BorderLayout.CENTER);
        mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);

    }

    public void updateScores(){
        scorePanel.removeAll();
        scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        // Add heart images based on the player's remaining lives
        scorePanel.setPreferredSize(new Dimension(100, 30));
        if (((PacmanObject)maze.getPacman()).getLives() <= 0){
            JLabel label = new JLabel("0");
            scorePanel.add(label);
        }
        else{
            JLabel label = new JLabel(Integer.toString(((PacmanObject)maze.getPacman()).getPoints()));
            scorePanel.add(label);
        }

        scorePanel.revalidate();
        scorePanel.repaint();
    }

    public void updateLives(){
        heartPanel.removeAll();
        heartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Add heart images based on the player's remaining lives
        heartPanel.setPreferredSize(new Dimension(100, 30));
        ImageIcon imageIcon = new ImageIcon("Pac-man\\img\\cherrypoint.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        if (((PacmanObject)maze.getPacman()).getLives() <= 0 ){
            JLabel label = new JLabel("Game Over");
            heartPanel.add(label);
        }
        else{
            for(int i = ((PacmanObject)maze.getPacman()).getLives(); i > 0; --i) {
                JLabel label = new JLabel(scaledIcon);
                heartPanel.add(label);
            }
        }

        heartPanel.revalidate();
        heartPanel.repaint();
    }

    public void initializeInterface(int lives, int points) {
        if(this.maze != null){
            int rows = this.maze.numRows();
            int cols = this.maze.numCols();
            GridLayout layout = new GridLayout(rows, cols);
            layout.setHgap(0);
            layout.setVgap(0);
            JPanel content = new JPanel(layout);
            content.setPreferredSize(new Dimension(600, 600));
            
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < cols; ++j) {
                    final int row = i;
                    final int col = j;
                    final CommonMaze maze = this.maze;
                    FieldView field = new FieldView(this.maze.getField(i, j));
                    field.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // Handle the click event
                            // if (((PacmanObject)maze.getPacman()).isWin() == false && ((PacmanObject)maze.getPacman()).isDead() == false){
                                Thread thread = new Thread(()->{
                                    while(maze.getPacman().getField() != maze.getField(row, col)){
                                        if (((PacmanObject)maze.getPacman()).isWin() == false && ((PacmanObject)maze.getPacman()).isDead() == false){
                                            ((PacmanObject)maze.getPacman()).mouseMoving(row, col, maze);
                                            maze.saveState();
                                            try {
                                                Thread.sleep(40);
                                            } catch (InterruptedException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                        else{
                                            break;
                                        }
                                    }
                                });
                                thread.start();    
                            // }
                            Thread thread2 = new Thread(()->{
                                updateLives();
                                updateScores();
                                if(((PacmanObject)maze.getPacman()).isWin() == true || ((PacmanObject)maze.getPacman()).isDead() == true){
                                    try {
                                        Thread.sleep(75);
                                    } catch (InterruptedException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    gameOver();
                                }

                            });
                            thread2.start();
                        }
                    });
                    content.add(field);
                }
            }

            // Remove the old main panel and add the new content panel to it
            this.mazePanel.removeAll();
            this.mazePanel.add(content);

            heartPanel.removeAll();
            heartPanel.revalidate();
            heartPanel.repaint();
            heartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            // Add heart images based on the player's remaining lives
            heartPanel.setPreferredSize(new Dimension(100, 30));
            ImageIcon imageIcon = new ImageIcon("Pac-man\\img\\cherrypoint.png");
            Image scaledImage = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            for(int i = lives; i > 0; --i) {
                JLabel label = new JLabel(scaledIcon);
                heartPanel.add(label);
            }
            scorePanel.removeAll();
            scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            // Add heart images based on the player's remaining lives
            scorePanel.setPreferredSize(new Dimension(100, 30));
            if (lives <= 0){
                JLabel label1 = new JLabel("0");
                scorePanel.add(label1);
            }
            else{
                JLabel label1 = new JLabel(Integer.toString((points)));
                scorePanel.add(label1);
            }
            scorePanel.revalidate();
            scorePanel.repaint();
            // Add the game panel and heart panel to the main panel
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            mainPanel.setPreferredSize(new Dimension(700, 700));
            mainPanel.add(this.mazePanel, BorderLayout.CENTER);
            mainAttributesPanel.removeAll();
            mainAttributesPanel.revalidate();
            mainAttributesPanel.repaint();
            mainAttributesPanel.add(heartPanel, BorderLayout.WEST);
            mainAttributesPanel.add(scorePanel, BorderLayout.EAST);
            mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);

            // Remove the old main panel and add the new content panel to it
            JPanel oldMainPanel = (JPanel) this.frame2.getContentPane();
            oldMainPanel.removeAll();
            oldMainPanel.add(mainPanel);
            if (pacmanObj != null){
                MyKeyListener keyListener = new MyKeyListener(pacmanObj, this.maze, this);
                this.frame2.addKeyListener(keyListener);
            }
            this.frame2.setFocusable(true);
            this.frame2.requestFocusInWindow();
            this.frame2.pack();
            this.frame2.setVisible(true);
            oldMainPanel.revalidate();
            oldMainPanel.repaint();
        }
    }
}
