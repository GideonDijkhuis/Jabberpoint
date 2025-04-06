package main.java.com.jabberpoint.util;

import java.awt.Color;
import java.awt.Font;

/**
 * Style class for defining the appearance of slide items.
 *
 * SOLID Principles: - Single Responsibility Principle: Only responsible for defining and managing presentation styles.
 * - Open/Closed Principle: New styles can be added without modifying existing styles. - Liskov Substitution Principle:
 * All style instances can be used interchangeably. - Interface Segregation Principle: Provides focused methods for
 * style operations. - Dependency Inversion Principle: Can be easily used by different components without tight
 * coupling.
 */
public class Style {
    private static Style[] styles;

    private static final String FONTNAME = "Helvetica";
    int indent;
    Color color;
    Font font;
    int fontSize;
    int leading;

    /**
     * Creates the default styles for different levels.
     */
    public static void createStyles() {
        styles = new Style[5];

        styles[0] = new Style(0, Color.red, 48, 20);    // style for item-level 0
        styles[1] = new Style(20, Color.blue, 40, 10);    // style for item-level 1
        styles[2] = new Style(50, Color.black, 36, 10);    // style for item-level 2
        styles[3] = new Style(70, Color.black, 30, 10);    // style for item-level 3
        styles[4] = new Style(90, Color.black, 24, 10);    // style for item-level 4
    }

    /**
     * Gets the style for the specified level.
     *
     * @param level The level to get the style for
     * @return The appropriate style
     */
    public static Style getStyle(int level) {
        if (level >= styles.length) {
            level = styles.length - 1;
        }
        return styles[level];
    }

    /**
     * Creates a new style with the specified properties.
     *
     * @param indent  The indent in pixels
     * @param color   The color of the text
     * @param points  The font size in points
     * @param leading The leading (line spacing)
     */
    public Style(int indent, Color color, int points, int leading) {
        this.indent = indent;
        this.color = color;
        this.font = new Font(FONTNAME, Font.BOLD, fontSize = points);
        this.leading = leading;
    }

    /**
     * Returns a string representation of this style.
     *
     * @return The string representation
     */
    public String toString() {
        return "[" + this.indent + "," + this.color + "; " + this.fontSize + " on " + this.leading + "]";
    }

    /**
     * Gets the font with the specified scale.
     *
     * @param scale The scale factor
     * @return The scaled font
     */
    public Font getFont(float scale) {
        return this.font.deriveFont(this.fontSize * scale);
    }
}
