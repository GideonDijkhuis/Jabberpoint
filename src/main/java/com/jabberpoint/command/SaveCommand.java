package main.java.com.jabberpoint.command;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import main.java.com.jabberpoint.receiver.FileReceiver;
import main.java.com.jabberpoint.ui.SlideViewerFrame;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for saving a presentation - Open/Closed
 * Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements the Command
 * interface - Interface Segregation Principle: Uses only required methods from Command - Dependency Inversion
 * Principle: Depends on abstractions (Command) not concrete implementations
 *
 * Command to save a presentation to a file.
 */
public class SaveCommand implements Command {
    private final FileReceiver receiver;
    private final String filename;

    /**
     * Creates a new command for saving a presentation.
     *
     * @param receiver The file receiver to handle the command
     * @param filename The default filename to save to
     */
    public SaveCommand(FileReceiver receiver, String filename) {
        this.receiver = receiver;
        this.filename = filename;
    }

    /**
     * Executes the command to save a presentation. Shows a file chooser dialog and saves to the selected file.
     */
    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                else {
                    return f.getName().toLowerCase().endsWith(".xml");
                }
            }

            @Override
            public String getDescription() {
                return "XML documents";
            }
        });

        int result = fileChooser.showSaveDialog(SlideViewerFrame.getInstance());

        if (result == JFileChooser.APPROVE_OPTION) {
            String savedFileName = fileChooser.getSelectedFile().getAbsolutePath();
            if (!savedFileName.endsWith(".xml")) {
                savedFileName += ".xml";
            }
            this.receiver.savePresentation(savedFileName);
            return;
        }

        this.receiver.savePresentation(this.filename);
    }
} 