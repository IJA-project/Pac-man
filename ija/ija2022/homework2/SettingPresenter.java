package ija.ija2022.homework2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

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
        this.setSize(1200, 700);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // Create the switch buttons
        ImageIcon off_icon = new ImageIcon("img\\off.png");
        toggleButton1 = new JToggleButton(off_icon);
        toggleButton2 = new JToggleButton(off_icon);
        toggleButton3 = new JToggleButton(off_icon);
        toggleButton4 = new JToggleButton(off_icon);
        // Add action listeners to the buttons
        toggleButton1.addActionListener(this);
        toggleButton2.addActionListener(this);
        toggleButton3.addActionListener(this);
        toggleButton4.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(700, 700));

        JPanel instructioPanel = new JPanel();
        instructioPanel.setPreferredSize(new Dimension(300, 100));
        instructioPanel.setBackground(Color.BLACK);
        JLabel instructionLabel = new JLabel();
        instructionLabel.setBackground(Color.BLACK);
        instructionLabel.setForeground(Color.yellow);
        instructionLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        instructionLabel.setText("Please, select a mode to load your game");
        instructioPanel.add(instructionLabel, BorderLayout.CENTER);
        mainPanel.add(instructioPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 250));
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(4, 2));

        JLabel label1 = new JLabel("Load Save");
        label1.setForeground(Color.yellow);
        label1.setFont(new Font("Bauhaus 93", Font.BOLD, 33));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label1);

        JLabel label2 = new JLabel("Load reverse Save");
        label2.setForeground(Color.yellow);
        label2.setFont(new Font("Bauhaus 93", Font.BOLD, 33));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label2);

        panel.add(toggleButton1);
        panel.add(toggleButton2);

        JLabel label3 = new JLabel("Run permanent");
        label3.setForeground(Color.yellow);
        label3.setFont(new Font("Bauhaus 93", Font.BOLD, 33));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label3);

        JLabel label4 = new JLabel("Run using buttons");
        label4.setForeground(Color.yellow);
        label4.setFont(new Font("Bauhaus 93", Font.BOLD, 33));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label4);

        panel.add(toggleButton3);
        panel.add(toggleButton4);

        mainPanel.add(panel, BorderLayout.CENTER);

        // Create a button for testing
        JPanel button_panel = new JPanel();
        button_panel.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        button_panel.setBackground(Color.BLACK);
        ImageIcon run_icon = new ImageIcon("img\\run_bttn.png");
        button = new JButton(run_icon);
        button.setPreferredSize(new Dimension(300, 100));
        button.setBounds(600, 100, 99, 25 );
        button.addActionListener(this);
        button_panel.add(button);

        mainPanel.add(button_panel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        //add(button);
        // Set the size and visibility of the frame
        this.setFocusable(true);
        this.requestFocusInWindow();
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        // Get the state of the button that triggered the event
        if (e.getSource() instanceof JToggleButton) {
            JToggleButton source = (JToggleButton) e.getSource();
            boolean on = source.isSelected();
    
            // Update the text of the button that triggered the event
            if (on) {
                ImageIcon on_icon = new ImageIcon("img\\on_button.png");
                source.setIcon(on_icon);
            } else {
                ImageIcon off_icon = new ImageIcon("img\\off.png");
                source.setIcon(off_icon);
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
