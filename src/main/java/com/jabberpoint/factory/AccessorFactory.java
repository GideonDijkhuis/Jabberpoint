package main.java.com.jabberpoint.factory;

import main.java.com.jabberpoint.accessor.*;

public class AccessorFactory
{
    private static AccessorFactory instance;

    private AccessorFactory()
    {
        // Private constructor for singleton
    }

    public static AccessorFactory getInstance()
    {
        if (instance == null)
        {
            instance = new AccessorFactory();
        }
        return instance;
    }

    public Accessor createXMLAccessor()
    {
        return new XMLAccessor();
    }

    public Accessor createDemoAccessor()
    {
        return new DemoPresentation();
    }

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
