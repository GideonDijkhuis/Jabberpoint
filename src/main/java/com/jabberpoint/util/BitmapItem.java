package main.java.com.jabberpoint.util;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import main.java.com.jabberpoint.model.SlideItem;

/**
 * A bitmap image item that can be displayed on a slide.
 * Bitmap items are responsible for drawing themselves.
 *
 * SOLID Principles:
 * - Single Responsibility Principle: Only responsible for loading and displaying bitmap images.
 * - Open/Closed Principle: Can be extended with new image loading methods without modifying existing code.
 * - Liskov Substitution Principle: Properly extends SlideItem without changing its behavior.
 * - Interface Segregation Principle: Implements only necessary methods from SlideItem.
 * - Dependency Inversion Principle: Uses abstractions (Graphics, ImageObserver) rather than concrete implementations.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.7 2023/09/30 Bram Huiskes - Updated to fix image loading
 */
public class BitmapItem extends SlideItem {
    private BufferedImage bufferedImage;
    private final String imageName;

    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";
    protected static final int DEFAULT_WIDTH = 200;
    protected static final int DEFAULT_HEIGHT = 150;

    /**
     * Creates a bitmap item with the specified level and image name.
     * 
     * @param level The level of the bitmap item
     * @param name The name of the image file
     */
    public BitmapItem(int level, String name) {
        super(level);
        this.imageName = name;
        loadImage();
    }

    /**
     * Creates an empty bitmap item with default level.
     */
    public BitmapItem() {
        this(0, null);
    }

    /**
     * Gets the name of the image.
     * 
     * @return The name of the image
     */
    public String getName() {
        return this.imageName;
    }

    /**
     * Loads the image from various locations.
     * Will try to load from:
     * 1. Direct file path
     * 2. Resources
     * 3. Classpath
     * 4. Project root
     */
    private void loadImage() {
        if (this.imageName == null) {
            return;
        }

        this.bufferedImage = null;

        try {
            File file = new File(this.imageName);
            if (file.exists()) {
                this.bufferedImage = ImageIO.read(file);
                if (this.bufferedImage != null) {
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }

        // Try to load from resources
        try {
            // Try to load directly as resource
            URL url = getClass().getResource("/" + this.imageName);
            if (url != null) {
                this.bufferedImage = ImageIO.read(url);
                if (this.bufferedImage != null) {
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from resource: " + e.getMessage());
        }

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(this.imageName);
            if (is != null) {
                this.bufferedImage = ImageIO.read(is);
                if (this.bufferedImage != null) {
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from classpath: " + e.getMessage());
        }

        try {
            File rootFile = new File(System.getProperty("user.dir"), this.imageName);
            if (rootFile.exists()) {
                this.bufferedImage = ImageIO.read(rootFile);
                if (this.bufferedImage != null) {
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from project root: " + e.getMessage());
        }

        System.err.println(FILE + this.imageName + NOTFOUND);
    }

    /**
     * Gets the bounding box for this bitmap item.
     * 
     * @param g The graphics context
     * @param observer The image observer
     * @param scale The scale factor
     * @param myStyle The style to apply
     * @return The bounding rectangle
     */
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
        if (this.bufferedImage == null) {
            return new Rectangle(
                    (int) (myStyle.indent * scale), 0,
                    (int) (DEFAULT_WIDTH * scale), (int) (DEFAULT_HEIGHT * scale)
            );
        }

        return new Rectangle(
                (int) (myStyle.indent * scale), 0,
                (int) (this.bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.leading * scale)) +
                        (int) (this.bufferedImage.getHeight(observer) * scale)
        );
    }

    /**
     * Draws the bitmap item on the screen.
     * 
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param scale The scale factor
     * @param g The graphics context
     * @param myStyle The style to apply
     * @param observer The image observer
     */
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
        if (this.bufferedImage == null) {
            g.drawString(
                    "Image " + this.imageName + " not found", x + (int) (myStyle.indent * scale),
                    y + (int) (myStyle.leading * scale)
            );
            return;
        }

        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);
        g.drawImage(
                this.bufferedImage, width, height,
                (int) (this.bufferedImage.getWidth(observer) * scale),
                (int) (this.bufferedImage.getHeight(observer) * scale), observer
        );
    }

    /**
     * Returns a string representation of this bitmap item.
     * 
     * @return The string representation
     */
    public String toString() {
        return "BitmapItem[" + getLevel() + "," + this.imageName + "]";
    }
}
