/**
 * Project name: Pac-man
 * File name: PacmanView.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: PacmanView class represents the graphical view of a pacman component.
 */

package tool.view;

import tool.common.CommonMazeObject;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The PacmanView class represents the graphical view of a pacman component.
 */
public class PacmanView implements ComponentView {
    /** The field of the pacman.*/
    private FieldView parent;

    /**
     * Constructor for a PacmanView object.
     * 
     * @param parent The parent FieldView of the pacman view.
     * @param m CommonMazeObject representing the pacman model (not used).
     */
    public PacmanView(FieldView parent, CommonMazeObject m) {
        this.parent = parent;
    }

    /**
     * Renders the graphical representation of the pacman component into the graphics context g.
     * 
     * @param g The graphics context.
     */
    @Override
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
        Image pacman_img = new ImageIcon("lib\\img\\pacman.png").getImage();
        g.drawImage(pacman_img, (int) x, (int) y, scaledWidth, scaledHeight, this.parent);
    }
}
