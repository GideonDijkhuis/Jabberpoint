package main.java.com.jabberpoint.model.observer;

public interface Subject
{
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

    void notifyObservers(Object data);
} 