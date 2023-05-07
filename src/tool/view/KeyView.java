/**
 * Project name: Pac-man
 * File name: KeyView.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: KeyView class represents the graphical view of a key component.
 */

package tool.view;

import tool.common.CommonMazeObject;

import java.awt.*;

import javax.swing.ImageIcon;

/**
 * The KeyView class represents the graphical view of a key component.
 */
public class KeyView implements ComponentView {
    /** The field of the key.*/
    private final FieldView parent;

    /**
     * Constructor for a KeyView object.
     * 
     * @param parent The parent FieldView of the key view.
     * @param m CommonMazeObject representing the key model (not used).
     */
    public KeyView(FieldView parent, CommonMazeObject m) {
        this.parent = parent;
    }

    /**
     * Renders the graphical representation of the key component into the graphics context g.
     * 
     * @param g The graphics context.
     */
    public void paintComponent(Graphics g) {
        Rectangle bounds = this.parent.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        int scaledWidth = (int) diameter;
        int scaledHeight = (int) diameter;
        Image pacman_img = new ImageIcon("lib\\img\\key.png").getImage();
        g.drawImage(pacman_img, (int) x, (int) y, scaledWidth, scaledHeight, this.parent);
    }
}
