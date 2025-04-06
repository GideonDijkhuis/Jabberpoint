package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

public class ExitCommand implements Command
{
    private final ApplicationReceiver receiver;

    public ExitCommand(ApplicationReceiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.exit();
    }
} 