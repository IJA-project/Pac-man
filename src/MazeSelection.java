package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MazeSelection {
    public MazeSelection(){
            JFrame frame = new JFrame();
            frame.setSize(1200, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setBackground(Color.BLACK);
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.BLACK);
            mainPanel.setPreferredSize(new Dimension(600, 600));
            JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 310, 20, 310));
            // JPanel buttonPanel = new JPanel();
            // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setPreferredSize(new Dimension(600, 600));
            buttonPanel.setBackground(Color.BLACK);

            ImageIcon maze1_icon = new ImageIcon("data\\img\\maze1.png");
            Image scaledImage1 = maze1_icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
            JButton maze1Button = new JButton();
            maze1Button.setIcon(scaledIcon1);
            // playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            maze1Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    frame.setVisible(false);
                    PacmanGame pacmanGame = new PacmanGame(1, 0, "data\\maps\\map1.txt");
                }
            });
            buttonPanel.add(maze1Button);
            
            ImageIcon maze2_icon = new ImageIcon("data\\img\\maze2.png");
            Image scaledImage2 = maze2_icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
            JButton maze2Button = new JButton();
            maze2Button.setIcon(scaledIcon2);
            // savedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            maze2Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Code to view high scores goes here
                    frame.setVisible(false);
                    PacmanGame pacmanGame = new PacmanGame(1, 0, "data\\maps\\map2.txt");
                }
            });
            buttonPanel.add(maze2Button);
            
            ImageIcon maze3_icon = new ImageIcon("data\\img\\maze3.png");
            Image scaledImage3 = maze3_icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
            JButton maze3Button = new JButton();
            maze3Button.setIcon(scaledIcon3);
            // exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            maze3Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Code to show options goes here
                    frame.setVisible(false);
                    PacmanGame pacmanGame = new PacmanGame(1, 0, "data\\maps\\map3.txt");
                    
                }
            });
            buttonPanel.add(maze3Button);

            ImageIcon maze4_icon = new ImageIcon("data\\img\\maze4.png");
            Image scaledImage4 = maze4_icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
            JButton maze4Button = new JButton();
            maze4Button.setIcon(scaledIcon4);
            // exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            maze4Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Code to show options goes here
                    frame.setVisible(false);
                    PacmanGame pacmanGame = new PacmanGame(1, 0, "data\\maps\\map4.txt");
                    
                }
            });
            buttonPanel.add(maze4Button);

            JLabel textLabel = new JLabel("Chose your maze");
            textLabel.setPreferredSize(new Dimension(300, 80));
            textLabel.setBackground(Color.BLACK);
            textLabel.setHorizontalAlignment(SwingConstants.CENTER);
            textLabel.setVerticalAlignment(SwingConstants.CENTER);
            textLabel.setForeground(Color.yellow);
            textLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
            mainPanel.add(textLabel, BorderLayout.NORTH);
            mainPanel.add(buttonPanel, BorderLayout.CENTER);
            frame.add(mainPanel, BorderLayout.CENTER);
            frame.setVisible(true);
    }
    
}
