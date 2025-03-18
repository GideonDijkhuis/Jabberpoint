 package main.java.com.jabberpoint.model.observer;

/**
 * Subject interface for the Observer pattern.
 * Classes that implement this interface can be observed by Observer objects.
 * @author Bram Huiskes
 * @version 1.0 2025/03/18
 */
public interface Subject {
    /**
     * Register an observer to receive updates.
     * 
     * @param observer The observer to register
     */
    void registerObserver(Observer observer);
    
    /**
     * Remove an observer from the notification list.
     * 
     * @param observer The observer to remove
     */
    void removeObserver(Observer observer);
    
    /**
     * Notify all registered observers when a change occurs.
     */
    void notifyObservers();
    
    /**
     * Notify all registered observers with specific data.
     * 
     * @param data Additional data to send to observers
     */
    void notifyObservers(Object data);
} 