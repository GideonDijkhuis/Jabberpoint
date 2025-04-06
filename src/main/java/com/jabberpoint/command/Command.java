package main.java.com.jabberpoint.command;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Each command has one responsibility - to execute an
 * action - Open/Closed Principle: New commands can be added without modifying existing ones - Interface Segregation
 * Principle: Interface contains only necessary methods - Dependency Inversion Principle: High-level modules depend on
 * this abstraction
 *
 * Command interface for the Command pattern.
 */
public interface Command {
    /**
     * Executes the command.
     */
    void execute();
} 