package model;

import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.ui.SlideViewerComponent;

import main.java.com.jabberpoint.ui.SlideViewerFrame;
import main.java.com.jabberpoint.util.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest {

    private Presentation presentation;
    private SlideViewerComponent slideViewerComponent;


    @BeforeEach
    void setUp() {
        Style.createStyles();

        this.presentation = Presentation.getInstance();
        this.presentation.clear();
        this.slideViewerComponent = new SlideViewerComponent(presentation, SlideViewerFrame.getInstance("test", this.presentation));
        this.presentation.registerObserver(this.slideViewerComponent);
    }

    @Test
    void getInstance_returnsSingleton_sameInstance() {
        // Prepare
        Presentation presentation1 = Presentation.getInstance();
        Presentation presentation2 = Presentation.getInstance();

        // Assert
        assertSame(presentation1, presentation2);
    }

    @Test
    void getSize_emptyPresentation_returnsZero() {
        // Assert
        assertEquals(0, this.presentation.getSize());
    }

    @Test
    void setTitle_setsTitle_correctTitle() {
        // Act
        this.presentation.setTitle("Test Presentation");

        // Assert
        assertEquals("Test Presentation", this.presentation.getTitle());
    }

    @Test
    void setSlideNumber_validSlideNumber_setsCurrentSlide() {
        // Prepare
        Slide slide = new Slide();
        this.presentation.append(slide);

        // Act
        this.presentation.setSlideNumber(0);

        // Assert
        assertEquals(0, this.presentation.getSlideNumber());
        assertEquals(slide, this.slideViewerComponent.getSlide());
    }

    @Test
    void setSlideNumber_invalidSlideNumber_doesNotChangeCurrentSlide() {
        // Prepare
        Slide slide = new Slide();
        this.presentation.append(slide);

        // Act
        this.presentation.setSlideNumber(1);

        // Assert
        assertEquals(0, this.presentation.getSlideNumber());
    }

    @Test
    void prevSlide_validPreviousSlide_setsPreviousSlide() {
        // Prepare
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        this.presentation.append(slide1);
        this.presentation.append(slide2);
        this.presentation.setSlideNumber(1);

        // Act
        this.presentation.prevSlide();

        // Assert
        assertEquals(0, this.presentation.getSlideNumber());
        assertEquals(slide1, this.slideViewerComponent.getSlide()); // Controleer de slide in SlideViewerComponent
    }

    @Test
    void prevSlide_noPreviousSlide_doesNotChangeCurrentSlide() {
        // Act
        this.presentation.prevSlide();

        // Assert
        assertEquals(0, this.presentation.getSlideNumber());
    }

    @Test
    void nextSlide_validNextSlide_setsNextSlide() {
        // Prepare
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        this.presentation.append(slide1);
        this.presentation.append(slide2);
        this.presentation.setSlideNumber(0);

        // Act
        this.presentation.nextSlide();

        // Assert
        assertEquals(1, this.presentation.getSlideNumber());
        assertEquals(slide2, this.slideViewerComponent.getSlide()); // Controleer de slide in SlideViewerComponent
    }

    @Test
    void nextSlide_noNextSlide_doesNotChangeCurrentSlide() {
        // Prepare
        Slide slide = new Slide();
        this.presentation.append(slide);
        this.presentation.setSlideNumber(0);

        // Act
        this.presentation.nextSlide();

        // Assert
        assertEquals(0, this.presentation.getSlideNumber());
    }

    @Test
    void clear_clearsPresentation_emptyPresentation() {
        // Prepare
        Slide slide = new Slide();
        this.presentation.append(slide);

        // Act
        this.presentation.clear();

        // Assert
        assertEquals(0, this.presentation.getSize());
        assertEquals(0, this.presentation.getSlideNumber());
    }

    @Test
    void append_addsSlide_slideAdded() {
        // Prepare
        Slide slide = new Slide();

        // Act
        this.presentation.append(slide);

        // Assert
        assertEquals(1, this.presentation.getSize());
        assertEquals(slide, this.presentation.getSlide(0));
    }

    @Test
    void getSlide_validSlideNumber_returnsSlide() {
        // Prepare
        Slide slide = new Slide();
        this.presentation.append(slide);

        // Assert
        assertEquals(slide, this.presentation.getSlide(0));
    }

    @Test
    void getSlide_invalidSlideNumber_returnsNull() {
        // Assert
        assertNull(this.presentation.getSlide(1));
    }

    @Test
    void getCurrentSlide_validCurrentSlide_returnsCurrentSlide() {
        // Prepare
        Slide slide = new Slide();
        this.presentation.append(slide);
        this.presentation.setSlideNumber(0);

        // Assert
        assertEquals(slide, this.presentation.getCurrentSlide());
    }

    @Test
    void getCurrentSlide_noCurrentSlide_returnsNull() {
        // Assert
        assertNull(this.presentation.getCurrentSlide());
    }

    @Test
    void registerObserver_addsObserver_observerRegistered() {
        // Prepare
        SlideViewerComponent newSlideViewerComponent = new SlideViewerComponent(this.presentation, SlideViewerFrame.getInstance("Test", this.presentation));
        Slide slide = new Slide();
        this.presentation.append(slide);

        // Act
        this.presentation.registerObserver(newSlideViewerComponent);
        this.presentation.notifyObservers(slide);

        // Assert
        // Check if the new SlideViewerComponent was updated with the correct slide.
        assertEquals(slide, newSlideViewerComponent.getSlide());
    }

    @Test
    void removeObserver_removesObserver_observerRemoved() {
        // Prepare
        Slide slide = new Slide();
        slide.setTitle("Slide 1");
        this.presentation.append(slide);

        // Act
        this.presentation.removeObserver(this.slideViewerComponent);
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        this.presentation.notifyObservers(slide2);

        // Assert
        assertNotEquals(slide2, this.slideViewerComponent.getSlide());
    }
}