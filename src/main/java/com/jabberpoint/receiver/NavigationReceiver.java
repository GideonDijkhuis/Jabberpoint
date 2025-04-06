package main.java.com.jabberpoint.receiver;

import main.java.com.jabberpoint.model.*;

public class NavigationReceiver implements Receiver
{
    private final Presentation presentation = Presentation.getInstance();

    public NavigationReceiver()
    {

    }

    public void nextSlide()
    {
        presentation.nextSlide();
    }

    public void prevSlide()
    {
        presentation.prevSlide();
    }

    public void gotoSlide(int slideNumber)
    {
        presentation.setSlideNumber(slideNumber);
    }
} 