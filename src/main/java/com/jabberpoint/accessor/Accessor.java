package main.java.com.jabberpoint.accessor;

import java.io.IOException;

import main.java.com.jabberpoint.model.Presentation;

/**
 * SOLID Principles Applied:
 * - Single Responsibility Principle: Each accessor handles one type of data source
 * - Open/Closed Principle: New accessors can be added without modifying existing ones
 * - Liskov Substitution Principle: All accessors can be used interchangeably
 * - Dependency Inversion Principle: High-level modules depend on this abstraction
 *
 * Abstract base class for presentation data accessors.
 */
public abstract class Accessor
{
    public static final String DEMO_NAME = "Demo presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    /**
     * Creates a new accessor.
     */
    public Accessor()
    {
    }

    /**
     * Gets a demo accessor for demonstration purposes.
     *
     * @return A demo accessor
     */
    public static Accessor getDemoAccessor()
    {
        return new DemoPresentation();
    }

    /**
     * Loads a presentation from a file.
     *
     * @param presentation The presentation to load data into
     * @param filename The name of the file to load from
     * @throws IOException If an I/O error occurs
     */
    public abstract void loadFile(Presentation presentation, String filename) throws IOException;

    /**
     * Saves a presentation to a file.
     *
     * @param presentation The presentation to save
     * @param filename The name of the file to save to
     * @throws IOException If an I/O error occurs
     */
    public abstract void saveFile(Presentation presentation, String filename) throws IOException;
}
