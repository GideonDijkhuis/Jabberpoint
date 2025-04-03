package accessor;

import main.java.com.jabberpoint.accessor.Accessor;
import main.java.com.jabberpoint.factory.AccessorFactory;
import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.util.BitmapItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {

    private Accessor xmlAccessor;
    private Presentation presentation;
    private String testFileName;

    @BeforeEach
    void setUp() {
        this.xmlAccessor = AccessorFactory.getInstance().createXMLAccessor();
        this.presentation = Presentation.getInstance();
        this.presentation.clear();
        this.testFileName = "testPresentation.xml";
    }

    @Test
    void loadFile_exampleXML_loadsPresentation() throws IOException {
        //Prepare
        String xmlContent = "<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">\n" +
                "<presentation>\n" +
                "    <showtitle>XML-Based Presentation for Jabberpoint</showtitle>\n" +
                "    <slide>\n" +
                "       <title>JabberPoint XML-Demo</title>\n" +
                "       <item kind=\"text\"  level=\"1\">Welkom bij Jabberpoint</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Copyright (c) 1996-2000 Ian Darwin</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Herschreven door</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Gert Florijn, Mark van Elswijk, Marc Evers</item>\n" +
                "       <item kind=\"text\"  level=\"2\">en Sylvia Stuurman</item>\n" +
                "       <item kind=\"text\"  level=\"1\">Navigatie:</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Volgende slide: PgDn or Enter</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Vorige slide: PgUp or up-arrow</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Stoppen: q or Q</item>\n" +
                "    </slide>\n" +
                "    <slide>\n" +
                "       <title>Een bijna lege slide</title>\n" +
                "       <item kind=\"text\"  level=\"1\"> </item>\n" +
                "    </slide>\n" +
                "    <slide>\n" +
                "       <title>Achtergrond</title>\n" +
                "       <item kind=\"text\"  level=\"1\">Jabberpoint is een oefening voor de</item>\n" +
                "       <item kind=\"text\"  level=\"1\">cursus Design patterns van de Open universiteit</item>\n" +
                "       <item kind=\"text\"  level=\"1\"> </item>\n" +
                "       <item kind=\"image\"  level=\"2\">logo-woordmerk_ou.gif</item>\n" +
                "       <item kind=\"text\"  level=\"1\"> </item>\n" +
                "       <item kind=\"text\"  level=\"1\">De oefening is ontwikeld door het SERC</item>\n" +
                "       <item kind=\"text\"  level=\"1\">in samenwerking met de Open universiteit </item>\n" +
                "       <item kind=\"text\"  level=\"1\"> </item>\n" +
                "       <item kind=\"image\"  level=\"2\">serclogo_fc.jpg</item>\n" +
                "    </slide>\n" +
                "    <slide>\n" +
                "       <title>JabberPoint: Levels en stijlen</title>\n" +
                "       <item kind=\"text\"  level=\"1\">Level 1</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Level 2</item>\n" +
                "       <item kind=\"text\"  level=\"1\">A Jabberful Point</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Level 2 heeft stijl 2</item>\n" +
                "       <item kind=\"text\"  level=\"3\">Zo ziet de stijl van level 3 er uit</item>\n" +
                "       <item kind=\"text\"  level=\"4\">En dit is level 4</item>\n" +
                "    </slide>\n" +
                "    <slide>\n" +
                "       <title>Laatste slide</title>\n" +
                "       <item kind=\"text\"  level=\"1\">Navigatie kan ook via het menu</item>\n" +
                "       <item kind=\"text\"  level=\"2\">Presentatie laden: File->Open</item>\n" +
                "       <item kind=\"text\"  level=\"1\"> </item>\n" +
                "       <item kind=\"text\"  level=\"1\">Dit is het einde van de presentatie.</item>\n" +
                "       <item kind=\"image\"  level=\"1\">JabberPoint.jpg</item>\n" +
                "       <item kind=\"text\"  level=\"1\">Tot ziens.</item>\n" +
                "    </slide>\n" +
                "</presentation>";

        this.createTestFile(xmlContent);
        this.xmlAccessor.loadFile(this.presentation, this.testFileName);

        //Act+Assert
        assertEquals("XML-Based Presentation for Jabberpoint", this.presentation.getTitle());
        assertEquals(5, this.presentation.getSize());

        Slide lastSlide = this.presentation.getSlide(4);
        assertEquals("Laatste slide", lastSlide.getTitle());
        assertEquals(6, lastSlide.getSlideItems().size());
        assertTrue(lastSlide.getSlideItems().get(4) instanceof BitmapItem);
        assertEquals("JabberPoint.jpg", ((BitmapItem) lastSlide.getSlideItems().get(4)).getName());
    }

    @Test
    void saveFile_presentationWithBitmap_savesXML() throws IOException, ParserConfigurationException, SAXException {
        //Prepare
        this.presentation.setTitle("Test Presentation");
        Slide slide = new Slide();
        slide.setTitle("Bitmap Slide");
        slide.append(new BitmapItem(1, "JabberPoint.jpg"));
        this.presentation.append(slide);
        this.xmlAccessor.saveFile(this.presentation, this.testFileName);

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new File(this.testFileName));
        Element doc = document.getDocumentElement();

        //Act + Assert
        assertEquals("Test Presentation", doc.getElementsByTagName("title").item(0).getTextContent());
        assertEquals(1, doc.getElementsByTagName("slide").getLength());

        NodeList items = doc.getElementsByTagName("item");
        assertEquals(1, items.getLength());
        assertEquals("image", items.item(0).getAttributes().getNamedItem("kind").getTextContent());
        assertEquals("JabberPoint.jpg", items.item(0).getTextContent());
    }

    private void createTestFile(String content) {
        try (java.io.PrintWriter out = new java.io.PrintWriter(this.testFileName)) {
            out.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}