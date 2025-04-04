package accessor;

import main.java.com.jabberpoint.accessor.DemoPresentation;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.util.BitmapItem;
import main.java.com.jabberpoint.util.Style;
import main.java.com.jabberpoint.util.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DemoPresentationTest {

    private DemoPresentation demoPresentation;
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        Style.createStyles();

        this.demoPresentation = new DemoPresentation();
        this.presentation = Presentation.getInstance();
        this.presentation.clear();
    }

    @Test
    void loadFile_loadsDemoPresentation_correctPresentation() {
        //Prepare
        this.demoPresentation.loadFile(this.presentation, "dummyFilename");

        //Act
        Slide firstSlide = this.presentation.getSlide(0);
        Slide lastSlide = this.presentation.getSlide(2);

        //Act + Assert
        assertEquals("Demo Presentation", this.presentation.getTitle());
        assertEquals(3, this.presentation.getSize());

        assertEquals("JabberPoint", firstSlide.getTitle());
        assertEquals(3, firstSlide.getSlideItems().size());
        assertTrue(firstSlide.getSlideItems().get(0) instanceof TextItem);
        assertEquals("The Java presentation tool", ((TextItem) firstSlide.getSlideItems().get(0)).getText());

        assertEquals("The third slide", lastSlide.getTitle());
        assertEquals(5, lastSlide.getSlideItems().size());
        assertTrue(lastSlide.getSlideItems().get(4) instanceof BitmapItem);
        assertEquals("JabberPoint.jpg", ((BitmapItem) lastSlide.getSlideItems().get(4)).getName());
    }

    @Test
    void saveFile_throwsIOException_exceptionThrown() {
        //Prepare + Act + Assert
        assertThrows(IOException.class, () -> this.demoPresentation.saveFile(this.presentation, "dummyFilename"));
    }
}