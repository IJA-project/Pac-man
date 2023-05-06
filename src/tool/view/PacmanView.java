//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package src.tool.view;

import src.tool.common.CommonMazeObject;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PacmanView implements ComponentView {
    private CommonMazeObject model;
    private FieldView parent;

    public PacmanView(FieldView parent, CommonMazeObject m) {
        this.model = m;
        this.parent = parent;
    }

    @Override
    public void paintComponent(Graphics g) {
        Rectangle bounds = this.parent.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        int scaledWidth = (int)diameter;
        int scaledHeight = (int)diameter;
        Image pacman_img = new ImageIcon("lib\\img\\pacman.png").getImage();
        g.drawImage(pacman_img, (int)x, (int)y, scaledWidth, scaledHeight, this.parent);
    }
}
