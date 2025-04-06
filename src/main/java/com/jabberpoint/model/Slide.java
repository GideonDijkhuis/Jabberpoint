package main.java.com.jabberpoint.model;

import main.java.com.jabberpoint.util.*;
import main.java.com.jabberpoint.model.observer.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.8 Gideon Dijkhuis - Updated finals
 */

public class Slide implements Subject
{
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title;
    protected Vector<SlideItem> items;
    private final List<Observer> observers = new ArrayList<>();

    public Slide()
    {
        items = new Vector<SlideItem>();
    }

    public void append(SlideItem anItem)
    {
        items.addElement(anItem);
        notifyObservers();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
        notifyObservers();
    }

    public void append(int level, String message)
    {
        append(new TextItem(level, message));
    }

    public SlideItem getSlideItem(int number)
    {
        return (SlideItem) items.elementAt(number);
    }

    public Vector<SlideItem> getSlideItems()
    {
        return items;
    }

    public int getSize()
    {
        return items.size();
    }

    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = getScale(area);
        int y = area.y;
        SlideItem slideItem = new TextItem(0, getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style, view);
        y += slideItem.getBoundingBox(g, view, scale, style).height;
        for (int number = 0; number < getSize(); number++)
        {
            slideItem = (SlideItem) getSlideItems().elementAt(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }

    @Override
    public void registerObserver(Observer observer)
    {
        if (observer != null && !observers.contains(observer))
        {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(Object data)
    {
        for (Observer observer : observers)
        {
            observer.update(this, data);
        }
    }
}
