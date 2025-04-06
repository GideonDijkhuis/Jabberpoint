package main.java.com.jabberpoint.factory;

import main.java.com.jabberpoint.command.*;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.receiver.*;

import java.awt.Frame;

public class CommandFactory
{
    private static CommandFactory instance;
    private Presentation presentation;
    private Frame frame;

    // Receivers
    private NavigationReceiver navigationReceiver;
    private FileReceiver fileReceiver;
    private ApplicationReceiver applicationReceiver;

    private CommandFactory()
    {
        this.presentation = Presentation.getInstance();
    }

    public static CommandFactory getInstance()
    {
        if (instance == null)
        {
            instance = new CommandFactory();
        }
        return instance;
    }

    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
        this.initializeReceivers();
    }

    public void setFrame(Frame frame)
    {
        this.frame = frame;
        this.initializeReceivers();
    }

    private void initializeReceivers()
    {
        if (presentation != null)
        {
            navigationReceiver = new NavigationReceiver();

            if (frame != null)
            {
                fileReceiver = new FileReceiver(presentation, frame);
                applicationReceiver = new ApplicationReceiver(frame);
            }
        }
    }

    public Command createNextSlideCommand()
    {
        return new NextSlideCommand(navigationReceiver);
    }

    public Command createPrevSlideCommand()
    {
        return new PrevSlideCommand(navigationReceiver);
    }

    public Command createExitCommand()
    {
        return new ExitCommand(applicationReceiver);
    }

    public Command createOpenCommand()
    {
        return new OpenCommand(fileReceiver);
    }

    public Command createSaveCommand(String filename)
    {
        return new SaveCommand(fileReceiver, filename);
    }

    public Command createNewCommand()
    {
        return new NewCommand(fileReceiver);
    }

    public Command createAboutCommand()
    {
        return new AboutCommand(applicationReceiver);
    }

    public Command createGotoCommand(String prompt)
    {
        return new GotoCommand(navigationReceiver, frame, prompt);
    }
} 