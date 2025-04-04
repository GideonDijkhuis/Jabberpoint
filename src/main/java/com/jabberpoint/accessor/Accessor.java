package main.java.com.jabberpoint.accessor;

import main.java.com.jabberpoint.model.*;

import java.io.IOException;

/**
 * <p>Een main.java.com.jabberpoint.accessor.Accessor maakt het mogelijk om gegevens voor een presentatie
 * te lezen of te schrijven.</p>
 * <p>Niet-abstracte subklassen moeten de load en save methoden implementeren.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.7 2023/09/29 Bram Huiskes - Updated for Factory Method pattern
 */

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
