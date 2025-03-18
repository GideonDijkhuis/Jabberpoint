/**
 * <p>AboutCommand implements the Command interface to show the about box</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class AboutCommand implements Command {
    private ApplicationReceiver receiver;
    
    /**
     * Constructor for AboutCommand
     * @param receiver The receiver that will handle showing the about box
     */
    public AboutCommand(ApplicationReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.showAbout();
    }
} 