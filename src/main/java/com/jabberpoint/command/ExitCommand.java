package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.ApplicationReceiver;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for executing the exit command -
 * Open/Closed Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements the
 * Command interface - Interface Segregation Principle: Uses only required methods from Command - Dependency Inversion
 * Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to exit the application.
 */
public class ExitCommand implements Command {
    private final ApplicationReceiver receiver;

    /**
     * Creates a new exit command.
     *
     * @param receiver The application receiver to handle the command
     */
    public ExitCommand(ApplicationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the exit command to close the application.
     */
    @Override
    public void execute() {
        this.receiver.exit();
    }
} 