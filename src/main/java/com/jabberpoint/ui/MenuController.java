package main.java.com.jabberpoint.ui;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.io.Serial;

import main.java.com.jabberpoint.command.Command;
import main.java.com.jabberpoint.factory.AccessorFactory;
import main.java.com.jabberpoint.factory.CommandFactory;
import main.java.com.jabberpoint.model.Presentation;

/**
 * Controller for the menu bar with file, view and help options.
 *
 * SOLID Principles: - Single Responsibility Principle: Only responsible for creating and managing the menu interface. -
 * Open/Closed Principle: New menu items can be added without modifying existing code. - Liskov Substitution Principle:
 * Properly extends MenuBar without changing its behavior. - Interface Segregation Principle: Creates focused action
 * listeners that handle specific commands. - Dependency Inversion Principle: Depends on abstractions (Command
 * interface) rather than concrete implementations.
 */
public class MenuController extends MenuBar {

    private final CommandFactory commandFactory;

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

    public static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    /**
     * Creates a menu controller with file, view and help menus.
     *
     * @param frame The parent frame for the menu
     * @param pres  The presentation to control
     */
    public MenuController(Frame frame, Presentation pres) {
        this.commandFactory = CommandFactory.getInstance();
        this.commandFactory.setPresentation(pres);
        this.commandFactory.setFrame(frame);

        AccessorFactory accessorFactory = AccessorFactory.getInstance();

        createFileMenu();
        createViewMenu();
        createHelpMenu();
    }

    /**
     * Creates the file menu with open, new, save and exit options.
     */
    private void createFileMenu() {
        MenuItem menuItem;
        Menu fileMenu = new Menu(FILE);

        Command openCommand = this.commandFactory.createOpenCommand();
        fileMenu.add(menuItem = mkMenuItem(OPEN));
        menuItem.addActionListener(createActionListener(openCommand));

        Command newCommand = this.commandFactory.createNewCommand();
        fileMenu.add(menuItem = mkMenuItem(NEW));
        menuItem.addActionListener(createActionListener(newCommand));

        Command saveCommand = this.commandFactory.createSaveCommand(SAVEFILE);
        fileMenu.add(menuItem = mkMenuItem(SAVE));
        menuItem.addActionListener(createActionListener(saveCommand));

        fileMenu.addSeparator();

        Command exitCommand = this.commandFactory.createExitCommand();
        fileMenu.add(menuItem = mkMenuItem(EXIT));
        menuItem.addActionListener(createActionListener(exitCommand));

        add(fileMenu);
    }

    /**
     * Creates the view menu with next, previous and goto options.
     */
    private void createViewMenu() {
        MenuItem menuItem;
        Menu viewMenu = new Menu(VIEW);

        Command nextCommand = this.commandFactory.createNextSlideCommand();
        viewMenu.add(menuItem = mkMenuItem(NEXT));
        menuItem.addActionListener(createActionListener(nextCommand));

        Command prevCommand = this.commandFactory.createPrevSlideCommand();
        viewMenu.add(menuItem = mkMenuItem(PREV));
        menuItem.addActionListener(createActionListener(prevCommand));

        Command gotoCommand = this.commandFactory.createGotoCommand(PAGENR);
        viewMenu.add(menuItem = mkMenuItem(GOTO));
        menuItem.addActionListener(createActionListener(gotoCommand));

        add(viewMenu);
    }

    /**
     * Creates the help menu with about option.
     */
    private void createHelpMenu() {
        MenuItem menuItem;
        Menu helpMenu = new Menu(HELP);

        Command aboutCommand = this.commandFactory.createAboutCommand();
        helpMenu.add(menuItem = mkMenuItem(ABOUT));
        menuItem.addActionListener(createActionListener(aboutCommand));

        setHelpMenu(helpMenu);
    }

    /**
     * Creates an action listener that executes the given command.
     *
     * @param command The command to execute
     * @return An ActionListener that executes the command
     */
    public ActionListener createActionListener(final Command command) {
        return e -> command.execute();
    }

    /**
     * Creates a menu item with a keyboard shortcut.
     *
     * @param name The name of the menu item
     * @return The created menu item
     */
    public MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
