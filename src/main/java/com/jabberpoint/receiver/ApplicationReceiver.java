package main.java.com.jabberpoint.receiver;

import java.awt.Frame;

import main.java.com.jabberpoint.JabberPoint;
import main.java.com.jabberpoint.ui.AboutBox;

/**
 * Receiver for application-level commands like exit and about.
 * 
 * SOLID Principles:
 * - Single Responsibility Principle: Handles only application-level commands (exit, about).
 * - Open/Closed Principle: New application commands can be added without modifying existing code.
 * - Liskov Substitution Principle: Properly implements the Receiver interface.
 * - Interface Segregation Principle: Implements the Receiver interface with methods relevant to application commands.
 * - Dependency Inversion Principle: Depends on abstractions (Receiver interface) rather than concrete implementations.
 */
public class ApplicationReceiver implements Receiver
{
    private final JabberPoint jabberPoint;
    private final Frame frame;

    /**
     * Creates a new ApplicationReceiver.
     * 
     * @param frame The parent frame for UI interactions
     */
    public ApplicationReceiver(Frame frame)
    {
        this.jabberPoint = JabberPoint.getInstance();
        this.frame = frame;
    }

    /**
     * Exits the application with the specified exit code.
     */
    public void exit()
    {
        this.jabberPoint.exit(0);
    }

    /**
     * Displays the about dialog box.
     */
    public void showAbout()
    {
        AboutBox.show(this.frame);
    }
} 