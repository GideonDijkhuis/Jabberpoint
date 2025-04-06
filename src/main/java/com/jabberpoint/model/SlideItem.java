package main.java.com.jabberpoint.model;

import main.java.com.jabberpoint.util.*;
import main.java.com.jabberpoint.model.observer.*;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public abstract class SlideItem implements Subject
{
    private int level = 0;
    private final List<Observer> observers = new ArrayList<>();

    public SlideItem(int lev)
    {
        level = lev;
    }

    public SlideItem()
    {
        this(0);
    }

    // Geef het level
    public int getLevel()
    {
        return level;
    }

    // Update the level
    public void setLevel(int level)
    {
        this.level = level;
        notifyObservers();
    }

    public abstract Rectangle getBoundingBox(
            Graphics g,
            ImageObserver observer, float scale, Style style
    );

    public abstract void draw(
            int x, int y, float scale,
            Graphics g, Style style, ImageObserver observer
    );

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
