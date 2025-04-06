package main.java.com.jabberpoint.ui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import main.java.com.jabberpoint.factory.CommandFactory;
import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.model.observer.Observer;

/**
 * The main frame for the slideviewer, implementing the Singleton pattern.
 * 
 * SOLID Principles:
 * - Single Responsibility Principle: Responsible only for the main application window and coordination of components.
 * - Open/Closed Principle: Can be extended with new UI components without modifying existing code.
 * - Liskov Substitution Principle: Properly extends JFrame and implements Observer without changing their behavior.
 * - Interface Segregation Principle: Implements only necessary Observer methods.
 * - Dependency Inversion Principle: Depends on abstractions (Observer interface) rather than concrete implementations.
 */
public class SlideViewerFrame extends JFrame implements Observer
{
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private final Presentation presentation;

    private static SlideViewerFrame instance;

    /**
     * Private constructor for the Singleton pattern.
     * 
     * @param title The title of the frame
     * @param presentation The presentation to display
     */
    private SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);
        this.presentation = presentation;

        presentation.registerObserver(this);

        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setShowView(slideViewerComponent);
        setupWindow(slideViewerComponent, presentation);
    }

    /**
     * Gets the singleton instance.
     * 
     * @return The SlideViewerFrame instance
     * @throws RuntimeException if the instance has not been created yet
     */
    public static SlideViewerFrame getInstance()
    {
        if (instance == null)
        {
            throw new RuntimeException("Instance is null");
        }

        return instance;
    }

    /**
     * Gets or creates the singleton instance.
     * 
     * @param title The title of the frame
     * @param presentation The presentation to display
     * @return The SlideViewerFrame instance
     */
    public static SlideViewerFrame getInstance(String title, Presentation presentation)
    {
        if (instance == null)
        {
            instance = new SlideViewerFrame(title, presentation);
        }

        return instance;
    }

    /**
     * Sets up the window with the given component and controllers.
     * 
     * @param slideViewerComponent The component to display slides
     * @param presentation The presentation to control
     */
    public void setupWindow(
            SlideViewerComponent
                    slideViewerComponent, Presentation presentation
    )
    {
        setTitle(JABTITLE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        getContentPane().add(slideViewerComponent);

        // Initialize the CommandFactory
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
     * Updates the frame title based on the subject.
     * 
     * @param subject The subject that changed
     * @param data Additional data about the change
     */
    @Override
    public void update(Object subject, Object data)
    {
        if (subject instanceof Presentation)
        {
            Presentation pres = (Presentation) subject;
            setTitle(JABTITLE + " - " + pres.getTitle());
        }
        else if (subject instanceof Slide)
        {
            setTitle(JABTITLE + " - " + this.presentation.getTitle());
        }
    }
}
