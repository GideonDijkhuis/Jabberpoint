package main.java.com.jabberpoint.ui;

import main.java.com.jabberpoint.command.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serial;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2023/09/29 Bram Huiskes - Updated to use main.java.com.jabberpoint.command.Command pattern and Factory Method
 * @version 1.8 - Gideon Dijkhuis - Made some variables final, create local variables and update openCommand
 */
public class MenuController extends MenuBar {

    private final CommandFactory commandFactory; // Factory for creating commands

    @Serial
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres) {
        // het frame, alleen gebruikt als ouder voor de Dialogs
        // Er worden commando's gegeven aan de presentatie

        // Initialize the factories
		commandFactory = CommandFactory.getInstance();
		commandFactory.setPresentation(pres);
		commandFactory.setFrame(frame);

        // Factory for creating accessors
        AccessorFactory accessorFactory = AccessorFactory.getInstance();
		
		// Create the menu structure
		createFileMenu();
		createViewMenu();
		createHelpMenu();
	}
	
	private void createFileMenu() {
		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		
		// Open command
		Command openCommand = commandFactory.createOpenCommand();
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(createActionListener(openCommand));
		
		// New command
		Command newCommand = commandFactory.createNewCommand();
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(createActionListener(newCommand));
		
		// Save command
		Command saveCommand = commandFactory.createSaveCommand(SAVEFILE);
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(createActionListener(saveCommand));
		
		fileMenu.addSeparator();
		
		// Exit command
		Command exitCommand = commandFactory.createExitCommand();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(createActionListener(exitCommand));
		
		add(fileMenu);
	}
	
	private void createViewMenu() {
		MenuItem menuItem;
		Menu viewMenu = new Menu(VIEW);
		
		// Next slide command
		Command nextCommand = commandFactory.createNextSlideCommand();
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(createActionListener(nextCommand));
		
		// Previous slide command
		Command prevCommand = commandFactory.createPrevSlideCommand();
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(createActionListener(prevCommand));
		
		// Go to slide command
		Command gotoCommand = commandFactory.createGotoCommand(PAGENR);
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(createActionListener(gotoCommand));
		
		add(viewMenu);
	}
	
	private void createHelpMenu() {
		MenuItem menuItem;
		Menu helpMenu = new Menu(HELP);
		
		// About command
		Command aboutCommand = commandFactory.createAboutCommand();
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(createActionListener(aboutCommand));
		
		setHelpMenu(helpMenu); // needed for portability (Motif, etc.)
	}
	
	/**
	 * Create an ActionListener that executes a command
	 * @param command The command to execute
	 * @return ActionListener that executes the command
	 */
	private ActionListener createActionListener(final Command command) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command.execute();
			}
		};
	}

	// Create a menu item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
