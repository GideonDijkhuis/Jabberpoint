package main.java.com.jabberpoint.model;

import main.java.com.jabberpoint.ui.*;
import main.java.com.jabberpoint.model.observer.*;

import java.util.ArrayList;
import java.util.List;

public class Presentation implements Subject
{
    private String showTitle;
    private ArrayList<Slide> showList = null;
    private int currentSlideNumber = 0;
    private SlideViewerComponent slideViewComponent = null;
    private static Presentation instance;
    private final List<Observer> observers = new ArrayList<>();

    private Presentation()
    {
        slideViewComponent = null;
        clear();
    }

    public static Presentation getInstance()
    {
        if (instance == null)
        {
            instance = new Presentation();
        }

        return instance;
    }

    public int getSize()
    {
        return showList.size();
    }

    public String getTitle()
    {
        return showTitle;
    }

    public void setTitle(String nt)
    {
        showTitle = nt;
        notifyObservers();
    }

    public void setShowView(SlideViewerComponent slideViewerComponent)
    {
        this.slideViewComponent = slideViewerComponent;
    }

    public int getSlideNumber()
    {
        return currentSlideNumber;
    }

    public void setSlideNumber(int slideNr)
    {
        if (!isSlideNRAvailable(slideNr))
        {
            return;
        }

        currentSlideNumber = slideNr;
        notifyObservers(getCurrentSlide());
    }

    public void prevSlide()
    {
        if (isSlideNRAvailable(this.currentSlideNumber - 1))
        {
            setSlideNumber(currentSlideNumber - 1);
        }
    }

    public void nextSlide()
    {
        if (this.isSlideNRAvailable(this.currentSlideNumber + 1))
        {
            setSlideNumber(this.currentSlideNumber + 1);
        }
    }

    public boolean isSlideNRAvailable(int slideNr)
    {
        return slideNr < this.showList.size() && slideNr >= 0;
    }

    public void clear()
    {
        showList = new ArrayList<Slide>();
        setSlideNumber(-1);
    }

    public void append(Slide slide)
    {
        showList.add(slide);
        notifyObservers();
    }

    public Slide getSlide(int number)
    {
        if (number < 0 || number >= getSize())
        {
            return null;
        }
        return showList.get(number);
    }

    public Slide getCurrentSlide()
    {
        return getSlide(currentSlideNumber);
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

        if (slideViewComponent != null)
        {
            slideViewComponent.update(this, getCurrentSlide());
        }
    }
}
