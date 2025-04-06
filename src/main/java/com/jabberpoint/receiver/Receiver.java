package main.java.com.jabberpoint.receiver;

/**
 * Marker interface for all receivers in the command pattern.
 *
 * SOLID Principles: - Single Responsibility Principle: Defines a single role - being a receiver in the command pattern.
 * - Open/Closed Principle: Allows for extension with new receiver implementations without modification. - Interface
 * Segregation Principle: Provides a minimal interface that doesn't force implementations to include unnecessary
 * methods. - Dependency Inversion Principle: Serves as an abstraction that concrete classes can depend on.
 */
public interface Receiver {
    //Empty interface, because this receiver doesn't require classes.
} 