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
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            
            JButton playButton = new JButton("Play Pacman");
            playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    frame.setVisible(false);
                    new MazeSelection();
                }
            });
            buttonPanel.add(playButton);
            
            JButton savedGameButton = new JButton("Saved Game");
            savedGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            savedGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0){
                    // Code to view high scores goes here
                    frame.setVisible(false);
                    new SettingPresenter();
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
            
            frame.add(buttonPanel);
            frame.setVisible(true);
    }
}
