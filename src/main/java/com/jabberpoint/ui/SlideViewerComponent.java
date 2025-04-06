package main.java.com.jabberpoint.ui;

import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.model.observer.*;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class SlideViewerComponent extends JComponent implements Observer
{

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

    public SlideViewerComponent(Presentation pres, JFrame frame)
    {
        setBackground(BGCOLOR);
        presentation = pres;
        labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
        this.frame = frame;

        presentation.registerObserver(this);
    }

    public Slide getSlide()
    {
        return this.slide;
    }

    public Frame getFrame()
    {
        return this.frame;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    public void update(Presentation presentation, Slide data)
    {
        if (data == null)
        {
            repaint();
            return;
        }
        this.presentation = presentation;
        this.slide = data;
        repaint();
        frame.setTitle(presentation.getTitle());
    }

    @Override
    public void update(Object subject, Object data)
    {
        if (!(subject instanceof Presentation pres))
        {
            return;
        }

        this.presentation = pres;

        if (data instanceof Slide)
        {
            this.slide = (Slide) data;
        }
        else
        {
            this.slide = pres.getCurrentSlide();
        }

        this.repaint();
        frame.setTitle(pres.getTitle());
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(BGCOLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);
        if (presentation.getSlideNumber() < 0 || slide == null)
        {
            return;
        }
        g.setFont(labelFont);
        g.setColor(COLOR);
        g.drawString(
                "Slide " + (1 + presentation.getSlideNumber()) + " of " +
                        presentation.getSize(), XPOS, YPOS
        );
        Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
        slide.draw(g, area, this);
    }
}
