/**
 * Project name: Pac-man
 * File name: MazePresenter.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: MazePresenter class display a game screen with maze and other attributes.
 */

package tool;

import game.MazeConfigure;
import game.PacmanObject;
import tool.common.CommonMaze;
import tool.view.FieldView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/**
 * MazePresenter class display a game screen with maze and other attributes.
 */
public class MazePresenter extends JComponent {
    /** Frame of the game. */
    public JFrame frame2 = new JFrame();
    /** Attribute representing of chosen maze*/
    private CommonMaze maze;
    /** Attribute representing of chosen pacman object*/
    private PacmanObject pacmanObj;
    /** Attribute representing of maze Panel*/
    private JPanel mazePanel;
    /** Attribute representing of heart Panel*/
    private JPanel heartPanel;
    /** Attribute representing of score Panel*/
    private JPanel scorePanel;
    /** Attribute representing of main Panel*/
    private JPanel mainPanel;
    /** Attribute representing panel containing pacman's lives and score*/
    private JPanel mainAttributesPanel;
    /** Attribute representing label of the first heart*/
    private JLabel heartLabelOne;
    /** Attribute representing label of the second heart*/
    private JLabel heartLabelTwo;
    /** Attribute representing label of the third heart*/
    private JLabel heartLabelThree;
    /** Attribute representing label of the pacman's score*/
    private JLabel scoreLabel;
    /** Attribute representing label for text PACMAN*/
    private JLabel imageLabel;
     /** Attribute representing MazeConfigure object*/
    public MazeConfigure cfg;
     /** Attribute representing current char from keyboard*/
    static char key = '`';
    /** Attribute representing label for icone of one herts*/
    private final Image heartImage = new ImageIcon("lib\\img\\heart.png")
            .getImage()
            .getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            
    /**
     * Constructor for MazePresenter.
     * 
     * @param cfg maze configuration
     * @param maze chosen maze for the game
     * @param pacmanObj pacman object in current maze
     * @param mode mode of the game
     */
    public MazePresenter(MazeConfigure cfg, CommonMaze maze, PacmanObject pacmanObj, int mode) {
        this.cfg = cfg;
        this.frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame2.setSize(1200, 700);
        this.frame2.setResizable(false);
        this.frame2.setLocationRelativeTo(null);
        this.frame2.setBackground(Color.BLACK);
        this.frame2.setFocusable(true);
        this.frame2.requestFocusInWindow();
        this.frame2.setVisible(true);

        this.maze = maze;
        this.pacmanObj = pacmanObj;
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
        this.mainAttributesPanel = new JPanel(new BorderLayout());
        this.mainAttributesPanel.setBackground(Color.BLACK);
        this.mainAttributesPanel.setSize(600, 35);
        this.mainAttributesPanel.setLayout(new BorderLayout());
        this.mainAttributesPanel.setPreferredSize(new Dimension(600, 90));
        if (mode == 2) {
            addNextButton();
        }
        this.heartLabelOne = new JLabel();
        this.heartLabelTwo = new JLabel();
        this.heartLabelThree = new JLabel();
        addExitButton();

    }

    /**
     * Updates maze and pacman for loads.
     * 
     * @param maze new current maze
     */
    public void updateMaze(CommonMaze maze) {
        this.maze = maze;
        if (maze != null && ((PacmanObject) maze.getPacman() != null)) {
            this.pacmanObj = ((PacmanObject) maze.getPacman());
        }

    }

    /**
     * Create new object GameOverContent after ending of the game.
     */
    public void gameOver() {
        if (this.pacmanObj.isDead() || this.pacmanObj.isWin()) {
            frame2.setVisible(false);
            GameOverContent gameOver = new GameOverContent(this.pacmanObj.isWin(), this.pacmanObj.getLives(), this.pacmanObj.getPoints());
        }

    }

    /**
     * Update visualisation of scores.
     */
    public void updateScores() {
        if (this.pacmanObj.getLives() <= 0) {
            scoreLabel.setText("0");
        } else {
            scoreLabel.setText(Integer.toString(this.pacmanObj.getPoints()));
        }
    }

    /**
     * Update visualisation of lives.
     */
    public void updateLives() {
        ImageIcon scaledIcon = new ImageIcon(heartImage);
        if (this.pacmanObj.getLives() == 3) {
            this.heartLabelThree.setText(null);
            this.heartLabelOne.setIcon(scaledIcon);
            this.heartLabelTwo.setIcon(scaledIcon);
            this.heartLabelThree.setIcon(scaledIcon);
        }
        if (this.pacmanObj.getLives() == 2) {
            this.heartLabelThree.setText(null);
            this.heartLabelOne.setIcon(scaledIcon);
            this.heartLabelTwo.setIcon(scaledIcon);
            this.heartLabelThree.setIcon(null);
        }
        if (this.pacmanObj.getLives() == 1) {
            this.heartLabelThree.setText(null);
            this.heartLabelOne.setIcon(scaledIcon);
            this.heartLabelTwo.setIcon(null);
            this.heartLabelThree.setIcon(null);
        }
        if (this.pacmanObj.getLives() <= 0) {
            this.heartLabelOne.setIcon(null);
            this.heartLabelOne.setText("Game Over");
            this.heartLabelTwo.setIcon(null);
            this.heartLabelThree.setIcon(null);
        }
    }

    /**
     * Initializes the game interface.
     */
    public void initializeInterface() {
        if (this.maze != null) {
            int rows = this.maze.numRows();
            int cols = this.maze.numCols();
            GridLayout layout = new GridLayout(rows, cols);
            layout.setHgap(0);
            layout.setVgap(0);
            JPanel content = new JPanel(layout);
            content.setBackground(Color.BLACK);
            content.setPreferredSize(new Dimension(550, 550));

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    final int row = i;
                    final int col = j;
                    final CommonMaze maze = this.maze;
                    FieldView field = new FieldView(this.maze.getField(i, j), this);
                    field.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Thread thread = new Thread(() -> {
                                while (maze.getPacman().getField() != maze.getField(row, col)) {
                                    if (((PacmanObject) maze.getPacman()).isWin() == false
                                            && ((PacmanObject) maze.getPacman()).isDead() == false) {
                                        ((PacmanObject) maze.getPacman()).mouseMoving(row, col, maze);
                                        maze.saveState();
                                        try {
                                            Thread.sleep(40);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            });
                            thread.start();
                            Thread thread2 = new Thread(() -> {
                                updateLives();
                                updateScores();
                            });
                            thread2.start();
                        }
                    });
                    content.add(field);
                }
            }

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

            mainPanel.setPreferredSize(new Dimension(550, 700));
            mainPanel.add(this.mazePanel, BorderLayout.CENTER);
            mainAttributesPanel.add(heartPanel, BorderLayout.WEST);
            mainAttributesPanel.add(scorePanel, BorderLayout.EAST);
            mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);
            JPanel mainCenterPanel = new JPanel();
            mainCenterPanel.add(mainPanel, BorderLayout.CENTER);
            mainCenterPanel.setBackground(Color.BLACK);
            this.frame2.add(mainCenterPanel);
            
            if (pacmanObj != null) {
                MyKeyListener keyListener = new MyKeyListener(pacmanObj, this.maze, this);
                this.frame2.addKeyListener(keyListener);
            }
        }
    }

    /**
     * Initializes the load game interface.
     * 
     * @param lives number of pacman lives in current state
     * @param points user score in current state
     * @param isButton flag for mode of the loading, step-by-step or permanent run
     */
    public void initializeInterfaceSaves(int lives, int points, boolean isButton) {
        if (this.maze != null) {
            int rows = this.maze.numRows();
            int cols = this.maze.numCols();
            GridLayout layout = new GridLayout(rows, cols);
            layout.setHgap(0);
            layout.setVgap(0);
            JPanel content = new JPanel(layout);
            content.setBackground(Color.BLACK);
            content.setPreferredSize(new Dimension(550, 550));
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    FieldView field = new FieldView(this.maze.getField(i, j), this);
                    content.add(field);
                }
            }
            // Remove the old maze panel and add the new content panel to it
            this.mazePanel.removeAll();
            this.mazePanel.add(content);

            ImageIcon icon = new ImageIcon("lib\\img\\heart.png");
            Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            if (lives == 3) {
                this.heartLabelOne.setText(null);
                this.heartLabelOne.setIcon(scaledIcon);
                this.heartLabelTwo.setIcon(scaledIcon);
                this.heartLabelThree.setIcon(scaledIcon);
            } else if (lives == 2) {
                this.heartLabelOne.setText(null);
                this.heartLabelOne.setIcon(scaledIcon);
                this.heartLabelTwo.setIcon(scaledIcon);
                this.heartLabelThree.setIcon(null);
            } else if (lives == 1) {
                this.heartLabelOne.setText(null);
                this.heartLabelOne.setIcon(scaledIcon);
                this.heartLabelTwo.setIcon(null);
                this.heartLabelThree.setIcon(null);
            } else if (lives <= 0 && maze.getPacman() != null) {
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

            if (lives <= 0) {
                scoreLabel.setText("0");
            } else {
                scoreLabel.setText(Integer.toString(points));
            }
            scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            scorePanel.setPreferredSize(new Dimension(240, 30));
            scorePanel.add(scoreLabel);

            mainPanel.setPreferredSize(new Dimension(550, 700));
            mainPanel.add(this.mazePanel, BorderLayout.CENTER);
            mainAttributesPanel.revalidate();
            mainAttributesPanel.repaint();
            mainAttributesPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
            mainAttributesPanel.add(heartPanel, BorderLayout.WEST);
            mainAttributesPanel.add(scorePanel, BorderLayout.EAST);
            mainPanel.add(mainAttributesPanel, BorderLayout.SOUTH);
            this.frame2.addKeyListener(new KeyListener() {
                /**
                * Invoked when a key has been pressed.
                * @param e The KeyEvent object that contains the char of the pressed key, and other details.
                */
                @Override
                public void keyPressed(KeyEvent e) {
                    key = e.getKeyChar();
                }
                /**
                * Invoked when a key has been released.
                * @param e The KeyEvent object that contains the details of the key release event.
                */
                @Override
                public void keyReleased(KeyEvent e) {
                    // Not used
                }

                /**
                * Invoked when a key has been typed (pressed and released).
                *
                * @param e The KeyEvent object that contains the details of the key typed event.
                */
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
            oldMainPanel.revalidate();
            oldMainPanel.repaint();
        }
    }

    /**
     * Returns pressed key in logging.
     * 
     * @return char of the pressed key
     */
    public char getKey() {
        char tmp = this.key;
        this.key = '~';
        return tmp;
    }

    /**
     * Visualise "next" button if mode of load is step-by-step.
     */
    public void addNextButton() {
        JButton nextButton = new JButton();
        nextButton.setSize(new Dimension(70, 40));
        ImageIcon next_icon = new ImageIcon("lib\\img\\next_reverse.png");
        Image scaledNextImage = next_icon.getImage().getScaledInstance(nextButton.getWidth(), nextButton.getHeight(),
                Image.SCALE_SMOOTH);
        nextButton.setOpaque(false);
        nextButton.setIcon(new ImageIcon(scaledNextImage));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                key = 'w';
            }
        });
        this.mainAttributesPanel.add(nextButton, BorderLayout.CENTER);
        mainAttributesPanel.revalidate();
        mainAttributesPanel.repaint();
    }

    /**
     * Visualise "exit" button.
     */
    public void addExitButton() {
        ImageIcon icon = new ImageIcon("lib\\img\\pacman_font.png");
        Image scaledImage = icon.getImage().getScaledInstance(230, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        this.imageLabel = new JLabel(scaledIcon);
        imageLabel.setSize(700, 50);

        ImageIcon exit_icon = new ImageIcon("lib\\img\\exit_button.png");
        Image scaledExitImage = exit_icon.getImage().getScaledInstance(100, 24, Image.SCALE_SMOOTH);
        JButton exitButton = new JButton(new ImageIcon(scaledExitImage));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        exitButton.setBounds(450, 10, 99, 25);
        imageLabel.add(exitButton, BorderLayout.EAST);
        imageLabel.revalidate();
        imageLabel.repaint();
        this.mainPanel.add(imageLabel, BorderLayout.NORTH);
    }

    /**
     * Display title if load is ended.
     */
    public void addEndLoadLable() {
        JLabel textLabel = new JLabel();
        textLabel.setText("End of Load");
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setForeground(Color.yellow);
        textLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 25));
        textLabel.setSize(150, 50);
        imageLabel.add(textLabel, BorderLayout.WEST);
        imageLabel.revalidate();
        imageLabel.repaint();
    }
}
