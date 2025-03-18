import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Frame;

/**
 * <p>FileReceiver implements the Receiver interface for file operation commands</p>
 * <p>This class knows how to perform file operations on a presentation</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class FileReceiver implements Receiver {
    private Presentation presentation;
    private Frame frame;
    
    // Error messages
    private static final String IOEX = "IO Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String SAVEERR = "Save Error";
    
    /**
     * Constructor for FileReceiver
     * @param presentation The presentation to operate on
     * @param frame The parent frame for dialogs
     */
    public FileReceiver(Presentation presentation, Frame frame) {
        this.presentation = presentation;
        this.frame = frame;
    }
    
    /**
     * Create a new presentation
     */
    public void newPresentation() {
        presentation.clear();
        frame.repaint();
    }
    
    /**
     * Open a presentation from a file
     * @param filename The filename to open
     */
    public void openPresentation(String filename) {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, filename);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(frame, IOEX + exc, 
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        frame.repaint();
    }
    
    /**
     * Save a presentation to a file
     * @param filename The filename to save to
     */
    public void savePresentation(String filename) {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, filename);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(frame, IOEX + exc, 
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }
} 