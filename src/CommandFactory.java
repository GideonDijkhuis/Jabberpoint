import java.awt.Frame;

/**
 * <p>CommandFactory creates command objects</p>
 * <p>This class centralizes the creation of Command objects</p>
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
     * Get the singleton instance of CommandFactory
     * @return The CommandFactory instance
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
            navigationReceiver = new NavigationReceiver(presentation);
            
            if (frame != null) {
                fileReceiver = new FileReceiver(presentation, frame);
                applicationReceiver = new ApplicationReceiver(presentation, frame);
            }
        }
    }
    
    /**
     * Create a NextSlideCommand
     * @return A new NextSlideCommand
     */
    public Command createNextSlideCommand() {
        return new NextSlideCommand(navigationReceiver);
    }
    
    /**
     * Create a PrevSlideCommand
     * @return A new PrevSlideCommand
     */
    public Command createPrevSlideCommand() {
        return new PrevSlideCommand(navigationReceiver);
    }
    
    /**
     * Create an ExitCommand
     * @return A new ExitCommand
     */
    public Command createExitCommand() {
        return new ExitCommand(applicationReceiver);
    }
    
    /**
     * Create an OpenCommand
     * @param filename The filename to open
     * @return A new OpenCommand
     */
    public Command createOpenCommand(String filename) {
        return new OpenCommand(fileReceiver, filename);
    }
    
    /**
     * Create a SaveCommand
     * @param filename The filename to save to
     * @return A new SaveCommand
     */
    public Command createSaveCommand(String filename) {
        return new SaveCommand(fileReceiver, filename);
    }
    
    /**
     * Create a NewCommand
     * @return A new NewCommand
     */
    public Command createNewCommand() {
        return new NewCommand(fileReceiver);
    }
    
    /**
     * Create an AboutCommand
     * @return A new AboutCommand
     */
    public Command createAboutCommand() {
        return new AboutCommand(applicationReceiver);
    }
    
    /**
     * Create a GotoCommand
     * @param prompt The prompt to show in the dialog
     * @return A new GotoCommand
     */
    public Command createGotoCommand(String prompt) {
        return new GotoCommand(navigationReceiver, frame, prompt);
    }
} 