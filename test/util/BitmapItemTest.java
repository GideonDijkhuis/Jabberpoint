package util;

import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.util.BitmapItem;
import main.java.com.jabberpoint.util.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

    private BitmapItem bitmapItem;

    @BeforeEach
    void setUp() {

        this.bitmapItem = new BitmapItem(1, "JabberPoint.jpg");
    }

    @Test
    void constructor_withLevelAndName_setsNameAndLevel() {
        assertEquals(1, this.bitmapItem.getLevel());
        assertEquals("JabberPoint.jpg", this.bitmapItem.getName());
    }

    @Test
    void constructor_default_setsNullNameAndLevelZero() {
        BitmapItem defaultItem = new BitmapItem();
        assertEquals(0, defaultItem.getLevel());
        assertNull(defaultItem.getName());
    }

    @Test
    void getName_returnsName() {
        assertEquals("JabberPoint.jpg", this.bitmapItem.getName());
    }

    @Test
    void toString_returnsStringRepresentation() {
        assertEquals("main.java.com.jabberpoint.util.BitmapItem[1,JabberPoint.jpg]", this.bitmapItem.toString());
    }
}