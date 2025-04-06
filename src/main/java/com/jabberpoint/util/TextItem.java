package main.java.com.jabberpoint.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.model.SlideItem;

/**
 * Text item that can be displayed on a slide.
 * 
 * SOLID Principles:
 * - Single Responsibility Principle: Only responsible for displaying and formatting text on slides.
 * - Open/Closed Principle: Can be extended for new text formatting capabilities without modifying existing code.
 * - Liskov Substitution Principle: Properly extends SlideItem without changing its behavior.
 * - Interface Segregation Principle: Implements only necessary methods from SlideItem.
 * - Dependency Inversion Principle: Depends on abstractions (Graphics, ImageObserver) rather than concrete implementations.
 */
public class TextItem extends SlideItem
{
    private final String text;

    private static final String EMPTYTEXT = "No Text Given";

    /**
     * Creates a text item with the specified level and text.
     * 
     * @param level The level of the text item
     * @param string The text content
     */
    public TextItem(int level, String string)
    {
        super(level);
        this.text = string;
    }

    /**
     * Creates an empty text item with default level.
     */
    public TextItem()
    {
        this(0, EMPTYTEXT);
    }

    /**
     * Gets the text content of this item.
     * 
     * @return The text content
     */
    public String getText()
    {
        return this.text == null ? "" : this.text;
    }

    /**
     * Creates an attributed string with the appropriate style.
     * 
     * @param style The style to apply
     * @param scale The scale factor
     * @return The attributed string
     */
    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attrStr = new AttributedString(getText());
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());
        return attrStr;
    }

    /**
     * Gets the bounding box for this text item.
     * 
     * @param g The graphics context
     * @param observer The image observer
     * @param scale The scale factor
     * @param myStyle The style to apply
     * @return The bounding rectangle
     */
    public Rectangle getBoundingBox(
            Graphics g, ImageObserver observer,
            float scale, Style myStyle
    )
    {
        List<TextLayout> layouts = getLayouts(g, myStyle, scale);
        int xsize = 0, ysize = (int) (myStyle.leading * scale);
        for (TextLayout layout : layouts)
        {
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize)
            {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0)
            {
                ysize += (int) bounds.getHeight();
            }
            ysize += (int) (layout.getLeading() + layout.getDescent());
        }
        return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
    }

    /**
     * Draws the text item on the screen.
     * 
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param scale The scale factor
     * @param g The graphics context
     * @param myStyle The style to apply
     * @param o The image observer
     */
    public void draw(
            int x, int y, float scale, Graphics g,
            Style myStyle, ImageObserver o
    )
    {
        if (this.text == null || this.text.isEmpty())
        {
            return;
        }
        List<TextLayout> layouts = getLayouts(g, myStyle, scale);
        Point pen = new Point(
                x + (int) (myStyle.indent * scale),
                y + (int) (myStyle.leading * scale)
        );
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(myStyle.color);
        for (TextLayout layout : layouts)
        {
            pen.y += (int) layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += (int) layout.getDescent();
        }
    }

    /**
     * Creates the text layouts for this text item.
     * 
     * @param g The graphics context
     * @param s The style to apply
     * @param scale The scale factor
     * @return The list of text layouts
     */
    private List<TextLayout> getLayouts(Graphics g, Style s, float scale)
    {
        List<TextLayout> layouts = new ArrayList<TextLayout>();
        AttributedString attrStr = getAttributedString(s, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (Slide.WIDTH - s.indent) * scale;
        while (measurer.getPosition() < getText().length())
        {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }
        return layouts;
    }

    /**
     * Returns a string representation of this text item.
     * 
     * @return The string representation
     */
    public String toString()
    {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }
}
