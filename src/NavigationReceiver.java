/**
 * <p>NavigationReceiver implements the Receiver interface for navigation commands</p>
 * <p>This class knows how to perform navigation operations on a presentation</p>
 * @author Bram Huiskes
 * @version 1.0
 */
public class NavigationReceiver implements Receiver {
    private Presentation presentation;
    
    /**
     * Constructor for NavigationReceiver
     * @param presentation The presentation to navigate through
     */
    public NavigationReceiver(Presentation presentation) {
        this.presentation = presentation;
    }
    
    /**
     * Go to the next slide
     */
    public void nextSlide() {
        presentation.nextSlide();
    }
    
    /**
     * Go to the previous slide
     */
    public void prevSlide() {
        presentation.prevSlide();
    }
    
    /**
     * Go to a specific slide number
     * @param slideNumber The slide number to go to (0-based)
     */
    public void gotoSlide(int slideNumber) {
        presentation.setSlideNumber(slideNumber);
    }
} 