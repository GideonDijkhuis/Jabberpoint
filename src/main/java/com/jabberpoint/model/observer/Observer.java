package main.java.com.jabberpoint.model.observer;

/**
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Only responsibility is to receive updates
 * - Interface Segregation Principle: Provides only necessary update method
 * - Dependency Inversion Principle: High-level modules depend on this abstraction
 * - Open/Closed Principle: New observers can be added without modifying existing code
 *
 * Interface for the Observer in the Observer pattern.
 */
public interface Observer
{
    /**
     * Called when the observed subject changes.
     * 
     * @param subject The subject that changed
     * @param data Additional data about the change
     */
    void update(Object subject, Object data);
} 