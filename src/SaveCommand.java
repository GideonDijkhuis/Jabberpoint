import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Frame;

/**
 * <p>SaveCommand implements the Command interface to save a presentation file</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class SaveCommand implements Command {
    private FileReceiver receiver;
    private String filename;
    
    /**
     * Constructor for SaveCommand
     * @param receiver The receiver that will handle the file operation
     * @param filename The filename to save to
     */
    public SaveCommand(FileReceiver receiver, String filename) {
        this.receiver = receiver;
        this.filename = filename;
    }
    
    /**
     * Execute the command by delegating to the receiver
     */
    @Override
    public void execute() {
        receiver.savePresentation(filename);
    }
} 