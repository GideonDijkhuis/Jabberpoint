package main.java.com.jabberpoint.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.model.observer.Observer;

/**
 * Component that displays the current slide of the presentation.
 *
 * SOLID Principles: - Single Responsibility Principle: Only responsible for displaying slides and responding to
 * presentation changes. - Open/Closed Principle: Can be extended for new display functionality without modifying
 * existing code. - Liskov Substitution Principle: Properly extends JComponent and implements Observer without changing
 * their behavior. - Interface Segregation Principle: Implements only necessary Observer methods. - Dependency Inversion
 * Principle: Depends on abstractions (Observer interface) rather than concrete implementations.
 */
public class SlideViewerComponent extends JComponent implements Observer {

    private Slide slide;
    private Font labelFont = null;
    private Presentation presentation = null;
    private JFrame frame = null;

    private static final long serialVersionUID = 227L;

    private static final Color BGCOLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONTNAME = "Dialog";
    private static final int FONTSTYLE = Font.BOLD;
    private static final int FONTHEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    /**
     * Creates a slide viewer component.
     *
     * @param pres  The presentation to display
     * @param frame The parent frame for this component
     */
    public SlideViewerComponent(Presentation pres, JFrame frame) {
        setBackground(BGCOLOR);
        this.presentation = pres;
        this.labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.frame = frame;

        this.presentation.registerObserver(this);
    }

    /**
     * Gets the current slide being displayed.
     *
     * @return The current slide
     */
    public Slide getSlide() {
        return this.slide;
    }

    /**
     * Gets the parent frame.
     *
     * @return The parent frame
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * Gets the preferred size of this component.
     *
     * @return The preferred dimension
     */
    public Dimension getPreferredSize() {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    /**
     * Updates the component with new presentation data.
     *
     * @param presentation The presentation
     * @param data         The slide data
     */
    public void update(Presentation presentation, Slide data) {
        if (data == null) {
            repaint();
            return;
        }
        this.presentation = presentation;
        this.slide = data;
        repaint();
        this.frame.setTitle(presentation.getTitle());
    }

    /**
     * Implementation of the Observer interface update method.
     *
     * @param subject The subject being observed (should be a Presentation)
     * @param data    The data (should be a Slide)
     */
    @Override
    public void update(Object subject, Object data) {
        if (!(subject instanceof Presentation pres)) {
            return;
        }

        this.presentation = pres;

        if (data instanceof Slide) {
            this.slide = (Slide) data;
        }
        else {
            this.slide = pres.getCurrentSlide();
        }

        this.repaint();
        this.frame.setTitle(pres.getTitle());
    }

    /**
     * Paints the slide and slide number information.
     *
     * @param g The graphics context to paint on
     */
    public void paintComponent(Graphics g) {
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);
        if (this.presentation.getSlideNumber() < 0 || this.slide == null) {
            return;
        }
        g.setFont(this.labelFont);
        g.setColor(COLOR);
        g.drawString(
                "Slide " + (1 + this.presentation.getSlideNumber()) + " of " +
                        this.presentation.getSize(), XPOS, YPOS
        );
        Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        this.slide.draw(g, area, this);
    }
}
