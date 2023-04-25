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

public class MenuPresenter{
    
    
    public int button_pressed = 0;
    public final JPanel frameContainer = new JPanel();

    public MenuPresenter(){
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton playButton = new JButton("Play Pacman");
        // Create the button
        frame.add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0){
                frame.setVisible(false);
                PacmanGame pacmanGame = new PacmanGame(false);
            }
        });

        // JButton loadButton = new JButton("Load Pacman");
        // frame.add(loadButton);aaa
        // loadButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent arg0){
        //         frame.setVisible(false);
        //         PacmanGame pacmanGame = new PacmanGame(false);
        //     }
        // });
        

        // Show the frame
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();

    }
}
