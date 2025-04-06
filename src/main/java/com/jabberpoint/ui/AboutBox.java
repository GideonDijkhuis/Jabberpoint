package main.java.com.jabberpoint.ui;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 * AboutBox component for displaying information about JabberPoint.
 * 
 * SOLID Principles:
 * - Single Responsibility Principle: Only responsible for displaying the about information.
 * - Open/Closed Principle: Can be extended without modifying by creating subclasses if needed.
 * - Dependency Inversion Principle: Depends on abstractions (Frame) rather than concrete implementations.
 */
public class AboutBox
{
    /**
     * Shows information about JabberPoint in a dialog box.
     * 
     * @param parent The parent frame for the dialog
     */
    public static void show(Frame parent)
    {
        JOptionPane.showMessageDialog(
                parent,
                "JabberPoint is a primitive slide-show program in Java(tm). It\n" +
                        "is freely copyable as long as you keep this notice and\n" +
                        "the splash screen intact.\n" +
                        "Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
                        "Adapted by Gert Florijn (version 1.1) and " +
                        "Sylvia Stuurman (version 1.2 and higher) for the Open" +
                        "University of the Netherlands, 2002 -- now." +
                        "Author's version available from http://www.darwinsys.com/." +
                        "Redesigned and upgraded by Bram Huiskes and Gideon Dijkhuis",
                "About JabberPoint",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
