package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;

import javax.swing.JOptionPane;
import java.awt.Frame;

/**
 * <p>main.java.com.jabberpoint.command.GotoCommand implements the main.java.com.jabberpoint.command.Command interface
 * to go to a specific slide</p>
 *
 * @author Bram Huiskes
 * @version 1.0
 */
public class GotoCommand implements Command
{
    private final NavigationReceiver receiver;
    private final Frame frame;
    private final String prompt;

    public GotoCommand(NavigationReceiver receiver, Frame frame, String prompt)
    {
        this.receiver = receiver;
        this.frame = frame;
        this.prompt = prompt;
    }


    @Override
    public void execute()
    {
        String pageNumberStr = JOptionPane.showInputDialog(frame, prompt);
        if (pageNumberStr != null && !pageNumberStr.isEmpty())
        {
            try
            {
                int pageNumber = Integer.parseInt(pageNumberStr);
                receiver.gotoSlide(pageNumber - 1); // Convert from 1-based to 0-based
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(
                        frame, "Please enter a valid number",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}