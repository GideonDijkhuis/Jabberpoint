package util;

import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.util.Style;
import main.java.com.jabberpoint.util.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.text.AttributedString;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;

class TextItemTest {

    private TextItem textItem;

    @BeforeEach
    void setUp() {

        this.textItem = new TextItem(1, "Test Text");
    }

    @Test
    void constructor_withLevelAndString_setsTextAndLevel() {
        assertEquals(1, this.textItem.getLevel());
        assertEquals("Test Text", this.textItem.getText());
    }

    @Test
    void constructor_default_setsEmptyTextAndLevelZero() {
        TextItem defaultItem = new TextItem();
        assertEquals(0, defaultItem.getLevel());
        assertEquals("No Text Given", defaultItem.getText());
    }

    @Test
    void getText_returnsText() {
        assertEquals("Test Text", this.textItem.getText());
    }


    @Test
    void toString_returnsStringRepresentation() {
        assertEquals("main.java.com.jabberpoint.util.TextItem[1,Test Text]", this.textItem.toString());
    }
}