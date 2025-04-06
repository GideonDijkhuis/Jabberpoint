package main.java.com.jabberpoint.ui;

import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.model.observer.*;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame implements Observer
{
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    private final Presentation presentation;

    private static SlideViewerFrame instance;

    private SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);
        this.presentation = presentation;

        presentation.registerObserver(this);

        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setShowView(slideViewerComponent);
        setupWindow(slideViewerComponent, presentation);
    }

    public static SlideViewerFrame getInstance()
    {
        if (instance == null)
        {
            throw new RuntimeException("Instance is null");
        }

        return instance;
    }

    public static SlideViewerFrame getInstance(String title, Presentation presentation)
    {
        if (instance == null)
        {
            instance = new SlideViewerFrame(title, presentation);
        }

        return instance;
    }

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
    public void update(Object subject, Object data)
    {
        if (subject instanceof Presentation)
        {
            Presentation pres = (Presentation) subject;
            setTitle(JABTITLE + " - " + pres.getTitle());
        }
        else if (subject instanceof Slide)
        {
            setTitle(JABTITLE + " - " + presentation.getTitle());
        }
    }
}
