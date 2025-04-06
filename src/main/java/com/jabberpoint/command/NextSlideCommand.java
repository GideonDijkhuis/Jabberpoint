package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.NavigationReceiver;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for navigating to the next slide -
 * Open/Closed Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements the
 * Command interface - Interface Segregation Principle: Uses only required methods from Command - Dependency Inversion
 * Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to navigate to the next slide.
 */
public class NextSlideCommand implements Command {
    private final NavigationReceiver receiver;

    /**
     * Creates a new command for navigating to the next slide.
     *
     * @param receiver The navigation receiver to handle the command
     */
    public NextSlideCommand(NavigationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the command to navigate to the next slide.
     */
    @Override
    public void execute() {
        this.receiver.nextSlide();
    }
} 