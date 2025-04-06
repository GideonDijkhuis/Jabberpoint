package main.java.com.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import main.java.com.jabberpoint.model.observer.Observer;
import main.java.com.jabberpoint.model.observer.Subject;
import main.java.com.jabberpoint.util.Style;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Base class for slide items with common behavior -
 * Open/Closed Principle: Can be extended with new item types without modification - Liskov Substitution Principle: All
 * derived classes can be used interchangeably - Interface Segregation Principle: Implements only necessary Subject
 * methods - Dependency Inversion Principle: Depends on abstractions (Subject interface)
 *
 * Abstract base class for all slide items in a presentation.
 */
public abstract class SlideItem implements Subject {
    private int level = 0;
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Creates a slide item with the specified level.
     *
     * @param lev The level of the item
     */
    public SlideItem(int lev) {
        this.level = lev;
    }

    /**
     * Creates a slide item with default level 0.
     */
    public SlideItem() {
        this(0);
    }

    /**
     * Gets the level of the slide item.
     *
     * @return The level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the level of the slide item.
     *
     * @param level The new level
     */
    public void setLevel(int level) {
        this.level = level;
        notifyObservers();
    }

    /**
     * Gets the bounding box for this item.
     *
     * @param g        The graphics context
     * @param observer The image observer
     * @param scale    The scale factor
     * @param style    The style to apply
     * @return The bounding rectangle
     */
    public abstract Rectangle getBoundingBox(
            Graphics g,
            ImageObserver observer, float scale, Style style
    );

    /**
     * Draws the item on the screen.
     *
     * @param x        The x-coordinate
     * @param y        The y-coordinate
     * @param scale    The scale factor
     * @param g        The graphics context
     * @param style    The style to apply
     * @param observer The image observer
     */
    public abstract void draw(
            int x, int y, float scale,
            Graphics g, Style style, ImageObserver observer
    );

    @Override
    public void registerObserver(Observer observer) {
        if (observer != null && !this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(Object data) {
        for (Observer observer : this.observers) {
            observer.update(this, data);
        }
    }
}
