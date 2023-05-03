package ija.ija2022.homework2;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeSelection {
    public MazeSelection(){
            JFrame frame = new JFrame();
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            
            JButton playButton = new JButton("Maze 1");
            playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    frame.setVisible(false);
                    PacmanGame pacmanGame = new PacmanGame(1, 0, "ija\\ija2022\\homework2\\filename.txt");
                }
            });
            buttonPanel.add(playButton);
            
            JButton savedGameButton = new JButton("Maze 2");
            savedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            savedGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Code to view high scores goes here
                    frame.setVisible(false);
                    PacmanGame pacmanGame = new PacmanGame(1, 0, "ija\\ija2022\\homework2\\maze1.txt");
                }
            });
            buttonPanel.add(savedGameButton);
            
            // JButton exitButton = new JButton("Maze 3");
            // exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            // exitButton.addActionListener(new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent arg0){
            //         // Code to show options goes here
            //         frame.setVisible(false);
            //         PacmanGame pacmanGame = new PacmanGame(1, 0);
                    
            //     }
            // });
            // buttonPanel.add(exitButton);
            
            frame.add(buttonPanel);
            frame.setVisible(true);
    }
    
}
