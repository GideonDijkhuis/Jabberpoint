package main.java.com.jabberpoint;

import java.io.IOException;
import javax.swing.JOptionPane;

import main.java.com.jabberpoint.accessor.Accessor;
import main.java.com.jabberpoint.factory.AccessorFactory;
import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.ui.SlideViewerFrame;
import main.java.com.jabberpoint.util.Style;

/**
 * JabberPoint Main Program
 * <p>
 * This program is distributed under the conditions of the attached COPYRIGHT.txt file (which is NOT the GNU General
 * Public License). Please read it. Your use of the software constitutes acceptance of the conditions mentioned in
 * COPYRIGHT.txt.
 * </p>
 *
 * SOLID Principles: - Single Responsibility Principle: Only responsible for application startup and lifecycle
 * management. - Open/Closed Principle: Can be extended for new startup options without modifying existing code. -
 * Liskov Substitution Principle: Singleton instance can be safely used throughout the application. - Interface
 * Segregation Principle: Provides focused methods related to application control. - Dependency Inversion Principle:
 * Depends on abstractions (Accessor, Presentation) rather than concrete implementations.
 *
 * @version 2.0 - Gideon Dijkhuis - Update classes to instances
 */
public class JabberPoint {
    protected static final String IO_ERR = "IO Error: ";
    protected static final String JAB_ERR = "Jabberpoint Error ";
    protected static final String JAB_VERSION = "Jabberpoint 1.6 - OU version";

    private static JabberPoint instance;

    /**
     * Private constructor for singleton pattern.
     */
    private JabberPoint() {
    }

    /**
     * Gets the singleton instance of JabberPoint.
     *
     * @return The singleton instance
     */
    public static JabberPoint getInstance() {
        if (instance == null) {
            instance = new JabberPoint();
        }
        return instance;
    }

    /**
     * Exits the application with the specified exit code.
     *
     * @param n The exit code
     */
    public void exit(int n) {
        System.exit(n);
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments, optionally containing a filename
     */
    public static void main(String[] args) {
        Style.createStyles();
        Presentation presentation = Presentation.getInstance();
        SlideViewerFrame.getInstance(JAB_VERSION, presentation);
        AccessorFactory accessorFactory = AccessorFactory.getInstance();

        try {
            if (args.length == 0) {
                Accessor demoAccessor = accessorFactory.createDemoAccessor();
                demoAccessor.loadFile(presentation, "");
            }
            else {
                Accessor accessor = accessorFactory.getAccessorForFile(args[0]);
                accessor.loadFile(presentation, args[0]);
            }
            presentation.setSlideNumber(0);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    IO_ERR + ex,
                    JAB_ERR,
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}