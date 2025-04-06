package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.JabberPoint;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.ui.*;

import java.awt.Frame;

public class ApplicationReceiver implements Receiver
{
    private final JabberPoint jabberPoint;
    private final Frame frame;

    public ApplicationReceiver(Frame frame)
    {
        this.jabberPoint = JabberPoint.getInstance();
        this.frame = frame;
    }

    public void exit()
    {
        this.jabberPoint.exit(0);
    }

    public void showAbout()
    {
        AboutBox.show(frame);
    }
} 