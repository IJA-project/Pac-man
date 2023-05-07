/**
 * Project name: Pac-man
 * File name: MenuPresenter.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description:  MenuPresenter class display a menu screen in a game.
 */

package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

/**
 * MenuPresenter class display a menu screen in a game.
 */
public class MenuPresenter{
    /**
     * Constructor for MenuPresenter
     */
    public MenuPresenter(){
            JFrame frame = new JFrame();
            ImageIcon imageIcon = new ImageIcon("lib\\img\\menu_white.png");
            frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
    
            ImageIcon play_icon = new ImageIcon("lib\\img\\play_button.png");
            JButton playButton = new JButton( play_icon);
            playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    frame.setVisible(false);
                    new MazeSelection();
                }
            });
            playButton.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+60, 199, 50);
            imageLabel.add(playButton);
            
            ImageIcon load_icon = new ImageIcon("lib\\img\\load_button.png");
            JButton savedGameButton = new JButton(load_icon);
            savedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            savedGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    frame.setVisible(false);
                    new SettingPresenter();
                }
            });
            savedGameButton.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+120, 199, 50);
            imageLabel.add(savedGameButton);
            
            ImageIcon exit_icon = new ImageIcon("lib\\img\\exit_button.png");
            JButton exitButton = new JButton(exit_icon);
            exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
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
