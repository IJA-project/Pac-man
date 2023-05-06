package src;

import javax.swing.*;
import java.awt.event.*;
public class MessageDialog extends JFrame implements ActionListener {
    public MessageDialog()
    {
        JLabel label = new JLabel("Please, select 1 option in every category");
        JButton button = new JButton("OK");
        button.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(button);
        this.add(panel);
        setSize(300, 200);
        this.setVisible(true);
 
    }
 
    // if button is pressed
    public void actionPerformed(ActionEvent evt)
    {
        this.setVisible(false);
    }
 
}