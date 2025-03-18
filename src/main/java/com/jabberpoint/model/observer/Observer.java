package main.java.com.jabberpoint.model.observer;

/**
 * Observer interface for the Observer pattern.
 * Classes that implement this interface can receive updates from Subject objects.heb o
 */
public interface Observer {
    /**
     * Update method called by the Subject when a change occurs.
     * 
     * @param subject The Subject that has changed
     * @param data Any additional data about the change
     */
    void update(Object subject, Object data);
} 