/**
 * <p>NextSlideCommand implements the Command interface to navigate to the next slide</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class NextSlideCommand implements Command {
    private NavigationReceiver receiver;

    /**
     * Constructor for NextSlideCommand
     * @param receiver The receiver that will handle the navigation
     */
    public NextSlideCommand(NavigationReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.nextSlide();
    }
} 