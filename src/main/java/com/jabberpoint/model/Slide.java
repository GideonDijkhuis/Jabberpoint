package main.java.com.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.java.com.jabberpoint.model.observer.Observer;
import main.java.com.jabberpoint.model.observer.Subject;
import main.java.com.jabberpoint.util.Style;
import main.java.com.jabberpoint.util.TextItem;

/**
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Manages only slide content and drawing
 * - Open/Closed Principle: Can be extended with new item types without modification
 * - Liskov Substitution Principle: Properly implements Subject interface methods
 * - Interface Segregation Principle: Implements only necessary Subject methods
 * - Dependency Inversion Principle: Depends on abstractions (SlideItem, Observer)
 *
 * A slide in a presentation with drawing functionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.8 Gideon Dijkhuis - Updated finals
 */
public class Slide implements Subject
{
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    protected String title;
    protected Vector<SlideItem> items;
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Creates a new empty slide.
     */
    public Slide()
    {
        this.items = new Vector<SlideItem>();
    }

    /**
     * Appends a slide item to this slide.
     * 
     * @param anItem The item to append
     */
    public void append(SlideItem anItem)
    {
        this.items.addElement(anItem);
        notifyObservers();
    }

    /**
     * Gets the title of this slide.
     * 
     * @return The title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Sets the title of this slide.
     * 
     * @param newTitle The new title
     */
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
        notifyObservers();
    }

    /**
     * Appends a text item with the specified level and message.
     * 
     * @param level The level of the text item
     * @param message The text message
     */
    public void append(int level, String message)
    {
        append(new TextItem(level, message));
    }

    /**
     * Gets a slide item at the specified index.
     * 
     * @param number The index of the item
     * @return The slide item
     */
    public SlideItem getSlideItem(int number)
    {
        return this.items.elementAt(number);
    }

    /**
     * Gets all slide items.
     * 
     * @return The vector of slide items
     */
    public Vector<SlideItem> getSlideItems()
    {
        return this.items;
    }

    /**
     * Gets the number of items in this slide.
     * 
     * @return The number of items
     */
    public int getSize()
    {
        return this.items.size();
    }

    /**
     * Draws the slide in the specified area.
     * 
     * @param g The graphics context
     * @param area The area to draw in
     * @param view The image observer
     */
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
            slideItem = getSlideItems().elementAt(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    /**
     * Calculates the scale to fit the slide in the specified area.
     * 
     * @param area The area to fit in
     * @return The scale factor
     */
    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }

    @Override
    public void registerObserver(Observer observer)
    {
        if (observer != null && !this.observers.contains(observer))
        {
            this.observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer)
    {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(Object data)
    {
        for (Observer observer : this.observers)
        {
            observer.update(this, data);
        }
    }
}
