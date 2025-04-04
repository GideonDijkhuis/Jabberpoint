package main.java.com.jabberpoint.ui;

import main.java.com.jabberpoint.command.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>This is the main.java.com.jabberpoint.ui.KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.7 2023/09/29 Bram Huiskes - Updated to use main.java.com.jabberpoint.command.Command pattern
 */

public class KeyController extends KeyAdapter
{
    private final Map<Integer, Command> keyCommands = new HashMap<>(); // Map of key codes to commands
    private final Map<Character, Command> charCommands = new HashMap<>(); // Map of characters to commands

    public KeyController(Presentation presentation)
    {
        // Get the command factory and set the presentation
        CommandFactory factory = CommandFactory.getInstance();
        factory.setPresentation(presentation);

        // Get commands from factory
        Command nextSlideCommand = factory.createNextSlideCommand();
        Command prevSlideCommand = factory.createPrevSlideCommand();
        Command exitCommand = factory.createExitCommand();

        // Register key commands
        keyCommands.put(KeyEvent.VK_PAGE_DOWN, nextSlideCommand);
        keyCommands.put(KeyEvent.VK_DOWN, nextSlideCommand);
        keyCommands.put(KeyEvent.VK_ENTER, nextSlideCommand);
        keyCommands.put(KeyEvent.VK_PAGE_UP, prevSlideCommand);
        keyCommands.put(KeyEvent.VK_UP, prevSlideCommand);

        // Register character commands
        charCommands.put('+', nextSlideCommand);
        charCommands.put('-', prevSlideCommand);
        charCommands.put('q', exitCommand);
        charCommands.put('Q', exitCommand);
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        int keyCode = keyEvent.getKeyCode();
        char keyChar = keyEvent.getKeyChar();

        // Check if there's a command for this key code
        Command command = keyCommands.get(keyCode);
        if (command != null)
        {
            command.execute();
            return;
        }

        // Check if there's a command for this character
        command = charCommands.get(keyChar);
        if (command != null)
        {
            command.execute();
        }
    }
}
