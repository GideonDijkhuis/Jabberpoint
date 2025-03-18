/**
 * <p>ExitCommand implements the Command interface to exit the application</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class ExitCommand implements Command {
    private ApplicationReceiver receiver;

    /**
     * Constructor for ExitCommand
     * @param receiver The receiver that will handle the exit operation
     */
    public ExitCommand(ApplicationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.exit();
    }
} 