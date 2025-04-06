package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

public class NewCommand implements Command
{
    private final FileReceiver receiver;

    public NewCommand(FileReceiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.newPresentation();
    }
} 