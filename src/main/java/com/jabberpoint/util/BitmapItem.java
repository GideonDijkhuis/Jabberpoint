package main.java.com.jabberpoint.util;

import main.java.com.jabberpoint.model.*;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import java.io.IOException;


/**
 * <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items hebben de verantwoordelijkheid om zichzelf te tekenen.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.7 2023/09/30 Bram Huiskes - Updated to fix image loading
 */

public class BitmapItem extends SlideItem
{
    private BufferedImage bufferedImage;
    private String imageName;

    protected static final String FILE = "Bestand ";
    protected static final String NOTFOUND = " niet gevonden";
    protected static final int DEFAULT_WIDTH = 200;
    protected static final int DEFAULT_HEIGHT = 150;

    // level staat voor het item-level; name voor de naam van het bestand met de afbeelding
    public BitmapItem(int level, String name)
    {
        super(level);
        imageName = name;
        loadImage();
    }

    // Een leeg bitmap-item
    public BitmapItem()
    {
        this(0, null);
    }

    // geef de bestandsnaam van de afbeelding
    public String getName()
    {
        return imageName;
    }

    // Load the image from various sources
    private void loadImage()
    {
        if (imageName == null)
        {
            return;
        }

        bufferedImage = null;

        // Try to load from file system
        try
        {
            File file = new File(imageName);
            if (file.exists())
            {
                bufferedImage = ImageIO.read(file);
                if (bufferedImage != null)
                {
                    return;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error loading from file: " + e.getMessage());
        }

        // Try to load from resources
        try
        {
            // Try to load directly as resource
            URL url = getClass().getResource("/" + imageName);
            if (url != null)
            {
                bufferedImage = ImageIO.read(url);
                if (bufferedImage != null)
                {
                    return;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error loading from resource: " + e.getMessage());
        }

        // Try to load from classpath root
        try
        {
            InputStream is = getClass().getClassLoader().getResourceAsStream(imageName);
            if (is != null)
            {
                bufferedImage = ImageIO.read(is);
                if (bufferedImage != null)
                {
                    return;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error loading from classpath: " + e.getMessage());
        }

        // Try to load from project root (for working dir)
        try
        {
            File rootFile = new File(System.getProperty("user.dir"), imageName);
            if (rootFile.exists())
            {
                bufferedImage = ImageIO.read(rootFile);
                if (bufferedImage != null)
                {
                    return;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error loading from project root: " + e.getMessage());
        }

        // If all loading attempts failed
        System.err.println(FILE + imageName + NOTFOUND);
    }

    // geef de bounding box van de afbeelding
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle)
    {
        if (bufferedImage == null)
        {
            return new Rectangle(
                    (int) (myStyle.indent * scale), 0,
                    (int) (DEFAULT_WIDTH * scale), (int) (DEFAULT_HEIGHT * scale)
            );
        }

        return new Rectangle(
                (int) (myStyle.indent * scale), 0,
                (int) (bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.leading * scale)) +
                        (int) (bufferedImage.getHeight(observer) * scale)
        );
    }

    // teken de afbeelding
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer)
    {
        if (bufferedImage == null)
        {
            g.drawString(
                    "Image " + imageName + " not found", x + (int) (myStyle.indent * scale),
                    y + (int) (myStyle.leading * scale)
            );
            return;
        }

        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);
        g.drawImage(
                bufferedImage, width, height,
                (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale), observer
        );
    }

    public String toString()
    {
        return "main.java.com.jabberpoint.util.BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
