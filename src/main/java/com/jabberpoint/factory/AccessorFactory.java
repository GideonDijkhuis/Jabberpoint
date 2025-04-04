package main.java.com.jabberpoint.factory;

import main.java.com.jabberpoint.accessor.*;

/**
 * <p>main.java.com.jabberpoint.factory.AccessorFactory creates accessor objects</p>
 * <p>This class centralizes the creation of main.java.com.jabberpoint.accessor.Accessor objects</p>
 * <p>This is an implementation of the Factory Method pattern</p>
 *
 * @author Bram Huiskes
 * @version 1.0
 */
public class AccessorFactory
{
    private static AccessorFactory instance;

    private AccessorFactory()
    {
        // Private constructor for singleton
    }

    /**
     * Get the singleton instance of main.java.com.jabberpoint.factory.AccessorFactory
     *
     * @return The main.java.com.jabberpoint.factory.AccessorFactory instance
     */
    public static AccessorFactory getInstance()
    {
        if (instance == null)
        {
            instance = new AccessorFactory();
        }
        return instance;
    }

    /**
     * Create an main.java.com.jabberpoint.accessor.XMLAccessor
     *
     * @return A new main.java.com.jabberpoint.accessor.XMLAccessor
     */
    public Accessor createXMLAccessor()
    {
        return new XMLAccessor();
    }

    /**
     * Create a main.java.com.jabberpoint.accessor.DemoPresentation accessor
     *
     * @return A new main.java.com.jabberpoint.accessor.DemoPresentation
     */
    public Accessor createDemoAccessor()
    {
        return new DemoPresentation();
    }

    /**
     * Get an accessor based on the file extension
     *
     * @param filename The filename to determine the accessor type
     * @return The appropriate accessor
     */
    public Accessor getAccessorForFile(String filename)
    {
        if (filename == null || filename.isEmpty() || filename.equals(Accessor.DEMO_NAME))
        {
            return createDemoAccessor();
        }
        else if (filename.endsWith(".xml"))
        {
            return createXMLAccessor();
        }
        // Default to XML accessor
        return createXMLAccessor();
    }
}
