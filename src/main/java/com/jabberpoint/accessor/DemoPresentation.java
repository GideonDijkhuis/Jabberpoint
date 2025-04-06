package main.java.com.jabberpoint.accessor;

import java.io.IOException;

import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.util.BitmapItem;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for providing demo content -
 * Open/Closed Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements all
 * Accessor methods - Interface Segregation Principle: Implements only necessary Accessor methods - Dependency Inversion
 * Principle: Depends on abstractions not concrete implementations
 *
 * Accessor that provides a demonstration presentation with example slides.
 */
public class DemoPresentation extends Accessor {

    /**
     * Loads a demo presentation with sample content.
     *
     * @param presentation   The presentation to load data into
     * @param unusedFilename Not used in this implementation
     */
    public void loadFile(Presentation presentation, String unusedFilename) {
        presentation.setTitle("Demo Presentation");
        Slide slide;
        slide = new Slide();
        slide.setTitle("JabberPoint");
        slide.append(1, "The Java presentation tool");
        slide.append(2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append(2, "Copyright (c) 2000-now: Gert Florijn and Sylvia Stuurman");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Demonstration of levels and styles");
        slide.append(1, "Level 1");
        slide.append(2, "Level 2");
        slide.append(1, "Again level 1");
        slide.append(1, "Level 1 has style number 1");
        slide.append(2, "Level 2 has style number 2");
        slide.append(3, "This is how level 3 looks like");
        slide.append(4, "And this is level 4");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("The third slide");
        slide.append(1, "To open a new presentation,");
        slide.append(2, "use File->Open from the menu.");
        slide.append(1, " ");
        slide.append(1, "This is the end of the presentation.");
        slide.append(new BitmapItem(1, "JabberPoint.jpg"));
        presentation.append(slide);
    }

    /**
     * Not supported for demo presentations.
     *
     * @param presentation   The presentation to save
     * @param unusedFilename Not used in this implementation
     * @throws IOException Always thrown as this operation is not supported
     */
    public void saveFile(Presentation presentation, String unusedFilename) throws IOException {
        throw new IOException("Save As->Demo! called");
    }
}
