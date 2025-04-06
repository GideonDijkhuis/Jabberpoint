package main.java.com.jabberpoint.command;

import main.java.com.jabberpoint.receiver.*;
import main.java.com.jabberpoint.ui.SlideViewerFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SaveCommand implements Command
{
    private final FileReceiver receiver;
    private final String filename;

    public SaveCommand(FileReceiver receiver, String filename)
    {
        this.receiver = receiver;
        this.filename = filename;
    }

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

        int result = fileChooser.showSaveDialog(SlideViewerFrame.getInstance());

        if (result == JFileChooser.APPROVE_OPTION)
        {
            String savedFileName = fileChooser.getSelectedFile().getAbsolutePath();
            if (!savedFileName.endsWith(".xml"))
            {
                savedFileName += ".xml";
            }
            receiver.savePresentation(savedFileName);
            return;
        }

        receiver.savePresentation(this.filename);
    }
} 