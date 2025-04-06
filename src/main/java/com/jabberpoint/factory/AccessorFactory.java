package main.java.com.jabberpoint.factory;

import main.java.com.jabberpoint.accessor.Accessor;
import main.java.com.jabberpoint.accessor.DemoPresentation;
import main.java.com.jabberpoint.accessor.XMLAccessor;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for creating accessor objects -
 * Open/Closed Principle: New accessor types can be added without modifying existing code - Dependency Inversion
 * Principle: Depends on the Accessor abstraction, not concrete implementations - Interface Segregation Principle:
 * Clients only need to work with the Accessor interface
 *
 * Factory for creating different types of accessors for presentations. Implements the Singleton pattern.
 */
public class AccessorFactory {
    private static AccessorFactory instance;

    /**
     * Private constructor for singleton pattern.
     */
    private AccessorFactory() {
        // Private constructor for singleton
    }

    /**
     * Gets the singleton instance of the AccessorFactory.
     *
     * @return The singleton instance
     */
    public static AccessorFactory getInstance() {
        if (instance == null) {
            instance = new AccessorFactory();
        }
        return instance;
    }

    /**
     * Creates an XMLAccessor for XML file operations.
     *
     * @return A new XMLAccessor instance
     */
    public Accessor createXMLAccessor() {
        return new XMLAccessor();
    }

    /**
     * Creates a DemoPresentation accessor for demo content.
     *
     * @return A new DemoPresentation instance
     */
    public Accessor createDemoAccessor() {
        return new DemoPresentation();
    }

    /**
     * Returns the appropriate accessor based on the filename.
     *
     * @param filename The name of the file to access
     * @return An accessor appropriate for the file type
     */
    public Accessor getAccessorForFile(String filename) {
        if (filename == null || filename.isEmpty() || filename.equals(Accessor.DEMO_NAME)) {
            return this.createDemoAccessor();
        }
        else if (filename.endsWith(".xml")) {
            return this.createXMLAccessor();
        }
        // Default to XML accessor
        return this.createXMLAccessor();
    }
}
