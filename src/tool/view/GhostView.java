//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package src.tool.view;

import src.tool.common.CommonMazeObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import java.awt.*;

public class GhostView implements ComponentView {
    private final CommonMazeObject model;
    private final FieldView parent;

    public GhostView(FieldView parent, CommonMazeObject m) {
        this.model = m;
        this.parent = parent;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle bounds = this.parent.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        int scaledWidth = (int)diameter;
        int scaledHeight = (int)diameter;
        Image pacman_img = new ImageIcon("data\\img\\redghost.png").getImage();
        g.drawImage(pacman_img, (int)x, (int)y, scaledWidth, scaledHeight, this.parent);
    }
}
