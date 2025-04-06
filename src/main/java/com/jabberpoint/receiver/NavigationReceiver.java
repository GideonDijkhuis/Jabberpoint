package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.model.Presentation;

/**
 * Receiver for navigation commands within a presentation.
 *
 * SOLID Principles: - Single Responsibility Principle: Handles only navigation-related commands for the presentation. -
 * Open/Closed Principle: New navigation commands can be added without modifying existing ones. - Liskov Substitution
 * Principle: Properly implements the Receiver interface. - Interface Segregation Principle: Implements the Receiver
 * interface with methods relevant to navigation. - Dependency Inversion Principle: Depends on abstractions (Receiver
 * interface) rather than concrete implementations.
 */
public class NavigationReceiver implements Receiver {
    private final Presentation presentation = Presentation.getInstance();

    /**
     * Creates a new NavigationReceiver.
     */
    public NavigationReceiver() {
        // Default constructor
    }

    /**
     * Navigates to the next slide in the presentation.
     */
    public void nextSlide() {
        this.presentation.nextSlide();
    }

    /**
     * Navigates to the previous slide in the presentation.
     */
    public void prevSlide() {
        this.presentation.prevSlide();
    }

    /**
     * Navigates to a specific slide in the presentation.
     *
     * @param slideNumber The slide number to navigate to
     */
    public void gotoSlide(int slideNumber) {
        this.presentation.setSlideNumber(slideNumber);
    }
} 