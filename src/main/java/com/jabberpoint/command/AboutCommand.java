package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

/**
 * <p>main.java.com.jabberpoint.command.AboutCommand implements the main.java.com.jabberpoint.command.Command interface to show the about box</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class AboutCommand implements Command {
    private ApplicationReceiver receiver;
    
    /**
     * Constructor for main.java.com.jabberpoint.command.AboutCommand
     * @param receiver The receiver that will handle showing the about box
     */
    public AboutCommand(ApplicationReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.showAbout();
    }
} 