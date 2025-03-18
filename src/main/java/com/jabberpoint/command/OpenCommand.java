package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

/**
 * <p>main.java.com.jabberpoint.command.OpenCommand implements the main.java.com.jabberpoint.command.Command interface to open a presentation file</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class OpenCommand implements Command {
    private FileReceiver receiver;
    private String filename;
    
    /**
     * Constructor for main.java.com.jabberpoint.command.OpenCommand
     * @param receiver The receiver that will handle the file operation
     * @param filename The filename to open
     */
    public OpenCommand(FileReceiver receiver, String filename) {
        this.receiver = receiver;
        this.filename = filename;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.openPresentation(filename);
    }
} 