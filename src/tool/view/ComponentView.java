/**
 * Project name: Pac-man
 * File name: ComponentView.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: ComponentView interface represents objects that can be placed under a FieldView.
 */

 package tool.view;

import java.awt.Graphics;

/**
 * The ComponentView interface represents objects that can be placed under a FieldView.
 */
public interface ComponentView {
    /**
     * Renders the graphical representation of the object into the graphics context g.
     * 
     * @param g The graphics context.
     */
    void paintComponent(Graphics g);
}
