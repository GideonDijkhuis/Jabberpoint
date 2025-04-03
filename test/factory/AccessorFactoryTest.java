package factory;


import main.java.com.jabberpoint.accessor.*;
import main.java.com.jabberpoint.factory.AccessorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessorFactoryTest {

    @Test
    void getInstance_returnsSingleton_sameInstance() {
        //Prepare
        AccessorFactory factory1 = AccessorFactory.getInstance();
        AccessorFactory factory2 = AccessorFactory.getInstance();

        //Assert
        assertSame(factory1, factory2);
    }

    @Test
    void createXMLAccessor_returnsXMLAccessor_correctInstance() {
        //Prepare
        AccessorFactory factory = AccessorFactory.getInstance();
        Accessor accessor = factory.createXMLAccessor();

        //Assert
        assertTrue(accessor instanceof XMLAccessor);
    }

    @Test
    void createDemoAccessor_returnsDemoPresentation_correctInstance() {
        //Prepare
        AccessorFactory factory = AccessorFactory.getInstance();
        Accessor accessor = factory.createDemoAccessor();

        //Assert
        assertTrue(accessor instanceof DemoPresentation);
    }

    @Test
    void getAccessorForFile_xmlFile_returnsXMLAccessor() {
        //Prepare
        AccessorFactory factory = AccessorFactory.getInstance();
        Accessor accessor = factory.getAccessorForFile("test.xml");

        //Assert
        assertTrue(accessor instanceof XMLAccessor);
    }

    @Test
    void getAccessorForFile_demoName_returnsDemoAccessor() {
        //Prepare
        AccessorFactory factory = AccessorFactory.getInstance();
        Accessor accessor = factory.getAccessorForFile(Accessor.DEMO_NAME);

        //Assert
        assertTrue(accessor instanceof DemoPresentation);
    }

    @Test
    void getAccessorForFile_nullOrEmpty_returnsDemoAccessor() {
        //Prepare
        AccessorFactory factory = AccessorFactory.getInstance();
        Accessor accessor1 = factory.getAccessorForFile(null);
        Accessor accessor2 = factory.getAccessorForFile("");

        //Assert
        assertTrue(accessor1 instanceof DemoPresentation);
        assertTrue(accessor2 instanceof DemoPresentation);
    }

    @Test
    void getAccessorForFile_unknownExtension_returnsXMLAccessor() {
        //Prepare
        AccessorFactory factory = AccessorFactory.getInstance();
        Accessor accessor = factory.getAccessorForFile("test.txt");

        //Assert
        assertTrue(accessor instanceof XMLAccessor);
    }
}
