/**
 * Project name: Pac-man
 * File name: PointView.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: PointView class represents the graphical view of a point component.
 */

package tool.view;

import tool.common.CommonMazeObject;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import java.awt.*;

/**
 * The PointView class represents the graphical view of a point component.
 */
public class PointView implements ComponentView {
    /** The field of the point.*/
    private final FieldView parent;

    /**
     * Constructor for a PointView object.
     * 
     * @param parent The parent FieldView of the point view.
     * @param m CommonMazeObject representing the point model (not used).
     */
    public PointView(FieldView parent, CommonMazeObject m) {
        this.parent = parent;
    }

    /**
     * Renders the graphical representation of the point component into the graphics context g.
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
        Image pacman_img = new ImageIcon("lib\\img\\cherrypoint.png").getImage();
        g.drawImage(pacman_img, (int) x, (int) y, scaledWidth, scaledHeight, this.parent);
    }
}
