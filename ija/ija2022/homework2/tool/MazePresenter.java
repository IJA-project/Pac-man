//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ija.ija2022.homework2.tool;

import ija.ija2022.homework2.GameOverContent;
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
    public JLabel heartLabelOne;
    public JLabel heartLabelTwo;
    public JLabel heartLabelThree;
    public JLabel scoreLabel;

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
        this.scoreLabel = new JLabel();
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainAttributesPanel =  new JPanel(new BorderLayout());
        this.heartLabelOne = new JLabel();
        this.heartLabelTwo = new JLabel();
        this.heartLabelThree = new JLabel();
    }

    public void updateMaze(CommonMaze maze){
        this.maze = maze;
        if (maze != null){
            this.pacmanObj = ((PacmanObject)maze.getPacman());
        }
        
    }

    public void gameOver(){
        //System.out.println("11111");
        mainPanel.removeAll();
        JPanel gameover = new GameOverContent(this.pacmanObj.isWin(), frame2);
        mainPanel.add(gameover, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

    }

    public void updateScores(){
        if (this.pacmanObj.getLives() <= 0){
            scoreLabel.setText("0");
        }
        else{
            scoreLabel.setText(Integer.toString(this.pacmanObj.getPoints()));
        }
    }

    public void updateLives(){
        ImageIcon icon = new ImageIcon("img\\cherrypoint.png");  
        Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        if (this.pacmanObj.getLives() == 3){
            this.heartLabelThree.setText(null);
            this.heartLabelOne.setIcon(scaledIcon);
            this.heartLabelTwo.setIcon(scaledIcon);
            this.heartLabelThree.setIcon(scaledIcon);
        }
        if (this.pacmanObj.getLives() == 2){
            this.heartLabelThree.setText(null);
            this.heartLabelOne.setIcon(scaledIcon);
            this.heartLabelTwo.setIcon(scaledIcon);
            this.heartLabelThree.setIcon(null);
        }
        if (this.pacmanObj.getLives() == 1){
            this.heartLabelThree.setText(null);
            this.heartLabelOne.setIcon(scaledIcon);
            this.heartLabelTwo.setIcon(null);
            this.heartLabelThree.setIcon(null);
        }
        if (this.pacmanObj.getLives() <= 0){
            this.heartLabelOne.setIcon(null);
            this.heartLabelOne.setText("Game Over");
            this.heartLabelTwo.setIcon(null);
            this.heartLabelThree.setIcon(null);
            

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
                                // while(maze.getPacman().getField() != maze.getField(row, col)){
                                //     try {
                                //         Thread.sleep(75);
                                //     } catch (InterruptedException e1) {
                                //         // TODO Auto-generated catch block
                                //         e1.printStackTrace();
                                //     }
                                    updateLives();
                                    updateScores();
                                    if(((PacmanObject)maze.getPacman()).isWin() == true || ((PacmanObject)maze.getPacman()).isDead() == true){
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                        }
                                        gameOver();
                                        // break;
                                    }
                                // }

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

            updateLives();
            heartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            heartPanel.setPreferredSize(new Dimension(100, 30));
            heartPanel.add(heartLabelOne);
            heartPanel.add(heartLabelTwo);
            heartPanel.add(heartLabelThree);

            updateScores();
            scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            scorePanel.setPreferredSize(new Dimension(100, 30));
            scorePanel.add(scoreLabel);

            // mainPanel.removeAll();
            // mainPanel.revalidate();
            // mainPanel.repaint();
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
    public void initializeInterfaceSaves(int lives, int points) {
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
                    FieldView field = new FieldView(this.maze.getField(i, j));
                    content.add(field);
                }
            }
            // Remove the old main panel and add the new content panel to it
            this.mazePanel.removeAll();
            this.mazePanel.add(content);

            ImageIcon icon = new ImageIcon("img\\cherrypoint.png");  
            Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            if (lives == 3){
                this.heartLabelOne.setText(null);
                this.heartLabelOne.setIcon(scaledIcon);
                this.heartLabelTwo.setIcon(scaledIcon);
                this.heartLabelThree.setIcon(scaledIcon);
            }
            else
            if (lives == 2){
                this.heartLabelOne.setText(null);
                this.heartLabelOne.setIcon(scaledIcon);
                this.heartLabelTwo.setIcon(scaledIcon);
                this.heartLabelThree.setIcon(null);
            }
            else
            if (lives == 1){
                this.heartLabelOne.setText(null);
                this.heartLabelOne.setIcon(scaledIcon);
                this.heartLabelTwo.setIcon(null);
                this.heartLabelThree.setIcon(null);
            }
            else
            if (lives <= 0 && maze.getPacman() != null){
                heartPanel.setPreferredSize(new Dimension(100, 30));
                this.heartLabelOne.setIcon(null);
                this.heartLabelOne.setText("Game Over");
                this.heartLabelTwo.setIcon(null);
                this.heartLabelThree.setIcon(null);
    
            }
            heartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            heartPanel.setPreferredSize(new Dimension(100, 30));
            heartPanel.add(heartLabelOne);
            heartPanel.add(heartLabelTwo);
            heartPanel.add(heartLabelThree);

            if (lives <= 0){
                scoreLabel.setText("0");
            }
            else{
                scoreLabel.setText(Integer.toString(points));
            }
            scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            scorePanel.setPreferredSize(new Dimension(100, 30));
            scorePanel.add(scoreLabel);

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
            this.frame2.setFocusable(true);
            this.frame2.requestFocusInWindow();
            this.frame2.pack();
            this.frame2.setVisible(true);
            oldMainPanel.revalidate();
            oldMainPanel.repaint();
        }
    }
}
