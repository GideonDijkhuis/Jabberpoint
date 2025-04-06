package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.accessor.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;

import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Frame;

public class FileReceiver implements Receiver
{
    private final Presentation presentation;
    private final Frame frame;
    private final AccessorFactory accessorFactory;

    // Error messages
    private static final String IOEX = "IO Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String SAVEERR = "Save Error";

    public FileReceiver(Presentation presentation, Frame frame)
    {
        this.presentation = presentation;
        this.frame = frame;
        this.accessorFactory = AccessorFactory.getInstance();
    }

    public void newPresentation()
    {
        presentation.clear();
        frame.repaint();
    }

    public void openPresentation(String filename)
    {
        presentation.clear();
        Accessor accessor = accessorFactory.getAccessorForFile(filename);
        try
        {
            accessor.loadFile(presentation, filename);
            presentation.setSlideNumber(0);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(
                    frame, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE
            );
        }
        frame.repaint();
    }

    public void savePresentation(String filename)
    {
        Accessor accessor = accessorFactory.getAccessorForFile(filename);
        try
        {
            accessor.saveFile(presentation, filename);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(
                    frame, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE
            );
        }
    }
} 