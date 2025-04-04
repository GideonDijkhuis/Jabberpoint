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
    protected String title; // de titel wordt apart bewaard
    protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard
    private final List<Observer> observers = new ArrayList<>();

    public Slide()
    {
        items = new Vector<SlideItem>();
    }

    // Voeg een main.java.com.jabberpoint.model.SlideItem toe
    public void append(SlideItem anItem)
    {
        items.addElement(anItem);
        notifyObservers();
    }

    // geef de titel van de slide
    public String getTitle()
    {
        return title;
    }

    // verander de titel van de slide
    public void setTitle(String newTitle)
    {
        title = newTitle;
        notifyObservers();
    }

    // Maak een main.java.com.jabberpoint.util.TextItem van String, en voeg het main.java.com.jabberpoint.util.TextItem toe
    public void append(int level, String message)
    {
        append(new TextItem(level, message));
    }

    // geef het betreffende main.java.com.jabberpoint.model.SlideItem
    public SlideItem getSlideItem(int number)
    {
        return (SlideItem) items.elementAt(number);
    }

    // geef alle SlideItems in een Vector
    public Vector<SlideItem> getSlideItems()
    {
        return items;
    }

    // geef de afmeting van de main.java.com.jabberpoint.model.Slide
    public int getSize()
    {
        return items.size();
    }

    // teken de slide
    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = getScale(area);
        int y = area.y;
        // De titel wordt apart behandeld
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

    // geef de schaal om de slide te kunnen tekenen
    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }

    // Observer pattern methods
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
