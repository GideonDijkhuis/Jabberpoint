package main.java.com.jabberpoint.command;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import main.java.com.jabberpoint.receiver.FileReceiver;
import main.java.com.jabberpoint.ui.SlideViewerFrame;

/**
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Only responsible for opening a presentation file
 * - Open/Closed Principle: Can be extended without modification
 * - Liskov Substitution Principle: Properly implements the Command interface
 * - Interface Segregation Principle: Uses only required methods from Command
 * - Dependency Inversion Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to open a presentation file.
 *
 * @author Bram Huiskes
 * @version 1.1 - Gideon Dijkhuis - Added testfile, added OpenFileDialog
 */
public class OpenCommand implements Command
{
    private final FileReceiver receiver;
    private String filename;

    protected static final String TESTFILE = "test.xml";

    /**
     * Creates a new command for opening a presentation file.
     *
     * @param receiver The file receiver to handle the command
     */
    public OpenCommand(FileReceiver receiver)
    {
        this.receiver = receiver;
        this.filename = TESTFILE;
    }

    /**
     * Executes the command to open a presentation file.
     * Shows a file chooser dialog and opens the selected file.
     */
    @Override
    public void execute()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        fileChooser.addChoosableFileFilter(new FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                if (f.isDirectory())
                {
                    return true;
                }
                else
                {
                    return f.getName().toLowerCase().endsWith(".xml");
                }
            }

            @Override
            public String getDescription()
            {
                return "XML documents";
            }
        });

        int result = fileChooser.showOpenDialog(SlideViewerFrame.getInstance());

        if (result == JFileChooser.APPROVE_OPTION)
        {
            this.filename = fileChooser.getSelectedFile().getAbsolutePath();
        }

        this.receiver.openPresentation(this.filename);
    }
} 