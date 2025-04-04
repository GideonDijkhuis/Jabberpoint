package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

/**
 * <p>main.java.com.jabberpoint.command.NextSlideCommand implements the main.java.com.jabberpoint.command.Command interface to navigate to the next slide</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class NextSlideCommand implements Command {
    private final NavigationReceiver receiver;

    /**
     * Constructor for main.java.com.jabberpoint.command.NextSlideCommand
     * @param receiver The receiver that will handle the navigation
     */
    public NextSlideCommand(NavigationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.nextSlide();
    }
} 