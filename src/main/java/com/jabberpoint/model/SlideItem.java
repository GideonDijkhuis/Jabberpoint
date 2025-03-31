package main.java.com.jabberpoint.model;

import main.java.com.jabberpoint.util.*;
import main.java.com.jabberpoint.model.observer.*;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/** <p>De abstracte klasse voor een item op een main.java.com.jabberpoint.model.Slide<p>
 * <p>Alle SlideItems hebben tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2023/09/30 Bram Huiskes - Updated to use Observer pattern
 * @version 1.8 Gideon Dijkhuis - Updated finals
*/

public abstract class SlideItem implements Subject {
	private int level = 0; // het level van het slideitem
	private final List<Observer> observers = new ArrayList<>();

	public SlideItem(int lev) {
		level = lev;
	}

	public SlideItem() {
		this(0);
	}

	// Geef het level
	public int getLevel() {
		return level;
	}
	
	// Update the level
	public void setLevel(int level) {
		this.level = level;
		notifyObservers();
	}

	// Geef de bounding box
	public abstract Rectangle getBoundingBox(Graphics g, 
			ImageObserver observer, float scale, Style style);

	// teken het item
	public abstract void draw(int x, int y, float scale,
                              Graphics g, Style style, ImageObserver observer);
                              
	// Observer pattern methods
	@Override
	public void registerObserver(Observer observer) {
		if (observer != null && !observers.contains(observer)) {
			observers.add(observer);
		}
	}
	
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	@Override
	public void notifyObservers(Object data) {
		for (Observer observer : observers) {
			observer.update(this, data);
		}
	}
}
