package src;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
public class MessageDialog extends JFrame implements ActionListener {
    public MessageDialog()
    {
        this.setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        JLabel label = new JLabel("Please, select only one option in each category");
        label.setForeground(Color.white);
        label.setFont(new Font("Century Gothic", Font.BOLD, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        JButton button = new JButton("OK");
        button.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.add(label);
        panel.add(button);
        this.add(panel);
        setSize(400, 100);
        this.setFocusable(true);
        this.setVisible(true);
 
    }
 
    // if button is pressed
    public void actionPerformed(ActionEvent evt)
    {
        this.setVisible(false);
    }
 
}