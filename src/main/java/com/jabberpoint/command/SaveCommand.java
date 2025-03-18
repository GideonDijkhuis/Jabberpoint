package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

/**
 * <p>main.java.com.jabberpoint.command.SaveCommand implements the main.java.com.jabberpoint.command.Command interface to save a presentation file</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class SaveCommand implements Command {
    private FileReceiver receiver;
    private String filename;
    
    /**
     * Constructor for main.java.com.jabberpoint.command.SaveCommand
     * @param receiver The receiver that will handle the file operation
     * @param filename The filename to save to
     */
    public SaveCommand(FileReceiver receiver, String filename) {
        this.receiver = receiver;
        this.filename = filename;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.savePresentation(filename);
    }
} 