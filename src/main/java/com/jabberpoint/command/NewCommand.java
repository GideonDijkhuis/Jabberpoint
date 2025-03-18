package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

/**
 * <p>main.java.com.jabberpoint.command.NewCommand implements the main.java.com.jabberpoint.command.Command interface to create a new presentation</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class NewCommand implements Command {
    private FileReceiver receiver;
    
    /**
     * Constructor for main.java.com.jabberpoint.command.NewCommand
     * @param receiver The receiver that will handle the operation
     */
    public NewCommand(FileReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.newPresentation();
    }
} 