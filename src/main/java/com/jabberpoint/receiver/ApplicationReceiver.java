package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.JabberPoint;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.ui.*;

import java.awt.Frame;

/**
 * <p>main.java.com.jabberpoint.receiver.ApplicationReceiver implements the main.java.com.jabberpoint.receiver.Receiver interface for application-level operations</p>
 * <p>This class knows how to perform operations on the application</p>
 * @author Bram Huiskes
 * @version 1.0
 * @version 1.1 Gideon Dijkhuis - Updated finals
 */
public class ApplicationReceiver implements Receiver {
    private final JabberPoint jabberPoint;
    private final Frame frame;
    
    /**
     * Constructor for main.java.com.jabberpoint.receiver.ApplicationReceiver
     * @param frame The parent frame for dialogs
     */
    public ApplicationReceiver(Frame frame) {
        this.jabberPoint = JabberPoint.getInstance();
        this.frame = frame;
    }
    
    /**
     * Exit the application
     */
    public void exit() {
        this.jabberPoint.exit(0);
    }
    
    /**
     * Show the about box
     */
    public void showAbout() {
        AboutBox.show(frame);
    }
} 