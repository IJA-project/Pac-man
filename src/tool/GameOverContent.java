/**
 * Project name: Pac-man
 * File name: GameOverContent.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description:  GameOverContent class display a game over screen in a game.
 */

package tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * GameOverContent class display a game over screen in a game.
 */
public class GameOverContent{
    /**
     * Constructor for GameOverContent
     * @param isWin does player win or loose
     * @param lives number of lives at the end of game
     * @param points number of points at the end of game
     */
    public GameOverContent(boolean isWin, int lives, int points){
        JFrame frame = new JFrame();
        ImageIcon imageIcon;
        if (isWin){
            imageIcon = new ImageIcon("lib\\img\\won.png");
        }
        else{
            imageIcon = new ImageIcon("lib\\img\\game_over.png");
        }
        frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        if (isWin){
            JLabel textLabel2 = new JLabel("Scores: "+points);
            textLabel2.setForeground(Color.yellow);
            textLabel2.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
            textLabel2.setBounds((imageIcon.getIconWidth()+200)/2-220, imageIcon.getIconHeight()/2+60, 300, 50);
            imageLabel.add(textLabel2);
        }
        
        ImageIcon menu_icon = new ImageIcon("lib\\img\\menu_button.png");
        JButton menuButton = new JButton(menu_icon);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                frame.setVisible(false);
                new MenuPresenter();
                
            }
        });
        menuButton.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+120, 199, 50);
        imageLabel.add(menuButton);
        
        ImageIcon exit_icon = new ImageIcon("lib\\img\\exit_button.png");
        JButton exitButton = new JButton(exit_icon);
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
