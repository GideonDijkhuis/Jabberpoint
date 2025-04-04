package model;

import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.model.SlideItem;
import main.java.com.jabberpoint.ui.SlideViewerComponent;
import main.java.com.jabberpoint.ui.SlideViewerFrame;
import main.java.com.jabberpoint.util.Style;
import main.java.com.jabberpoint.util.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest {

    private Slide slide;

    @BeforeEach
    void setUp() {
        // Prepare
        this.slide = new Slide();
        Presentation presentation = Presentation.getInstance();
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, SlideViewerFrame.getInstance("Test", presentation));
        this.slide.registerObserver(slideViewerComponent);
    }

    @Test
    void append_slideItem_itemAdded() {
        // Prepare
        SlideItem item = new TextItem(1, "Test Item");

        // Act
        this.slide.append(item);

        // Assert
        assertEquals(1, this.slide.getSize());
        assertEquals(item, this.slide.getSlideItem(0));
    }

    @Test
    void append_levelAndMessage_textItemAdded() {
        // Act
        this.slide.append(2, "Another Test Item");

        // Assert
        assertEquals(1, this.slide.getSize());
        assertTrue(this.slide.getSlideItem(0) instanceof TextItem);
        assertEquals("Another Test Item", ((TextItem) this.slide.getSlideItem(0)).getText());
    }

    @Test
    void getTitle_defaultTitle_returnsNull() {
        // Assert
        assertNull(this.slide.getTitle());
    }

    @Test
    void setTitle_setsTitle_correctTitle() {
        // Act
        this.slide.setTitle("Test Slide Title");

        // Assert
        assertEquals("Test Slide Title", this.slide.getTitle());
    }

    @Test
    void getSlideItem_validIndex_returnsItem() {
        // Prepare
        SlideItem item = new TextItem(1, "Test Item");
        this.slide.append(item);

        // Assert
        assertEquals(item, this.slide.getSlideItem(0));
    }

    @Test
    void getSlideItem_invalidIndex_throwsException() {
        // Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.slide.getSlideItem(1));
    }

    @Test
    void getSlideItems_returnsVectorOfItems() {
        // Prepare
        SlideItem item1 = new TextItem(1, "Item 1");
        SlideItem item2 = new TextItem(2, "Item 2");
        this.slide.append(item1);
        this.slide.append(item2);

        // Act
        Vector<SlideItem> items = this.slide.getSlideItems();

        // Assert
        assertEquals(2, items.size());
        assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));
    }

    @Test
    void getSize_emptySlide_returnsZero() {
        // Assert
        assertEquals(0, this.slide.getSize());
    }
}