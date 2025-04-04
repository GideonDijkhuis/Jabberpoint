package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

/**
 * <p>main.java.com.jabberpoint.command.ExitCommand implements the main.java.com.jabberpoint.command.Command interface
 * to exit the application</p>
 *
 * @author Bram Huiskes
 * @version 1.0
 */
public class ExitCommand implements Command
{
    private final ApplicationReceiver receiver;

    /**
     * Constructor for main.java.com.jabberpoint.command.ExitCommand
     *
     * @param receiver The receiver that will handle the exit operation
     */
    public ExitCommand(ApplicationReceiver receiver)
    {
        this.receiver = receiver;
    }

    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute()
    {
        receiver.exit();
    }
} 