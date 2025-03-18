package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.ui.*;

import java.awt.Frame;

/**
 * <p>main.java.com.jabberpoint.receiver.ApplicationReceiver implements the main.java.com.jabberpoint.receiver.Receiver interface for application-level operations</p>
 * <p>This class knows how to perform operations on the application</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class ApplicationReceiver implements Receiver {
    private Presentation presentation;
    private Frame frame;
    
    /**
     * Constructor for main.java.com.jabberpoint.receiver.ApplicationReceiver
     * @param presentation The presentation for application operations
     * @param frame The parent frame for dialogs
     */
    public ApplicationReceiver(Presentation presentation, Frame frame) {
        this.presentation = presentation;
        this.frame = frame;
    }
    
    /**
     * Exit the application
     */
    public void exit() {
        presentation.exit(0);
    }
    
    /**
     * Show the about box
     */
    public void showAbout() {
        AboutBox.show(frame);
    }
} 