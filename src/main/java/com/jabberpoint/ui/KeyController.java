package main.java.com.jabberpoint.ui;

import main.java.com.jabberpoint.command.*;
import main.java.com.jabberpoint.factory.*;
import main.java.com.jabberpoint.model.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Map;

public class KeyController extends KeyAdapter
{
    private final Map<Integer, Command> keyCommands = new HashMap<>();
    private final Map<Character, Command> charCommands = new HashMap<>();

    public KeyController(Presentation presentation)
    {
        CommandFactory factory = CommandFactory.getInstance();
        factory.setPresentation(presentation);

        Command nextSlideCommand = factory.createNextSlideCommand();
        Command prevSlideCommand = factory.createPrevSlideCommand();
        Command exitCommand = factory.createExitCommand();

        keyCommands.put(KeyEvent.VK_PAGE_DOWN, nextSlideCommand);
        keyCommands.put(KeyEvent.VK_DOWN, nextSlideCommand);
        keyCommands.put(KeyEvent.VK_ENTER, nextSlideCommand);
        keyCommands.put(KeyEvent.VK_PAGE_UP, prevSlideCommand);
        keyCommands.put(KeyEvent.VK_UP, prevSlideCommand);

        charCommands.put('+', nextSlideCommand);
        charCommands.put('-', prevSlideCommand);
        charCommands.put('q', exitCommand);
        charCommands.put('Q', exitCommand);
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        int keyCode = keyEvent.getKeyCode();
        char keyChar = keyEvent.getKeyChar();

        Command command = keyCommands.get(keyCode);
        if (command != null)
        {
            command.execute();
            return;
        }

        command = charCommands.get(keyChar);
        if (command != null)
        {
            command.execute();
        }
    }
}
