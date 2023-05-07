/**
 * Project name: Pac-man
 * File name: FieldView.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Zdebska Kateryna(xzdebs00)
 * Description: FieldView class represents the view for displaying 
 *              the field and models on it in a graphical user interface.
 */

package tool.view;

import game.TargetField;
import game.WallField;
import tool.MazePresenter;
import tool.common.CommonField;
import tool.common.CommonMazeObject;
import tool.common.Observable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import java.awt.*;

/**
 * FieldView class represents the view for displaying the field in a graphical user interface.
 */
public class FieldView extends JPanel implements Observable.Observer {
    
    /** Attribute representing model of field */
    private final CommonField model;
    /** Attribute representing objects placed on current field. */
    private final List<ComponentView> objects;
    /** Attribute representing number of model changes. */
    private int changedModel = 0;
    /** Attribute representing object for the maze graphical user interface. */
    private MazePresenter presenter;

    /**
     * Constructor for a FieldView object.
     * 
     * @param model The CommonField object representing the model of field.
     * @param presenter The MazePresenter object for the maze graphical user interface.
     */
    public FieldView(CommonField model, MazePresenter presenter) {
        this.model = model;
        this.objects = new ArrayList();
        this.privUpdate();
        this.presenter = presenter;
        model.addObserver(this);
    }

    /**
     * Method to paint the field and models on it.
     * 
     * @param g The Graphics object used for painting.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle bounds = this.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        int scaledWidth = (int) diameter;
        int scaledHeight = (int) diameter;
        if (this.model instanceof TargetField) {
            Image door_img = new ImageIcon("lib\\img\\door.png").getImage();
            g.drawImage(door_img, (int) x, (int) y, scaledWidth, scaledHeight, this);
        } else if (this.model instanceof WallField) {
            Image wall_img = new ImageIcon("lib\\img\\wallbrick.png").getImage();
            g.drawImage(wall_img, 0, 0, (int) w, (int) h, this);
        }
        List<ComponentView> copyObjects = new ArrayList<>(this.objects);
        for (ComponentView v : copyObjects) {
            v.paintComponent(g);
        }
    }

    /**
     * Update the view based on the model state.
     */
    private void privUpdate() {
        this.setBackground(Color.black);
        if (this.model.canMove()) {
            if (!this.model.isEmpty()) {
                CommonMazeObject o = this.model.get();
                ComponentView v = o.isPacman() ? new PacmanView(this, this.model.get())
                        : o.isPoint() ? new PointView(this, this.model.get())
                                : o.isKey() ? new KeyView(this, this.model.get())
                                        : new GhostView(this, this.model.get());
                this.objects.add(v);
            } else {
                this.objects.clear();
            }
            this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        }
    }

    /**
     * Update method called when the observer captured field changes.
     * 
     * @param field The observed field.
     */
    public final void update(Observable field) {
        ++this.changedModel;
        this.privUpdate();
        this.presenter.gameOver();
    }

    /**
     * Gets the number of updates made to the field view.
     * 
     * @return The number of updates.
     */
    public int numberUpdates() {
        return this.changedModel;
    }

    /**
     * Clears the counter for model changes.
     */
    public void clearChanged() {
        this.changedModel = 0;
    }

    /**
     * Gets the CommonField object representing the field model.
     * 
     * @return The CommonField object.
     */
    public CommonField getField() {
        return this.model;
    }
}
