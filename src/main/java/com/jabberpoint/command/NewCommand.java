package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.FileReceiver;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for creating a new presentation -
 * Open/Closed Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements the
 * Command interface - Interface Segregation Principle: Uses only required methods from Command - Dependency Inversion
 * Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to create a new presentation.
 */
public class NewCommand implements Command {
    private final FileReceiver receiver;

    /**
     * Creates a new command for creating a new presentation.
     *
     * @param receiver The file receiver to handle the command
     */
    public NewCommand(FileReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the command to create a new presentation.
     */
    @Override
    public void execute() {
        this.receiver.newPresentation();
    }
} 