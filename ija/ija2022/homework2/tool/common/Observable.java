package ija.ija2022.homework2.tool.common;

public interface Observable {
    public void addObserver(Observable.Observer o);
    public void notifyObservers();

    public void removeObserver(Observable.Observer o);

    public static interface Observer {
        void update(Observable o);
    }
}
