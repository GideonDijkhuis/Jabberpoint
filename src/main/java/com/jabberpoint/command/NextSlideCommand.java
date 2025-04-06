package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

public class NextSlideCommand implements Command
{
    private final NavigationReceiver receiver;

    public NextSlideCommand(NavigationReceiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.nextSlide();
    }
} 