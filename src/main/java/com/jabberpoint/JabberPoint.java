package main.java.com.jabberpoint;

import main.java.com.jabberpoint.accessor.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.ui.*;
import main.java.com.jabberpoint.util.*;

import javax.swing.JOptionPane;

import java.io.IOException;

/**
 * main.java.com.jabberpoint.JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License). Please read it. Your use of the software
 * constitutes acceptance of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 2.0 - Gideon Dijkhuis - Update classes to instances
 */

public class JabberPoint
{
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    private static JabberPoint instance;

    private JabberPoint()
    {

    }

    public static JabberPoint getInstance()
    {
        if (instance == null)
        {
            instance = new JabberPoint();
        }

        return instance;
    }

    public void exit(int n)
    {
        System.exit(n);
    }

    public static void main(String[] args)
    {

        Style.createStyles();

        Presentation presentation = Presentation.getInstance();

        SlideViewerFrame.getInstance(JABVERSION, presentation);

        AccessorFactory accessorFactory = AccessorFactory.getInstance();

        try
        {
            if (args.length == 0)
            {
                Accessor demoAccessor = accessorFactory.createDemoAccessor();
                demoAccessor.loadFile(presentation, "");
            }
            else
            {
                Accessor accessor = accessorFactory.getAccessorForFile(args[0]);
                accessor.loadFile(presentation, args[0]);
            }

            presentation.setSlideNumber(0);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    IOERR + ex, JABERR,
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
