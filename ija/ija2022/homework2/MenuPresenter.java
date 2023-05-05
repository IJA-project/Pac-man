package ija.ija2022.homework2;

import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.view.FieldView;
import ija.ija2022.homework2.game.GhostObject;
import ija.ija2022.homework2.game.MazeConfigure;
import ija.ija2022.homework2.game.PacmanObject;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;

public class MenuPresenter{

    public MenuPresenter(){
            JFrame frame = new JFrame();
            ImageIcon imageIcon = new ImageIcon("img\\menu_white.png");
            //System.out.println(imageIcon.getIconWidth());
            //System.out.println(imageIcon.getIconHeight());
            frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            
            Image image = imageIcon.getImage();
            // Create a label with the image
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight()); // Set the position and size of the label
    
            ImageIcon play_icon = new ImageIcon("img\\play_button.png");

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
            
            ImageIcon load_icon = new ImageIcon("img\\load_button.png");
            JButton savedGameButton = new JButton(load_icon);
            savedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            savedGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Code to view high scores goes here
                    frame.setVisible(false);
                    new SettingPresenter();
                }
            });
            savedGameButton.setBounds((imageIcon.getIconWidth()-200)/2, imageIcon.getIconHeight()/2+120, 199, 50);
            imageLabel.add(savedGameButton);
            
            ImageIcon exit_icon = new ImageIcon("img\\exit_button.png");
            JButton exitButton = new JButton(exit_icon);
            exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
