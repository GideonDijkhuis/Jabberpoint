/**
 * <p>PrevSlideCommand implements the Command interface to navigate to the previous slide</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class PrevSlideCommand implements Command {
    private NavigationReceiver receiver;

    /**
     * Constructor for PrevSlideCommand
     * @param receiver The receiver that will handle the navigation
     */
    public PrevSlideCommand(NavigationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.prevSlide();
    }
} 