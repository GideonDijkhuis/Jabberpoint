package main.java.com.jabberpoint.command;

/**
 * <p>main.java.com.jabberpoint.command.Command interface for the main.java.com.jabberpoint.command.Command pattern</p>
 * <p>This interface defines the execute method that all concrete commands must implement.</p>
 * <p>Commands delegate the actual work to a receiver.</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public interface Command {
    /**
     * Execute the command by delegating to the receiver
     */
    void execute();
} 