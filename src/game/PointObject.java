/**
 * Project name: Pac-man
 * File name: PointObject.java
 * Date: 06.05.2023
 * Last update: 04.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: PointObject class represents point object in maze
 */

package game;

import tool.common.CommonField;
import tool.common.CommonMazeObject;

/**
 * PointObject class represents point object in maze
 */
public class PointObject implements CommonMazeObject {
    /**
     * Attribute representing field where point is
     */
    private CommonField field;

    /**
     * Constructor for PointObject
     * 
     * @param field field where point is
     */
    public PointObject(CommonField field) {
        this.field = field;
    }

    /**
     * Override CommonMazeObject method for get true if object is point
     * 
     * @return always true
     */
    @Override
    public boolean isPoint() {
        return true;
    }

    /**
     * Override CommonMazeObject method for get current field where point is
     * 
     * @return field where point is
     */
    @Override
    public CommonField getField() {
        return this.field;
    }

}
