/**
 * Project name: Pac-man
 * File name: MessageDialog.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description:  MessageDialog class display a message screen in a game 
 *               when player chose wrong combination of buttons in logging settings.
 */

package src;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

/**
 * MessageDialog class display a message screen in a game 
 * when player chose wrong combination of buttons in logging settings.
 */
public class MessageDialog extends JFrame implements ActionListener {
    /**
     * Constructor for MessageDialog
     */
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
    /**
    * Hides the message screen when player click on the button OK.
    * @param evt The ActionEvent object representing click on the button.
    */
    public void actionPerformed(ActionEvent evt)
    {
        this.setVisible(false);
    }
 
}