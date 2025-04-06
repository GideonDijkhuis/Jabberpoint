package main.java.com.jabberpoint.receiver;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

import main.java.com.jabberpoint.accessor.Accessor;
import main.java.com.jabberpoint.factory.AccessorFactory;
import main.java.com.jabberpoint.model.Presentation;

/**
 * Receiver for file operations on presentations.
 * 
 * SOLID Principles:
 * - Single Responsibility Principle: Handles only file-related operations for presentations.
 * - Open/Closed Principle: New file operations can be added without modifying existing ones.
 * - Liskov Substitution Principle: Properly implements the Receiver interface.
 * - Interface Segregation Principle: Implements the Receiver interface with methods relevant to file operations.
 * - Dependency Inversion Principle: Depends on abstractions (Receiver interface, Accessor) rather than concrete implementations.
 */
public class FileReceiver implements Receiver
{
    private final Presentation presentation;
    private final Frame frame;
    private final AccessorFactory accessorFactory;

    // Error messages
    private static final String IOEX = "IO Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String SAVEERR = "Save Error";

    /**
     * Creates a new FileReceiver.
     * 
     * @param presentation The presentation to perform file operations on
     * @param frame The parent frame for UI interactions
     */
    public FileReceiver(Presentation presentation, Frame frame)
    {
        this.presentation = presentation;
        this.frame = frame;
        this.accessorFactory = AccessorFactory.getInstance();
    }

    /**
     * Creates a new empty presentation.
     */
    public void newPresentation()
    {
        this.presentation.clear();
        this.frame.repaint();
    }

    /**
     * Opens a presentation from a file.
     * 
     * @param filename The name of the file to open
     */
    public void openPresentation(String filename)
    {
        this.presentation.clear();
        Accessor accessor = this.accessorFactory.getAccessorForFile(filename);
        try
        {
            accessor.loadFile(this.presentation, filename);
            this.presentation.setSlideNumber(0);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(
                    this.frame, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE
            );
        }
        this.frame.repaint();
    }

    /**
     * Saves a presentation to a file.
     * 
     * @param filename The name of the file to save to
     */
    public void savePresentation(String filename)
    {
        Accessor accessor = this.accessorFactory.getAccessorForFile(filename);
        try
        {
            accessor.saveFile(this.presentation, filename);
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(
                    this.frame, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE
            );
        }
    }
} 