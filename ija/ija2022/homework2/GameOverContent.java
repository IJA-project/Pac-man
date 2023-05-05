package ija.ija2022.homework2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverContent{
    public GameOverContent(boolean isWin, int lives, int points){
        JFrame frame = new JFrame();
        ImageIcon imageIcon;
        if (isWin){
            imageIcon = new ImageIcon("img\\won.png");
        }
        else{
            imageIcon = new ImageIcon("img\\game_over.png");
        }
        frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image image = imageIcon.getImage();
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        if (isWin){
            JLabel textLabel = new JLabel("Lives "+lives);
            textLabel.setForeground(Color.yellow);
            textLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
            textLabel.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+60, 199, 50);
            imageLabel.add(textLabel);
        }
        
        ImageIcon menu_icon = new ImageIcon("img\\menu_button.png");
        JButton menuButton = new JButton(menu_icon);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                // Code to view high scores goes here
                frame.setVisible(false);
                new MenuPresenter();
                
            }
        });
        menuButton.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+120, 199, 50);
        imageLabel.add(menuButton);
        
        ImageIcon exit_icon = new ImageIcon("img\\exit_button.png");
        JButton exitButton = new JButton(exit_icon);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                // Code to show options goes here
                System.exit(0);
                
            }
        });
        exitButton.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+180, 199, 50);
        imageLabel.add(exitButton);

        frame.add(imageLabel);
        frame.pack();
        frame.setVisible(true);
    }
    
}
