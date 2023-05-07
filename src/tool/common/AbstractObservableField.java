/**
 * Project name: Pac-man
 * File name: AbstractObservableField.java
 * Date: 06.05.2023
 * Last update: 01.05.2023
 * Author: Andrei Kulinkovich(xkulin01)
 * Description: Abstract class implementing Observable interface operations. Allows you to insert and cancel observers and notify registered observers of changes.
 */

package tool.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class implementing Observable interface operations. Allows you to
 * insert and cancel observers and notify registered observers of changes.
 */
public abstract class AbstractObservableField implements CommonField {
    private final Set<Observable.Observer> observers = new HashSet();

    /**
     * Registers a new observer.
     * 
     * @param o observer to be registered
     */
    public void addObserver(Observable.Observer o) {
        this.observers.add(o);
    }

    /**
     * Removes the observer.
     * 
     * @param o observer to be removed from the list of registered observers
     */
    public void removeObserver(Observable.Observer o) {
        this.observers.remove(o);
    }

    /**
     * Notifies all registered observers of the change.
     */
    public void notifyObservers() {
        this.observers.forEach((o) -> {
            o.update(this);
        });
    }
}
