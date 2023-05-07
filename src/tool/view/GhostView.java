/**
 * Project name: Pac-man
 * File name: GhostView.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: GhostView class represents the graphical view of a ghost component.
 */

package tool.view;

import tool.common.CommonMazeObject;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import java.awt.*;

/**
 * The GhostView class represents the graphical view of a ghost component.
 */
public class GhostView implements ComponentView {
    /** The field of the ghost.*/
    private final FieldView parent;

    /**
     * Constructor for a GhostView object.
     * 
     * @param parent The parent FieldView of the ghost view.
     * @param m CommonMazeObject representing the ghost model (not used).
     */
    public GhostView(FieldView parent, CommonMazeObject m) {
        this.parent = parent;
    }

    /**
     * Renders the graphical representation of the ghost component into the graphics context g.
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
        Image pacman_img = new ImageIcon("lib\\img\\redghost.png").getImage();
        g.drawImage(pacman_img, (int) x, (int) y, scaledWidth, scaledHeight, this.parent);
    }
}
