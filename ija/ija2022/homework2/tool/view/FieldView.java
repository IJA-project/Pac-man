//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ija.ija2022.homework2.tool.view;

import ija.ija2022.homework2.game.TargetField;
import ija.ija2022.homework2.game.WallField;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.Observable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import java.awt.*;

public class FieldView extends JPanel implements Observable.Observer {
    private final CommonField model;
    private final List<ComponentView> objects;
    private int changedModel = 0;

    public FieldView(CommonField model) {
        this.model = model;
        this.objects = new ArrayList();
        this.privUpdate();
        model.addObserver(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle bounds = this.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        int scaledWidth = (int)diameter;
        int scaledHeight = (int)diameter;
        if(this.model instanceof TargetField){
            Image door_img = new ImageIcon("img\\door.png").getImage();
            g.drawImage(door_img, (int)x, (int)y, scaledWidth, scaledHeight, this);

        }else if (this.model instanceof WallField){
            Image wall_img = new ImageIcon("img\\wallbrick.png").getImage();
            g.drawImage(wall_img, 0, 0, (int)w, (int)h, this);
        }
        this.objects.forEach((v) -> {
            v.paintComponent(g);
        });
    }

    private void privUpdate() {
        this.setBackground(Color.black);
        if (this.model.canMove()) {
            if (!this.model.isEmpty()) {
                CommonMazeObject o = this.model.get();
                ComponentView v = o.isPacman() ? new PacmanView(this, this.model.get()) : o.isPoint() ? new PointView(this, this.model.get()) : o.isKey() ? new KeyView(this, this.model.get()) :new GhostView(this, this.model.get());
                this.objects.add(v);
            } else {
                this.objects.clear();
            }
            this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        }else{
        }
        //this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        
    }

    public final void update(Observable field) {
        ++this.changedModel;
        this.privUpdate();
    }

    public int numberUpdates() {
        return this.changedModel;
    }

    public void clearChanged() {
        this.changedModel = 0;
    }

    public CommonField getField() {
        return this.model;
    }
}
