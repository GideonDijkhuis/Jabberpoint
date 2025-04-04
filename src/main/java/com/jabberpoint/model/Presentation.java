package main.java.com.jabberpoint.model;

import main.java.com.jabberpoint.ui.*;
import main.java.com.jabberpoint.model.observer.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>main.java.com.jabberpoint.model.Presentation houdt de slides in de presentatie bij.</p>
 * <p>Er is slechts een instantie van deze klasse aanwezig.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.9 Gideon Dijkhuis - Updated slideNr checks
 */

public class Presentation implements Subject
{
    private String showTitle; // de titel van de presentatie
    private ArrayList<Slide> showList = null; // een ArrayList met de Slides
    private int currentSlideNumber = 0; // het slidenummer van de huidige main.java.com.jabberpoint.model.Slide
    private SlideViewerComponent slideViewComponent = null; // de viewcomponent voor de Slides
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

    // geef het nummer van de huidige slide
    public int getSlideNumber()
    {
        return currentSlideNumber;
    }

    // verander het huidige-slide-nummer en laat het aan het window weten.
    public void setSlideNumber(int slideNr)
    {
        if (!isSlideNRAvailable(slideNr))
        {
            return;
        }

        currentSlideNumber = slideNr;
        notifyObservers(getCurrentSlide());
    }

    // ga naar de vorige slide tenzij je aan het begin van de presentatie bent
    public void prevSlide()
    {
        if (isSlideNRAvailable(this.currentSlideNumber - 1))
        {
            setSlideNumber(currentSlideNumber - 1);
        }
    }

    // Ga naar de volgende slide tenzij je aan het einde van de presentatie bent.
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

    // Verwijder de presentatie, om klaar te zijn voor de volgende
    public void clear()
    {
        showList = new ArrayList<Slide>();
        setSlideNumber(-1);
    }

    // Voeg een slide toe aan de presentatie
    public void append(Slide slide)
    {
        showList.add(slide);
        notifyObservers();
    }

    // Geef een slide met een bepaald slidenummer
    public Slide getSlide(int number)
    {
        if (number < 0 || number >= getSize())
        {
            return null;
        }
        return showList.get(number);
    }

    // Geef de huidige main.java.com.jabberpoint.model.Slide
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

        // For backward compatibility
        if (slideViewComponent != null)
        {
            slideViewComponent.update(this, getCurrentSlide());
        }
    }
}
