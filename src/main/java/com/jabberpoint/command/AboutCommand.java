package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

public class AboutCommand implements Command
{
    private final ApplicationReceiver receiver;

    public AboutCommand(ApplicationReceiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.showAbout();
    }
} 