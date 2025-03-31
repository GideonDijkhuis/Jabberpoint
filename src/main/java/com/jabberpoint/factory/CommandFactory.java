package main.java.com.jabberpoint.factory;

import main.java.com.jabberpoint.command.*;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.receiver.*;

import java.awt.Frame;

/**
 * <p>main.java.com.jabberpoint.factory.CommandFactory creates command objects</p>
 * <p>This class centralizes the creation of main.java.com.jabberpoint.command.Command objects</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class CommandFactory {
    private static CommandFactory instance;
    private Presentation presentation;
    private Frame frame;
    
    // Receivers
    private NavigationReceiver navigationReceiver;
    private FileReceiver fileReceiver;
    private ApplicationReceiver applicationReceiver;
    
    private CommandFactory() {
        // Private constructor for singleton
        this.presentation = Presentation.getInstance();
    }
    
    /**
     * Get the singleton instance of main.java.com.jabberpoint.factory.CommandFactory
     * @return The main.java.com.jabberpoint.factory.CommandFactory instance
     */
    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }
    
    /**
     * Set the presentation for the factory
     * @param presentation The presentation to use
     */
    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
        // Reinitialize receivers
        initializeReceivers();
    }
    
    /**
     * Set the parent frame for the factory
     * @param frame The frame to use for dialogs
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
        // Reinitialize receivers
        initializeReceivers();
    }
    
    /**
     * Initialize receivers
     */
    private void initializeReceivers() {
        if (presentation != null) {
            navigationReceiver = new NavigationReceiver();
            
            if (frame != null) {
                fileReceiver = new FileReceiver(presentation, frame);
                applicationReceiver = new ApplicationReceiver(frame);
            }
        }
    }
    
    /**
     * Create a main.java.com.jabberpoint.command.NextSlideCommand
     * @return A new main.java.com.jabberpoint.command.NextSlideCommand
     */
    public Command createNextSlideCommand() {
        return new NextSlideCommand(navigationReceiver);
    }
    
    /**
     * Create a main.java.com.jabberpoint.command.PrevSlideCommand
     * @return A new main.java.com.jabberpoint.command.PrevSlideCommand
     */
    public Command createPrevSlideCommand() {
        return new PrevSlideCommand(navigationReceiver);
    }
    
    /**
     * Create an main.java.com.jabberpoint.command.ExitCommand
     * @return A new main.java.com.jabberpoint.command.ExitCommand
     */
    public Command createExitCommand() {
        return new ExitCommand(applicationReceiver);
    }
    
    /**
     * Create an main.java.com.jabberpoint.command.OpenCommand
     * @param filename The filename to open
     * @return A new main.java.com.jabberpoint.command.OpenCommand
     */
    public Command createOpenCommand(String filename) {
        return new OpenCommand(fileReceiver, filename);
    }
    
    /**
     * Create a main.java.com.jabberpoint.command.SaveCommand
     * @param filename The filename to save to
     * @return A new main.java.com.jabberpoint.command.SaveCommand
     */
    public Command createSaveCommand(String filename) {
        return new SaveCommand(fileReceiver, filename);
    }
    
    /**
     * Create a main.java.com.jabberpoint.command.NewCommand
     * @return A new main.java.com.jabberpoint.command.NewCommand
     */
    public Command createNewCommand() {
        return new NewCommand(fileReceiver);
    }
    
    /**
     * Create an main.java.com.jabberpoint.command.AboutCommand
     * @return A new main.java.com.jabberpoint.command.AboutCommand
     */
    public Command createAboutCommand() {
        return new AboutCommand(applicationReceiver);
    }
    
    /**
     * Create a main.java.com.jabberpoint.command.GotoCommand
     * @param prompt The prompt to show in the dialog
     * @return A new main.java.com.jabberpoint.command.GotoCommand
     */
    public Command createGotoCommand(String prompt) {
        return new GotoCommand(navigationReceiver, frame, prompt);
    }
} 