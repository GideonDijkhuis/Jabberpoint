package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.ApplicationReceiver;

/**
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Only responsible for executing the about command
 * - Open/Closed Principle: Can be extended without modification
 * - Liskov Substitution Principle: Properly implements the Command interface
 * - Interface Segregation Principle: Uses only required methods from Command
 * - Dependency Inversion Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to show the about dialog.
 */
public class AboutCommand implements Command
{
    private final ApplicationReceiver receiver;

    /**
     * Creates a new about command.
     *
     * @param receiver The application receiver to handle the command
     */
    public AboutCommand(ApplicationReceiver receiver)
    {
        this.receiver = receiver;
    }

    /**
     * Executes the about command to show the about dialog.
     */
    @Override
    public void execute()
    {
        this.receiver.showAbout();
    }
} 