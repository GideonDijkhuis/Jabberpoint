package main.java.com.jabberpoint.model;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jabberpoint.model.observer.Observer;
import main.java.com.jabberpoint.model.observer.Subject;
import main.java.com.jabberpoint.ui.SlideViewerComponent;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Handles only presentation data management - Open/Closed
 * Principle: Can be extended with new functionality without modification - Liskov Substitution Principle: Properly
 * implements Subject interface methods - Interface Segregation Principle: Implements only necessary Subject methods -
 * Dependency Inversion Principle: Depends on abstractions (Observer interface)
 *
 * Represents a presentation with slides. Implements the Singleton pattern and Observer pattern.
 */
public class Presentation implements Subject {
    private String showTitle;
    private ArrayList<Slide> showList = null;
    private int currentSlideNumber = 0;
    private SlideViewerComponent slideViewComponent = null;
    private static Presentation instance;
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Private constructor for singleton pattern.
     */
    private Presentation() {
        this.slideViewComponent = null;
        clear();
    }

    /**
     * Gets the singleton instance of the Presentation.
     *
     * @return The singleton instance
     */
    public static Presentation getInstance() {
        if (instance == null) {
            instance = new Presentation();
        }

        return instance;
    }

    /**
     * Gets the number of slides in the presentation.
     *
     * @return The number of slides
     */
    public int getSize() {
        return this.showList.size();
    }

    /**
     * Gets the title of the presentation.
     *
     * @return The title
     */
    public String getTitle() {
        return this.showTitle;
    }

    /**
     * Sets the title of the presentation.
     *
     * @param nt The new title
     */
    public void setTitle(String nt) {
        this.showTitle = nt;
        notifyObservers();
    }

    /**
     * Sets the slide viewer component for this presentation.
     *
     * @param slideViewerComponent The slide viewer component
     */
    public void setShowView(SlideViewerComponent slideViewerComponent) {
        this.slideViewComponent = slideViewerComponent;
    }

    /**
     * Gets the current slide number.
     *
     * @return The current slide number
     */
    public int getSlideNumber() {
        return this.currentSlideNumber;
    }

    /**
     * Sets the current slide number.
     *
     * @param slideNr The slide number to set
     */
    public void setSlideNumber(int slideNr) {
        if (!isSlideNRAvailable(slideNr)) {
            return;
        }

        this.currentSlideNumber = slideNr;
        notifyObservers(getCurrentSlide());
    }

    /**
     * Navigates to the previous slide.
     */
    public void prevSlide() {
        if (isSlideNRAvailable(this.currentSlideNumber - 1)) {
            setSlideNumber(this.currentSlideNumber - 1);
        }
    }

    /**
     * Navigates to the next slide.
     */
    public void nextSlide() {
        if (this.isSlideNRAvailable(this.currentSlideNumber + 1)) {
            setSlideNumber(this.currentSlideNumber + 1);
        }
    }

    /**
     * Checks if the given slide number is available.
     *
     * @param slideNr The slide number to check
     * @return True if the slide number is available
     */
    public boolean isSlideNRAvailable(int slideNr) {
        return slideNr < this.showList.size() && slideNr >= 0;
    }

    /**
     * Clears the presentation.
     */
    public void clear() {
        this.showList = new ArrayList<Slide>();
        setSlideNumber(-1);
    }

    /**
     * Appends a slide to the presentation.
     *
     * @param slide The slide to append
     */
    public void append(Slide slide) {
        this.showList.add(slide);
        notifyObservers();
    }

    /**
     * Gets a slide by its number.
     *
     * @param number The slide number
     * @return The slide, or null if the number is invalid
     */
    public Slide getSlide(int number) {
        if (number < 0 || number >= getSize()) {
            return null;
        }
        return showList.get(number);
    }

    /**
     * Gets the current slide.
     *
     * @return The current slide
     */
    public Slide getCurrentSlide() {
        return getSlide(this.currentSlideNumber);
    }

    // Observer pattern methods
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

        if (this.slideViewComponent != null) {
            this.slideViewComponent.update(this, getCurrentSlide());
        }
    }
}
