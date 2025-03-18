/**
 * <p>NewCommand implements the Command interface to create a new presentation</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class NewCommand implements Command {
    private FileReceiver receiver;
    
    /**
     * Constructor for NewCommand
     * @param receiver The receiver that will handle the operation
     */
    public NewCommand(FileReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.newPresentation();
    }
} 