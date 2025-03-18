package main.java.com.jabberpoint.ui;

import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.model.observer.*;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>Het applicatiewindow voor een slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2023/09/29 Bram Huiskes - Updated to use main.java.com.jabberpoint.command.Command pattern
 * @version 1.8 2023/09/30 Bram Huiskes - Updated to use Observer pattern
*/

public class SlideViewerFrame extends JFrame implements Observer {
	private static final long serialVersionUID = 3227L;
	
	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	
	private Presentation presentation;
	
	public SlideViewerFrame(String title, Presentation presentation) {
		super(title);
		this.presentation = presentation;
		
		// Register as an observer of the presentation
		presentation.registerObserver(this);
		
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setShowView(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
	}

	// De GUI opzetten
	public void setupWindow(SlideViewerComponent 
			slideViewerComponent, Presentation presentation) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(slideViewerComponent);
		
		// Initialize the main.java.com.jabberpoint.factory.CommandFactory
		CommandFactory factory = CommandFactory.getInstance();
		factory.setPresentation(presentation);
		factory.setFrame(this);
		
		// Add controllers
		addKeyListener(new KeyController(presentation));
		setMenuBar(new MenuController(this, presentation));
		
		setSize(new Dimension(WIDTH, HEIGHT)); // Dezelfde maten als main.java.com.jabberpoint.model.Slide hanteert.
		setVisible(true);
	}
	
	/**
	 * Observer pattern update method
	 */
	@Override
	public void update(Object subject, Object data) {
		if (subject instanceof Presentation) {
			Presentation pres = (Presentation) subject;
			setTitle(JABTITLE + " - " + pres.getTitle());
		} else if (subject instanceof Slide) {
			// If the slide itself changes, we could update the title or other UI elements
			// For now, we just ensure the frame title is up to date
			setTitle(JABTITLE + " - " + presentation.getTitle());
		}
	}
}
