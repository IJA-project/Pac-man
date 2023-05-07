/**
 * Project name: Pac-man
 * File name: Observable.java
 * Date: 06.05.2023
 * Last update: 01.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: An Observable interface representing objects that can notify dependent objects (observers) of changes. you can insert Mazeobjects.
 */
package tool.common;

/**
 * An Observable interface representing objects that can notify dependent
 * objects (observers) of changes. you can insert Mazeobjects.
 */
public interface Observable {
    /**
     * Registers a new observer.
     * 
     * @param o observer to be registered
     */
    public void addObserver(Observable.Observer o);

    /**
     * Notifies all registered observers of the change.
     */
    public void notifyObservers();

    /**
     * Removes the observer.
     * 
     * @param o observer to be removed from the list of registered observers
     */
    public void removeObserver(Observable.Observer o);

    /**
     * Observer interface for objects that need to be notified of changes in
     * observable objects.
     */
    public static interface Observer {
        void update(Observable o);
    }
}
