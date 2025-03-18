package main.java.com.jabberpoint;

import main.java.com.jabberpoint.accessor.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.ui.*;
import main.java.com.jabberpoint.util.*;

import javax.swing.JOptionPane;

import java.io.IOException;

/** main.java.com.jabberpoint.JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2023/09/29 Bram Huiskes - Updated to use Factory Method and main.java.com.jabberpoint.command.Command patterns
 * @version 1.8 2023/09/30 Bram Huiskes - Updated to use Observer pattern
 */

public class JabberPoint {
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

	private static JabberPoint instance;

	private JabberPoint(){

	}

	public static JabberPoint getInstance()
	{
		if (instance == null)
		{
			instance = new JabberPoint();
		}

		return instance;
	}

	/** Het Main Programma */
	public static void main(String argv[])
	{
		
		Style.createStyles();
		
		// Create the presentation model (Subject in Observer pattern)
		Presentation presentation = Presentation.getInstance();
		
		// Create the main frame (Observer in Observer pattern)
		new SlideViewerFrame(JABVERSION, presentation);
		
		// Get the accessor factory
		AccessorFactory accessorFactory = AccessorFactory.getInstance();
		
		try {
			if (argv.length == 0) { // een demo presentatie
				// Get demo accessor from factory
				Accessor demoAccessor = accessorFactory.createDemoAccessor();
				demoAccessor.loadFile(presentation, "");
			} else {
				// Get appropriate accessor for the file
				Accessor accessor = accessorFactory.getAccessorForFile(argv[0]);
				accessor.loadFile(presentation, argv[0]);
			}
			
			// This will trigger the Observer pattern updates
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					IOERR + ex, JABERR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
