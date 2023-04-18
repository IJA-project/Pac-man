package ija.ija2022.homework2.tool.view;

import java.awt.Graphics;

/**
 * Rozhrani objektu, ktere mohou byt zarazeny pod FieldView.
 */
public interface ComponentView {
    /**
     * Vykreslí grafickou podobu objektu do grafického kontextu g.
     * 
     * @param g
     */
    void paintComponent(Graphics g);
}
