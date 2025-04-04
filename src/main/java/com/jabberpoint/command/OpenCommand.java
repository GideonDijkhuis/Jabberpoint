package main.java.com.jabberpoint.command;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import main.java.com.jabberpoint.receiver.*;
import main.java.com.jabberpoint.ui.SlideViewerFrame;

import java.io.File;

/**
 * <p>main.java.com.jabberpoint.command.OpenCommand implements the main.java.com.jabberpoint.command.Command interface
 * to open a presentation file</p>
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
     * Constructor for main.java.com.jabberpoint.command.OpenCommand
     *
     * @param receiver The receiver that will handle the file operation
     */
    public OpenCommand(FileReceiver receiver)
    {
        this.receiver = receiver;
        this.filename = TESTFILE;
    }

    /**
     * Execute the command by delegating to the receiver
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