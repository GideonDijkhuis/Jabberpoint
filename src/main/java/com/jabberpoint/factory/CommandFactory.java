package main.java.com.jabberpoint.factory;

import java.awt.Frame;

import main.java.com.jabberpoint.command.AboutCommand;
import main.java.com.jabberpoint.command.Command;
import main.java.com.jabberpoint.command.ExitCommand;
import main.java.com.jabberpoint.command.GotoCommand;
import main.java.com.jabberpoint.command.NewCommand;
import main.java.com.jabberpoint.command.NextSlideCommand;
import main.java.com.jabberpoint.command.OpenCommand;
import main.java.com.jabberpoint.command.PrevSlideCommand;
import main.java.com.jabberpoint.command.SaveCommand;
import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.receiver.ApplicationReceiver;
import main.java.com.jabberpoint.receiver.FileReceiver;
import main.java.com.jabberpoint.receiver.NavigationReceiver;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for creating command objects -
 * Open/Closed Principle: New command types can be added without modifying existing code - Dependency Inversion
 * Principle: Depends on abstractions (Command, Receiver interfaces) - Interface Segregation Principle: Creates specific
 * commands that implement focused interfaces - Liskov Substitution Principle: All commands can be used interchangeably
 * through Command interface
 *
 * Factory for creating various commands used in the application. Implements the Singleton pattern.
 */
public class CommandFactory {
    private static CommandFactory instance;
    private Presentation presentation;
    private Frame frame;

    // Receivers
    private NavigationReceiver navigationReceiver;
    private FileReceiver fileReceiver;
    private ApplicationReceiver applicationReceiver;

    /**
     * Private constructor for singleton pattern.
     */
    private CommandFactory() {
        this.presentation = Presentation.getInstance();
    }

    /**
     * Gets the singleton instance of the CommandFactory.
     *
     * @return The singleton instance
     */
    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    /**
     * Sets the presentation to be controlled by the commands.
     *
     * @param presentation The presentation instance
     */
    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
        this.initializeReceivers();
    }

    /**
     * Sets the frame to be used by commands that need UI interaction.
     *
     * @param frame The frame instance
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
        this.initializeReceivers();
    }

    /**
     * Initializes the receivers with the current presentation and frame.
     */
    private void initializeReceivers() {
        if (this.presentation != null) {
            this.navigationReceiver = new NavigationReceiver();

            if (this.frame != null) {
                this.fileReceiver = new FileReceiver(this.presentation, this.frame);
                this.applicationReceiver = new ApplicationReceiver(this.frame);
            }
        }
    }

    /**
     * Creates a command to navigate to the next slide.
     *
     * @return A NextSlideCommand instance
     */
    public Command createNextSlideCommand() {
        return new NextSlideCommand(this.navigationReceiver);
    }

    /**
     * Creates a command to navigate to the previous slide.
     *
     * @return A PrevSlideCommand instance
     */
    public Command createPrevSlideCommand() {
        return new PrevSlideCommand(this.navigationReceiver);
    }

    /**
     * Creates a command to exit the application.
     *
     * @return An ExitCommand instance
     */
    public Command createExitCommand() {
        return new ExitCommand(this.applicationReceiver);
    }

    /**
     * Creates a command to open a presentation.
     *
     * @return An OpenCommand instance
     */
    public Command createOpenCommand() {
        return new OpenCommand(this.fileReceiver);
    }

    /**
     * Creates a command to save a presentation.
     *
     * @param filename The name of the file to save to
     * @return A SaveCommand instance
     */
    public Command createSaveCommand(String filename) {
        return new SaveCommand(this.fileReceiver, filename);
    }

    /**
     * Creates a command to create a new presentation.
     *
     * @return A NewCommand instance
     */
    public Command createNewCommand() {
        return new NewCommand(this.fileReceiver);
    }

    /**
     * Creates a command to show the about dialog.
     *
     * @return An AboutCommand instance
     */
    public Command createAboutCommand() {
        return new AboutCommand(this.applicationReceiver);
    }

    /**
     * Creates a command to navigate to a specific slide.
     *
     * @param prompt The prompt to display to the user
     * @return A GotoCommand instance
     */
    public Command createGotoCommand(String prompt) {
        return new GotoCommand(this.navigationReceiver, this.frame, prompt);
    }
} 