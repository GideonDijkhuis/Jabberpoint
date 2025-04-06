package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

public class PrevSlideCommand implements Command
{
    private final NavigationReceiver receiver;

    public PrevSlideCommand(NavigationReceiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        receiver.prevSlide();
    }
} 