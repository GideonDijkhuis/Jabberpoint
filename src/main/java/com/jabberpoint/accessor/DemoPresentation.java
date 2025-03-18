package main.java.com.jabberpoint.accessor;

import main.java.com.jabberpoint.model.*;
import main.java.com.jabberpoint.util.*;

import java.io.IOException;

/**
 * Een Demo-Presentatie
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2023/09/29 Bram Huiskes - Updated for Factory Method pattern
 */

public class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) {
		presentation.setTitle("Demo main.java.com.jabberpoint.model.Presentation");
		Slide slide;
		slide = new Slide();
		slide.setTitle("main.java.com.jabberpoint.JabberPoint");
		slide.append(1, "The Java presentation tool");
		slide.append(2, "Copyright (c) 1996-2000: Ian Darwin");
		slide.append(2, "Copyright (c) 2000-now: Gert Florijn and Sylvia Stuurman");
		slide.append(4, "Calling showSlide...");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Demonstration of levels and styles");
		slide.append(1, "Level 1");
		slide.append(2, "Level 2");
		slide.append(1, "Again level 1");
		slide.append(1, "Level 1 has style number 1");
		slide.append(2, "Level 2 has style number 2");
		slide.append(3, "This is how level 3 looks like");
		slide.append(4, "And this is level 4");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("The third slide");
		slide.append(1, "To open a new presentation,");
		slide.append(2, "use File->Open from the menu.");
		slide.append(1, " ");
		slide.append(1, "This is the end of the presentation.");
		slide.append(new BitmapItem(1, "main.java.com.jabberpoint.JabberPoint.jpg"));
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) throws IOException {
		throw new IOException("Save As->Demo! called");
	}
}
