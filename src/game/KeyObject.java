/**
 * Project name: Pac-man
 * File name: KeyObject.java
 * Date: 06.05.2023
 * Last update: 06.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: KeyObject class represents key object in maze
 */


package src.game;

import src.tool.common.CommonField;
import src.tool.common.CommonMazeObject;

/**
 * KeyObject class represents key object in maze
 */
public class KeyObject implements CommonMazeObject {
    private CommonField field;
    /**
     * Constructor for KeyObject
     * @param field field where key is
     */
    public KeyObject(CommonField field){
        this.field = field;
    }

    /**
     * Implement method from CommonMazeObject to check if object is key
     * @return true, because object is key
     */
    public boolean isKey(){
        return true;
    }

    /**
     * Implement method from CommonMazeObject, get current field where key is
     * @return field where key is
     */
    @Override
    public CommonField getField() {
        return field;
    }

}
