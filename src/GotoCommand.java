import javax.swing.JOptionPane;
import java.awt.Frame;

/**
 * <p>GotoCommand implements the Command interface to go to a specific slide</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class GotoCommand implements Command {
    private NavigationReceiver receiver;
    private Frame frame;
    private String prompt;
    
    /**
     * Constructor for GotoCommand
     * @param receiver The receiver that will handle the navigation
     * @param frame The parent frame for dialogs
     * @param prompt The prompt for the input dialog
     */
    public GotoCommand(NavigationReceiver receiver, Frame frame, String prompt) {
        this.receiver = receiver;
        this.frame = frame;
        this.prompt = prompt;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog(frame, prompt);
        if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
            try {
                int pageNumber = Integer.parseInt(pageNumberStr);
                receiver.gotoSlide(pageNumber - 1); // Convert from 1-based to 0-based
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number", 
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}