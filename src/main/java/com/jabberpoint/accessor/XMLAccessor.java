package main.java.com.jabberpoint.accessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.java.com.jabberpoint.model.Presentation;
import main.java.com.jabberpoint.model.Slide;
import main.java.com.jabberpoint.model.SlideItem;
import main.java.com.jabberpoint.util.BitmapItem;
import main.java.com.jabberpoint.util.TextItem;

/**
 * SOLID Principles Applied: - Single Responsibility Principle: Only responsible for XML file operations - Open/Closed
 * Principle: Can be extended without modification - Liskov Substitution Principle: Properly implements all Accessor
 * methods - Interface Segregation Principle: Implements only necessary Accessor methods - Dependency Inversion
 * Principle: Depends on abstractions not concrete implementations
 *
 * Accessor for loading and saving presentations in XML format.
 */
public class XMLAccessor extends Accessor {

    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";

    /** Names of xml tags of attributes */
    protected static final String SLIDETAG = "slide";
    protected static final String SHOWTITLETAG = "showtitle";
    protected static final String TITLETAG = "title";
    protected static final String ITEMTAG = "item";
    protected static final String LEVELTAG = "level";
    protected static final String KINDTAG = "kind";
    protected static final String TEXTTAG = "text";
    protected static final String IMAGETAG = "image";

    /** Text of messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    /**
     * Gets the title element's content from an XML element.
     *
     * @param element The element containing the title
     * @param tagName The tag name of the title element
     * @return The title text
     */
    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    /**
     * Loads a presentation from an XML file.
     *
     * @param presentation The presentation to load data into
     * @param filename     The name of the XML file to load from
     * @throws IOException If an I/O error occurs during loading
     */
    public void loadFile(Presentation presentation, String filename) throws IOException {
        int slideNumber, itemNumber, max = 0, maxItems = 0;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); // Create a JDOM document
            Element doc = document.getDocumentElement();
            presentation.setTitle(getTitle(doc, SHOWTITLETAG));

            NodeList slides = doc.getElementsByTagName(SLIDETAG);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++) {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(getTitle(xmlSlide, TITLETAG));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEMTAG);
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
                    Element item = (Element) slideItems.item(itemNumber);
                    loadSlideItem(slide, item);
                }
            }
        }
        catch (IOException iox) {
            System.err.println(iox.toString());
            throw iox;
        }
        catch (SAXException sax) {
            System.err.println(sax.getMessage());
            throw new IOException("Parse error");
        }
        catch (ParserConfigurationException pcx) {
            System.err.println(PCE);
            throw new IOException("Parse error");
        }
    }

    /**
     * Loads a slide item from an XML element.
     *
     * @param slide The slide to add the item to
     * @param item  The XML element containing the item data
     */
    protected void loadSlideItem(Slide slide, Element item) {
        int level = 1; // default
        NamedNodeMap attributes = item.getAttributes();
        String leveltext = attributes.getNamedItem(LEVELTAG).getTextContent();
        if (leveltext != null) {
            try {
                level = Integer.parseInt(leveltext);
            }
            catch (NumberFormatException x) {
                System.err.println(NFE);
            }
        }
        String type = attributes.getNamedItem(KINDTAG).getTextContent();
        if (TEXTTAG.equals(type)) {
            slide.append(new TextItem(level, item.getTextContent()));
        }
        else {
            if (IMAGETAG.equals(type)) {
                slide.append(new BitmapItem(level, item.getTextContent()));
            }
            else {
                System.err.println(UNKNOWNTYPE);
            }
        }
    }

    /**
     * Saves a presentation to an XML file.
     *
     * @param presentation The presentation to save
     * @param filename     The name of the file to save to
     * @throws IOException If an I/O error occurs during saving
     */
    public void saveFile(Presentation presentation, String filename) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
        out.println("<presentation>");
        out.print("<title>");
        out.print(presentation.getTitle());
        out.println("</title>");
        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
            Slide slide = presentation.getSlide(slideNumber);
            out.println("<slide>");
            out.print("<title>");
            out.print(slide.getTitle());
            out.println("</title>");
            Vector<SlideItem> slideItems = slide.getSlideItems();
            for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
                SlideItem slideItem = slideItems.elementAt(itemNumber);
                out.print("<item kind=");
                if (slideItem instanceof TextItem) {
                    out.print("\"text\" level=\"");
                    out.print(slideItem.getLevel());
                    out.print("\">");
                    out.print(((TextItem) slideItem).getText());
                }
                else {
                    if (slideItem instanceof BitmapItem) {
                        out.print("\"image\" level=\"");
                        out.print(slideItem.getLevel());
                        out.print("\">");
                        out.print(((BitmapItem) slideItem).getName());
                    }
                    else {
                        System.out.println("Ignoring " + slideItem);
                    }
                }
                out.println("</item>");
            }
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }
}
