//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ija.ija2022.homework2.tool;

import ija.ija2022.homework2.GameOverContent;
import ija.ija2022.homework2.MenuPresenter;
import ija.ija2022.homework2.MyKeyListener;
import ija.ija2022.homework2.game.MazeConfigure;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.MazePlan;
import ija.ija2022.homework2.tool.view.FieldView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public MazeConfigure cfg;
    static char key = '`';

    public MazePresenter(MazeConfigure cfg, CommonMaze maze, PacmanObject pacmanObj, int mode) {
        this.cfg = cfg;
        this.frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame2.setSize(1200, 700);
        this.frame2.setResizable(false);
        this.frame2.setLocationRelativeTo(null);
        this.frame2.setBackground(Color.BLACK);
        this.maze = maze;
        this.pacmanObj = pacmanObj;
        this.oldMainPanel = new JPanel();
        this.mazePanel = new JPanel();
        this.mazePanel.setBackground(Color.BLACK);
        this.heartPanel = new JPanel();
        this.heartPanel.setBackground(Color.BLACK);
        this.scorePanel = new JPanel();
        this.scorePanel.setBackground(Color.BLACK);
        this.scoreLabel = new JLabel();
        this.scoreLabel.setForeground(Color.yellow);
        this.scoreLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.setBackground(Color.BLACK);
        this.mainPanel.setSize(600, 700);
        this.mainPanel.setPreferredSize(new Dimension(600, 700));
        this.mainPanel.setLayout(new BorderLayout());
        this.mainAttributesPanel =  new JPanel(new BorderLayout());
        this.mainAttributesPanel.setBackground(Color.BLACK);
        this.mainAttributesPanel.setSize(600, 35);
        this.mainAttributesPanel.setLayout(new BorderLayout());
        this.mainAttributesPanel.setPreferredSize(new Dimension(600, 90));
        if (mode == 2){
            addNextButton();
        }
        this.heartLabelOne = new JLabel();
        this.heartLabelTwo = new JLabel();
        this.heartLabelThree = new JLabel();
        addMenuButton();

        
    }

    public void updateMaze(CommonMaze maze){
        this.maze = maze;
        if (maze != null && ((PacmanObject)maze.getPacman()!= null)){
            this.pacmanObj = ((PacmanObject)maze.getPacman());
        }
        
    }

    public void gameOver(){
        //System.out.println(this.pacmanObj.isWin());
        if (this.pacmanObj.isDead() || this.pacmanObj.isWin()){
            //System.out.println("11111");
            frame2.setVisible(false);
            GameOverContent gameover = new GameOverContent(this.pacmanObj.isWin(), this.pacmanObj.getLives(), this.pacmanObj.getPoints());
        }

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
        ImageIcon icon = new ImageIcon("img\\heart.png");  
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
            content.setBackground(Color.BLACK);
            content.setPreferredSize(new Dimension(550, 550));
            
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < cols; ++j) {
                    final int row = i;
                    final int col = j;
                    final CommonMaze maze = this.maze;
                    FieldView field = new FieldView(this.maze.getField(i, j), this);
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
                                    // if(((PacmanObject)maze.getPacman()).isWin() == true || ((PacmanObject)maze.getPacman()).isDead() == true){
                                    //     try {
                                    //         Thread.sleep(500);
                                    //     } catch (InterruptedException e1) {
                                    //         // TODO Auto-generated catch block
                                    //         e1.printStackTrace();
                                    //     }
                                    //     gameOver();
                                    //     // break;
                                    // }
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
            heartPanel.setPreferredSize(new Dimension(240, 30));
            heartPanel.add(heartLabelOne);
            heartPanel.add(heartLabelTwo);
            heartPanel.add(heartLabelThree);

            updateScores();
            scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            scorePanel.setPreferredSize(new Dimension(240, 30));
            scorePanel.add(scoreLabel);

            // mainPanel.removeAll();
            // mainPanel.revalidate();
            // mainPanel.repaint();
            mainPanel.setPreferredSize(new Dimension(550, 700));
            mainPanel.add(this.mazePanel, BorderLayout.CENTER);
            //mainAttributesPanel.removeAll();
            mainAttributesPanel.revalidate();
            mainAttributesPanel.repaint();
            mainAttributesPanel.add(heartPanel, BorderLayout.WEST);
            mainAttributesPanel.add(scorePanel, BorderLayout.EAST);
            mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);
            JPanel mainCenterPanel = new JPanel();
            mainCenterPanel.add(mainPanel, BorderLayout.CENTER);
            mainCenterPanel.setBackground(Color.BLACK);
            // Remove the old main panel and add the new content panel to it
            JPanel oldMainPanel = (JPanel) this.frame2.getContentPane();
            oldMainPanel.removeAll();
            oldMainPanel.add(mainCenterPanel);
            if (pacmanObj != null){
                MyKeyListener keyListener = new MyKeyListener(pacmanObj, this.maze, this);
                this.frame2.addKeyListener(keyListener);
            }
            this.frame2.setFocusable(true);
            this.frame2.requestFocusInWindow();
            //this.frame2.pack();
            this.frame2.setVisible(true);
            oldMainPanel.revalidate();
            oldMainPanel.repaint();
        }
    }

    public void initializeInterfaceSaves(int lives, int points, boolean isButton) {
        if(this.maze != null){
            int rows = this.maze.numRows();
            int cols = this.maze.numCols();
            GridLayout layout = new GridLayout(rows, cols);
            layout.setHgap(0);
            layout.setVgap(0);
            JPanel content = new JPanel(layout);
            content.setBackground(Color.BLACK);
            content.setPreferredSize(new Dimension(550, 550));
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < cols; ++j) {
                    FieldView field = new FieldView(this.maze.getField(i, j), this);
                    content.add(field);
                }
            }
            // Remove the old main panel and add the new content panel to it
            this.mazePanel.removeAll();
            this.mazePanel.add(content);

            ImageIcon icon = new ImageIcon("img\\heart.png");  
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
                heartPanel.setPreferredSize(new Dimension(240, 30));
                this.heartLabelOne.setIcon(null);
                this.heartLabelOne.setText("Game Over");
                this.heartLabelTwo.setIcon(null);
                this.heartLabelThree.setIcon(null);
    
            }
            heartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            heartPanel.setPreferredSize(new Dimension(240, 30));
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
            scorePanel.setPreferredSize(new Dimension(240, 30));
            scorePanel.add(scoreLabel);

            mainPanel.setPreferredSize(new Dimension(550, 700));
            mainPanel.add(this.mazePanel, BorderLayout.CENTER);
            //mainAttributesPanel.removeAll();
            mainAttributesPanel.revalidate();
            mainAttributesPanel.repaint();
            mainAttributesPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
            mainAttributesPanel.add(heartPanel, BorderLayout.WEST);
            mainAttributesPanel.add(scorePanel, BorderLayout.EAST);
            mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);
            this.frame2.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    key = e.getKeyChar();
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    // Not used
                }
            
                @Override
                public void keyTyped(KeyEvent e) {
                    // Not used
                }
            });
            JPanel mainCenterPanel = new JPanel();
            mainCenterPanel.add(mainPanel, BorderLayout.CENTER);
            mainCenterPanel.setBackground(Color.BLACK);
            // Remove the old main panel and add the new content panel to it
            JPanel oldMainPanel = (JPanel) this.frame2.getContentPane();
            oldMainPanel.removeAll();
            oldMainPanel.add(mainCenterPanel);
            this.frame2.setFocusable(true);
            this.frame2.requestFocusInWindow();
            //this.frame2.pack();
            this.frame2.setVisible(true);
            oldMainPanel.revalidate();
            oldMainPanel.repaint();
        }
    }

    public char getKey(){
        char tmp = this.key;
        this.key = '~';
        return tmp;
    }

    public void addNextButton() {

        JButton nextButton = new JButton();
        nextButton.setSize(new Dimension(70, 40));
        ImageIcon next_icon = new ImageIcon("img\\next_reverse.png");
        Image scaledNextImage = next_icon.getImage().getScaledInstance(nextButton.getWidth(), nextButton.getHeight(), Image.SCALE_SMOOTH);
        nextButton.setOpaque(false);
        nextButton.setIcon(new ImageIcon(scaledNextImage));
        nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    key = 'w';
                }
            });
        //nextButton.setBounds(530, 10,99, 25 );
        this.mainAttributesPanel.add(nextButton, BorderLayout.CENTER);
        mainAttributesPanel.revalidate();
        mainAttributesPanel.repaint();
        //mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);
    }

    public void addMenuButton() {
        ImageIcon icon = new ImageIcon("img\\pacman_font.png");  
        Image scaledImage = icon.getImage().getScaledInstance(230, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setSize(600, 50);

        ImageIcon menu_icon = new ImageIcon("img\\menu_button.png");
        Image scaledMenuImage = menu_icon.getImage().getScaledInstance(100, 24, Image.SCALE_SMOOTH);
        JButton menuButton = new JButton(new ImageIcon(scaledMenuImage));
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                // Code to view high scores goes here
                // frame2.setVisible(false);
                // new MenuPresenter();
                
            }
        });
        menuButton.setBounds(450, 10,99, 25 );
        imageLabel.add(menuButton, BorderLayout.EAST);
        this.mainPanel.add(imageLabel, BorderLayout.NORTH);
    }

}
