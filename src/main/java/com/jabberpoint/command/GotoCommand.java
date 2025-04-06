package main.java.com.jabberpoint.command;

import java.awt.Frame;
import javax.swing.JOptionPane;

import main.java.com.jabberpoint.receiver.NavigationReceiver;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for navigating to a specific slide -
 * Open/Closed Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements the
 * Command interface - Interface Segregation Principle: Uses only required methods from Command - Dependency Inversion
 * Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to navigate to a specific slide.
 *
 * @author Bram Huiskes
 * @version 1.0
 */
public class GotoCommand implements Command {
    private final NavigationReceiver receiver;
    private final Frame frame;
    private final String prompt;

    /**
     * Creates a new goto command.
     *
     * @param receiver The navigation receiver to handle the command
     * @param frame    The parent frame for displaying dialogs
     * @param prompt   The prompt text to display in the input dialog
     */
    public GotoCommand(NavigationReceiver receiver, Frame frame, String prompt) {
        this.receiver = receiver;
        this.frame = frame;
        this.prompt = prompt;
    }

    /**
     * Executes the goto command, showing an input dialog and navigating to the specified slide.
     */
    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog(this.frame, this.prompt);
        if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
            try {
                int pageNumber = Integer.parseInt(pageNumberStr);
                this.receiver.gotoSlide(pageNumber - 1); // Convert from 1-based to 0-based
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this.frame, "Please enter a valid number",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}