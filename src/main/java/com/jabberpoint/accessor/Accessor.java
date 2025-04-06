package main.java.com.jabberpoint.accessor;

import main.java.com.jabberpoint.model.*;

import java.io.IOException;

public abstract class Accessor
{
    public static final String DEMO_NAME = "Demo presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    public Accessor()
    {
    }

    public static Accessor getDemoAccessor()
    {
        return new DemoPresentation();
    }

    public abstract void loadFile(Presentation presentation, String filename) throws IOException;

    public abstract void saveFile(Presentation presentation, String filename) throws IOException;
}
