package main.java.com.jabberpoint.model.observer;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for managing observers and
 * notifications - Open/Closed Principle: Can be extended with new notification behaviors without modification -
 * Interface Segregation Principle: Defines only methods necessary for subject behavior - Dependency Inversion
 * Principle: High-level modules depend on this abstraction - Liskov Substitution Principle: All implementations should
 * be substitutable without affecting behavior
 *
 * Interface for the Subject in the Observer pattern.
 */
public interface Subject {
    /**
     * Registers an observer to receive updates.
     *
     * @param observer The observer to register
     */
    void registerObserver(Observer observer);

    /**
     * Removes an observer so it no longer receives updates.
     *
     * @param observer The observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers of a change.
     */
    void notifyObservers();

    /**
     * Notifies all registered observers of a change with specific data.
     *
     * @param data Additional data about the change
     */
    void notifyObservers(Object data);
} 