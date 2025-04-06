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

public class MenuController extends MenuBar
{

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

    public MenuController(Frame frame, Presentation pres)
    {
        commandFactory = CommandFactory.getInstance();
        commandFactory.setPresentation(pres);
        commandFactory.setFrame(frame);

        AccessorFactory accessorFactory = AccessorFactory.getInstance();

        createFileMenu();
        createViewMenu();
        createHelpMenu();
    }

    private void createFileMenu()
    {
        MenuItem menuItem;
        Menu fileMenu = new Menu(FILE);

        Command openCommand = commandFactory.createOpenCommand();
        fileMenu.add(menuItem = mkMenuItem(OPEN));
        menuItem.addActionListener(createActionListener(openCommand));

        Command newCommand = commandFactory.createNewCommand();
        fileMenu.add(menuItem = mkMenuItem(NEW));
        menuItem.addActionListener(createActionListener(newCommand));

        Command saveCommand = commandFactory.createSaveCommand(SAVEFILE);
        fileMenu.add(menuItem = mkMenuItem(SAVE));
        menuItem.addActionListener(createActionListener(saveCommand));

        fileMenu.addSeparator();

        Command exitCommand = commandFactory.createExitCommand();
        fileMenu.add(menuItem = mkMenuItem(EXIT));
        menuItem.addActionListener(createActionListener(exitCommand));

        add(fileMenu);
    }

    private void createViewMenu()
    {
        MenuItem menuItem;
        Menu viewMenu = new Menu(VIEW);

        Command nextCommand = commandFactory.createNextSlideCommand();
        viewMenu.add(menuItem = mkMenuItem(NEXT));
        menuItem.addActionListener(createActionListener(nextCommand));

        Command prevCommand = commandFactory.createPrevSlideCommand();
        viewMenu.add(menuItem = mkMenuItem(PREV));
        menuItem.addActionListener(createActionListener(prevCommand));

        Command gotoCommand = commandFactory.createGotoCommand(PAGENR);
        viewMenu.add(menuItem = mkMenuItem(GOTO));
        menuItem.addActionListener(createActionListener(gotoCommand));

        add(viewMenu);
    }

    private void createHelpMenu()
    {
        MenuItem menuItem;
        Menu helpMenu = new Menu(HELP);

        Command aboutCommand = commandFactory.createAboutCommand();
        helpMenu.add(menuItem = mkMenuItem(ABOUT));
        menuItem.addActionListener(createActionListener(aboutCommand));

        setHelpMenu(helpMenu);
    }

    public ActionListener createActionListener(final Command command)
    {
        return e -> command.execute();
    }

    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
