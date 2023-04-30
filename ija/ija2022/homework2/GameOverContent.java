package ija.ija2022.homework2;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOverContent extends JPanel {
    public GameOverContent(boolean isWin, JFrame frame){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton savedGameButton = new JButton("Menu");
        savedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        savedGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                // Code to view high scores goes here
                frame.setVisible(false);
                new MenuPresenter();
                
            }
        });
        buttonPanel.add(savedGameButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                // Code to show options goes here
                System.exit(0);
                
            }
        });
        buttonPanel.add(exitButton);
        this.add(buttonPanel);
    }
    
}
