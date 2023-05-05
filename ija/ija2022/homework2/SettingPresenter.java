package ija.ija2022.homework2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class SettingPresenter extends JFrame implements ActionListener {
    private JToggleButton toggleButton1;
    private JToggleButton toggleButton2;
    private JToggleButton toggleButton3;
    private JToggleButton toggleButton4;
    private JLabel label1, label2, label3, label4;
    private JButton button;
    private boolean count1 = false;
    private boolean count2 = false;
    private boolean count3 = false;
    private boolean count4 = false;

    public SettingPresenter() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // Create the switch buttons
        toggleButton1 = new JToggleButton("OFF");
        toggleButton2 = new JToggleButton("OFF");
        toggleButton3 = new JToggleButton("OFF");
        toggleButton4 = new JToggleButton("OFF");
        // Add action listeners to the buttons
        toggleButton1.addActionListener(this);
        toggleButton2.addActionListener(this);
        toggleButton3.addActionListener(this);
        toggleButton4.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Load Save"));
        panel.add(toggleButton1);

        panel.add(new JLabel("Load reverse Save"));
        panel.add(toggleButton2);

        panel.add(new JLabel("Run"));
        panel.add(toggleButton3);

        panel.add(new JLabel("Buttons"));
        panel.add(toggleButton4);

        getContentPane().add(panel, BorderLayout.CENTER);

        // Create a button for testing
        button = new JButton("Test");
        button.addActionListener(this);
        getContentPane().add(button, BorderLayout.SOUTH);

        // Set the size and visibility of the frame
        setSize(300, 200);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        // Get the state of the button that triggered the event
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton source = (JToggleButton) e.getSource();
            boolean on = source.isSelected();
    
            // Update the text of the button that triggered the event
            if (on) {
                source.setText("ON");
            } else {
                source.setText("OFF");
            }
            if (source == toggleButton1) {
                if (on) {
                    count1 = true;
                } else {
                    count1 = false;
                }
            } else if (source == toggleButton2) {
                // Do something based on the state of the button
                if (on) {
                    count2 = true;
                } else {
                    count2 = false;
                }
            } else if (source == toggleButton3) {
                // Do something based on the state of the button
                if (on) {
                    count3 = true;
                } else {
                    count3 = false;
                }
            } else if (source == toggleButton4) {
                // Do something based on the state of the button
                if (on) {
                    count4 = true;
                } else {
                    count4 = false;
                }
            }
        }
        if (e.getSource() == button) {
            // System.out.println(count1);
            // System.out.println(count2);
            // System.out.println(count3);
            // System.out.println(count4);
            // Check if at least 1 switch every category is selected
            if ((count1 && count2) || (count3 && count4) || (!count1 && !count2) || (!count3 && !count4)) {
              new MessageDialog();

            } else {
                //////
                if (count1){
                    if(count3){
                        PacmanGame pacmanGame = new PacmanGame(2, 1, "");
                    }
                    if(count4){
                        PacmanGame pacmanGame = new PacmanGame(2, 2, "");
                    }
                    
                }
                if(count2){
                    if(count3){
                        PacmanGame pacmanGame = new PacmanGame(3, 1, "");
                    }
                    if(count4){
                        PacmanGame pacmanGame = new PacmanGame(3, 2 ,"");
                    }
                }
                this.setVisible(false);

            }
            
        }
    }
  
}
