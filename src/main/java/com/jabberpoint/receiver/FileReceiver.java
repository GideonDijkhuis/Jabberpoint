package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.accessor.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;

import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Frame;

/**
 * <p>main.java.com.jabberpoint.receiver.FileReceiver implements the main.java.com.jabberpoint.receiver.Receiver interface for file operation commands</p>
 * <p>This class knows how to perform file operations on a presentation</p>
 * @author Bram Huiskes
 * @version 1.0
 * @version 1.1 Gideon Dijkhuis - Updated finals
 */
public class FileReceiver implements Receiver {
    private final Presentation presentation;
    private final Frame frame;
    private final AccessorFactory accessorFactory;
    
    // Error messages
    private static final String IOEX = "IO Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String SAVEERR = "Save Error";
    
    /**
     * Constructor for main.java.com.jabberpoint.receiver.FileReceiver
     * @param presentation The presentation to operate on
     * @param frame The parent frame for dialogs
     */
    public FileReceiver(Presentation presentation, Frame frame) {
        this.presentation = presentation;
        this.frame = frame;
        this.accessorFactory = AccessorFactory.getInstance();
    }
    
    /**
     * Create a new presentation
     */
    public void newPresentation() {
        presentation.clear();
        frame.repaint();
    }
    
    /**
     * Open a presentation from a file
     * @param filename The filename to open
     */
    public void openPresentation(String filename) {
        presentation.clear();
        Accessor accessor = accessorFactory.getAccessorForFile(filename);
        try {
            accessor.loadFile(presentation, filename);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(frame, IOEX + exc, 
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        frame.repaint();
    }
    
    /**
     * Save a presentation to a file
     * @param filename The filename to save to
     */
    public void savePresentation(String filename) {
        Accessor accessor = accessorFactory.getAccessorForFile(filename);
        try {
            accessor.saveFile(presentation, filename);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(frame, IOEX + exc, 
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }
} 