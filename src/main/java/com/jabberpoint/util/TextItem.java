package main.java.com.jabberpoint.util;

import main.java.com.jabberpoint.model.*;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.ArrayList;

public class TextItem extends SlideItem
{
    private final String text;

    private static final String EMPTYTEXT = "No Text Given";

    public TextItem(int level, String string)
    {
        super(level);
        text = string;
    }

    public TextItem()
    {
        this(0, EMPTYTEXT);
    }

    public String getText()
    {
        return text == null ? "" : text;
    }

    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attrStr = new AttributedString(getText());
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;
    }

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

    public void draw(
            int x, int y, float scale, Graphics g,
            Style myStyle, ImageObserver o
    )
    {
        if (text == null || text.isEmpty())
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

    public String toString()
    {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }
}
